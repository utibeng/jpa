package com.utibe.olympics.dataload;

import com.utibe.olympics.dataload.tables.athletenames.AthleteNames;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Prework {

    public enum HeaderEnum {
        ID,Name,Sex,Age,Height,Weight,Team,NOC,Games,Year,Season,City,Sport,Event,Medal
    }

    //https://howtodoinjava.com/java8/java-stream-distinct-examples/
    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor){
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    public Stream <AthleteNames> getAthleteTableRow(Stream<CSVRecord> csvRecordStream){
        Stream <AthleteNames> athleteName = csvRecordStream.filter( distinctByKey(p -> p.get("ID")) ).map(csvRecord ->
                new AthleteNames(Integer.parseInt(csvRecord.get("ID")),
                        csvRecord.get("Name") ));
        return athleteName;
    }

    //generate unique Id and Name
    private List<AthleteNames> getAthletesNameTableData(Supplier<Stream<CSVRecord>> csvRecordsSupplier){
        List<AthleteNames> athletes = getAthleteTableRow(csvRecordsSupplier.get()).collect(Collectors.toList());
        System.out.println("ok here");

        return athletes;
    }


    private void processCsvFile(String fileName) {

        Supplier<Stream<CSVRecord>> csvRecordsSupplier = null;

        try {
            File file = new ClassPathResource(fileName).getFile();

            //see https://github.com/apache/commons-csv/blob/master/src/test/java/org/apache/commons/csv/CSVParserTest.java
            CSVFormat FORMAT_EXPLICIT_HEADER = CSVFormat.Builder.create(CSVFormat.DEFAULT)
                    .setSkipHeaderRecord(true)
                    .setHeader(HeaderEnum.class)
                    .build();

            CSVParser parser = CSVParser.parse(file, Charset.defaultCharset(), FORMAT_EXPLICIT_HEADER);
            System.out.println(parser.getHeaderNames());

            //see https://stackoverflow.com/questions/23860533/copy-a-stream-to-avoid-stream-has-already-been-operated-upon-or-closed
            csvRecordsSupplier = parser::stream;

            getAthletesNameTableData(csvRecordsSupplier);

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public static void main(String [] args) {
        Prework prework = new Prework();
        prework.processCsvFile("OLYMPICS_athlete_events.csv");
    }
}

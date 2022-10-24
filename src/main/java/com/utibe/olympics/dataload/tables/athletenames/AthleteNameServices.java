package com.utibe.olympics.dataload.tables.athletenames;

import org.apache.commons.csv.CSVRecord;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AthleteNameServices {
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
    public List<AthleteNames> getAthletesNameTableData(Supplier<Stream<CSVRecord>> csvRecordsSupplier){
        List<AthleteNames> athletes = getAthleteTableRow(csvRecordsSupplier.get()).collect(Collectors.toList());
        System.out.println("ok here");

        return athletes;
    }


}

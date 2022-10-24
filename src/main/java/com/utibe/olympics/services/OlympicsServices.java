package com.utibe.olympics.services;

import com.utibe.olympics.dataload.Prework;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.nio.charset.Charset;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class OlympicsServices {

    public enum HeaderEnum {
        ID,Name,Sex,Age,Height,Weight,Team,NOC,Games,Year,Season,City,Sport,Event,Medal
    }


    //Takes a csv file and returns a Supplier<Stream<CSVRecord>>
    private Supplier<Stream<CSVRecord>> processCsvFile(String fileName) {

        Supplier<Stream<CSVRecord>> csvRecordsSupplier = null;

        try {
            File file = new ClassPathResource(fileName).getFile();

            //see https://github.com/apache/commons-csv/blob/master/src/test/java/org/apache/commons/csv/CSVParserTest.java
            CSVFormat FORMAT_EXPLICIT_HEADER = CSVFormat.Builder.create(CSVFormat.DEFAULT)
                    .setSkipHeaderRecord(true)
                    .setHeader(Prework.HeaderEnum.class)
                    .build();

            CSVParser parser = CSVParser.parse(file, Charset.defaultCharset(), FORMAT_EXPLICIT_HEADER);
            System.out.println(parser.getHeaderNames());

            //see https://stackoverflow.com/questions/23860533/copy-a-stream-to-avoid-stream-has-already-been-operated-upon-or-closed
            csvRecordsSupplier = parser::stream;

        } catch (Exception e) {
            System.out.println(e);
        }

        return csvRecordsSupplier;
    }
}

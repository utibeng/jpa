package com.utibe.olympics.dataload.tables;

import org.apache.commons.csv.CSVRecord;

import java.util.function.Supplier;
import java.util.stream.Stream;

public interface OlympicsTables {
    public long recordCount(Supplier<Stream<CSVRecord>> csvRecordsSupplier);
}

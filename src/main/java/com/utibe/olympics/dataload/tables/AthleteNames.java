package com.utibe.olympics.dataload.tables;

import org.apache.commons.csv.CSVRecord;

import java.util.function.Supplier;
import java.util.stream.Stream;

public class AthleteNames implements OlympicsTables{

    private int ID;
    private String name;

    public AthleteNames(int ID, String name) {
        this.ID = ID;
        this.name = name;
        System.out.println("ID = " + this.ID + " Name = " + this.getName());
    }

    public long recordCount(Supplier<Stream<CSVRecord>> csvRecordsSupplier){
        return csvRecordsSupplier.get().count();
    }

    public Stream <AthleteNames> getAthleteTableRow(Stream<CSVRecord> csvRecordStream){
        //ID,Name,Sex,Age,Height,Weight,Team,NOC,Games,Year,Season,City,Sport,Event,Medal
        //Stream <String> name = csvRecordStream.map(csvRecord -> csvRecord.get("Name") );
        Stream <AthleteNames> athleteName = csvRecordStream.map(csvRecord ->
                                                                new AthleteNames(Integer.parseInt(csvRecord.get("ID")),
                                                                        csvRecord.get("Name") ));
        return athleteName;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

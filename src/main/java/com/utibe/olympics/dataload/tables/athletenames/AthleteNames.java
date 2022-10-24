package com.utibe.olympics.dataload.tables.athletenames;


import org.apache.commons.csv.CSVRecord;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.function.Supplier;
import java.util.stream.Stream;

@Entity
@Table(name="Athletesnames", schema="public")
public class AthleteNames{

    @Id
    @Column(name="ID")
    private int id;

    @Column(name="Name")
    private String name;

    public AthleteNames() {

    }

    public AthleteNames(int ID, String name) {
        this.id= ID;
        this.name = name;
        System.out.println("ID = " + this.id + " Name = " + this.getName());
    }

    public long recordCount(Supplier<Stream<CSVRecord>> csvRecordsSupplier){
        return csvRecordsSupplier.get().count();
    }

    public Stream <AthleteNames> getAthleteTableRow(Stream<CSVRecord> csvRecordStream){
        return csvRecordStream.map(csvRecord ->
                new AthleteNames(Integer.parseInt(csvRecord.get("ID")),
                        csvRecord.get("Name") ));
    }

    public int getID() {
        return id;
    }

    public void setID(int ID) {
        this.id = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return "id=" + this.id + ", Name=" + this.name;
    }
}

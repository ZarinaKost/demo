package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Otchet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String otchetName;
    private int year;

    public Otchet() {
        this.otchetName = "" ;
        this.year = 0 ;
    }

    public Otchet(String bookName, int year) {
        this.otchetName = otchetName;
        this.year = year;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOtchetName() {
        return otchetName;
    }

    public void setOtchetName(String jurnalName) {
        this.otchetName = otchetName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}





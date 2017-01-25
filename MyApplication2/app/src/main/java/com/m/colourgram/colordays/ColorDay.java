package com.m.colourgram.colordays;

import com.m.colourgram.calendar.CalendarHelper;

import io.realm.RealmObject;

/**
 * object for color day
 */
public class ColorDay extends RealmObject {
    private String yymmdd;
    private String year;
    private String month;
    private String date;
    private String day;
    private String color;
    private String mood;
    public ColorDay(){
    }

    public ColorDay(String color, String mood){
        CalendarHelper helper = new CalendarHelper();
        this.year = helper.getCurrentYear();
        this.month = helper.getCurrentMonth();
        this.date = helper.getCurrentDate();
    }
    public void setColor(String color) {
        this.color = color;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getColor() {
        return color;
    }

    public String getMood() {
        return mood;
    }

    public String getYear() {
        return year;
    }

    public String getMonth() {
        return month;
    }

    public String getDate() {
        return date;
    }

    public String getDay() {
        return day;
    }
}

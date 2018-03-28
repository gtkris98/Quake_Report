package com.example.android.quakereport;

public class EarthquakeData
{
    private String location,url;
    private Double magnitude;
    private Long date;

    public EarthquakeData(String location, Double magnitude, Long date, String url) {
        this.location = location;
        this.magnitude = magnitude;
        this.date = date;
        this.url = url;
    }

    public String getLocation() {
        return location;
    }

    public Double getMagnitude() {
        return magnitude;
    }

    public String getUrl() {
        return url;
    }

    public Long getDate() {
        return date;
    }
}

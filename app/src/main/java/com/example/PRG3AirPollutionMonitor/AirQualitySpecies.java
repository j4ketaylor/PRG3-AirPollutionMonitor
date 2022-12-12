package com.example.PRG3AirPollutionMonitor;

public class AirQualitySpecies {
    Integer AirQualitySpeciesTotal;
    Integer AirQualityEntriesTotal;
    String AirQualitySpeciesName;
    String AirQualityColour;

    public AirQualitySpecies(Integer airQualitySpeciesTotal, Integer airQualityEntriesTotal, String airQualitySpeciesName, String airQualityColour) {
        AirQualitySpeciesTotal = airQualitySpeciesTotal;
        AirQualityEntriesTotal = airQualityEntriesTotal;
        AirQualitySpeciesName = airQualitySpeciesName;
        AirQualityColour = airQualityColour;
    }

    public Integer getAirQualitySpeciesTotal() {
        return AirQualitySpeciesTotal;
    }

    public void setAirQualitySpeciesTotal(Integer airQualitySpeciesTotal) {
        AirQualitySpeciesTotal = airQualitySpeciesTotal;
    }

    public Integer getAirQualityEntriesTotal() {
        return AirQualityEntriesTotal;
    }

    public void setAirQualityEntriesTotal(Integer airQualityEntriesTotal) {
        AirQualityEntriesTotal = airQualityEntriesTotal;
    }

    public String getAirQualityColour() {
        return AirQualityColour;
    }

    public void setAirQualityColour(String airQualityColour) {
        AirQualityColour = airQualityColour;
    }

    public String getAirQualitySpeciesName() {
        return AirQualitySpeciesName;
    }

    public void setAirQualitySpeciesName(String airQualitySpeciesName) {
        AirQualitySpeciesName = airQualitySpeciesName;
    }
}

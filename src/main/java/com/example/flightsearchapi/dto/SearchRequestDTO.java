package com.example.flightsearchapi.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SearchRequestDTO {

    public SearchRequestDTO() {
    }

    public SearchRequestDTO(String departureAirport, String arrivalAirport, LocalDateTime departureTime, LocalDateTime returnTime) {
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.departureTime = departureTime;
        this.returnTime = returnTime;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(String arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(LocalDateTime returnTime) {
        this.returnTime = returnTime;
    }

    private String departureAirport;
    private String arrivalAirport;
    private LocalDateTime departureTime;
    private LocalDateTime returnTime;

    private String formatLocalDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTime.format(formatter);
    }
    @Override
    public String toString() {
        return "SearchRequest{" +
                "arrivalAirport='" + arrivalAirport + '\'' +
                ", departureTime=" + departureTime +
                ", returnTime=" + returnTime +
                '}';
    }

}

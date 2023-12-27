package com.example.flightsearchapi.service;

import com.example.flightsearchapi.model.Airport;

import java.util.List;
import java.util.Optional;

public interface AirportServiceInterface {
    List<Airport> getAllAirports();
    Optional<Airport> getAirportById(Long id);
    Airport saveAirport(Airport airport);
    void deleteAirport(Long id);
}

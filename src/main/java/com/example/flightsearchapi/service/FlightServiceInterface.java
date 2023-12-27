package com.example.flightsearchapi.service;

import com.example.flightsearchapi.model.Flight;

import java.util.List;
import java.util.Optional;

public interface FlightServiceInterface {
    List<Flight> getAllFlights();
    Optional<Flight> getFlightById(Long id);
    Flight saveFlight(Flight flight);
    void deleteFlight(Long id);
}

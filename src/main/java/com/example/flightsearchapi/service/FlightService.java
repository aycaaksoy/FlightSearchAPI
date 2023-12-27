package com.example.flightsearchapi.service;

import com.example.flightsearchapi.model.Flight;
import com.example.flightsearchapi.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class FlightService implements FlightServiceInterface {
    @Autowired
    private FlightRepository flightRepository;


    @Override
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    @Override
    public Optional<Flight> getFlightById(Long id) {
        return flightRepository.findById(id);
    }

    @Override
    public Flight saveFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    @Override
    public void deleteFlight(Long id) {
        flightRepository.deleteById(id);
    }

    public List<Flight> searchFlights(String departure, String destination, LocalDateTime departureTime, LocalDateTime returnTime) {
        if (returnTime == null) {
            // One-way flight
            return searchOneWayFlights(departure, destination, departureTime);
        } else {
            // Two-way flight
            return searchTwoWayFlights(departure, destination, departureTime, returnTime);
        }
    }

    private List<Flight> searchOneWayFlights(String departure, String destination, LocalDateTime departureTime) {
        return flightRepository.findByDepAndArrAndDepTime(departure, destination, departureTime);
    }

    private List<Flight> searchTwoWayFlights(String departure, String destination, LocalDateTime departureTime, LocalDateTime returnTime) {
        return flightRepository.findByDepAndArrAndDepTimeAndRetTime(departure, destination, departureTime, returnTime);
    }
}

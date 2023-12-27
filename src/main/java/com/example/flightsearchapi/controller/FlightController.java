package com.example.flightsearchapi.controller;

import com.example.flightsearchapi.model.Flight;
import com.example.flightsearchapi.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/flights")
public class FlightController {
    @Autowired
    private FlightService flightService;

    @GetMapping
    public List<Flight> getAllFlights() {
        return flightService.getAllFlights();
    }

    @GetMapping("/{id}")
    public Flight getFlightById(@PathVariable Long id) {
        return flightService.getFlightById(id).orElse(null);
    }

    @PostMapping
    public Flight saveFlight(@RequestBody Flight flight) {
        return flightService.saveFlight(flight);
    }

    @PutMapping("/{id}")
    public Flight updateFlight(@PathVariable Long id, @RequestBody Flight flight) {
        flight.setId(id);
        return flightService.saveFlight(flight);
    }

    @DeleteMapping("/{id}")
    public void deleteFlight(@PathVariable Long id) {
        flightService.deleteFlight(id);
    }

    @GetMapping
    public List<Flight> searchFlights(
            @RequestParam String departure,
            @RequestParam String destination,
            @RequestParam LocalDateTime departureTime,
            @RequestParam(required = false) LocalDateTime returnTime) {

        // Call the flight service to perform the search
        return flightService.searchFlights(departure, destination, departureTime, returnTime);
    }
}
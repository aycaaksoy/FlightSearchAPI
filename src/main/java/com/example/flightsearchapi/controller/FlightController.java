package com.example.flightsearchapi.controller;

import com.example.flightsearchapi.dto.FlightDTO;
import com.example.flightsearchapi.dto.SearchRequestDTO;
import com.example.flightsearchapi.model.Flight;
import com.example.flightsearchapi.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        flight.setDepartureTime(LocalDateTime.parse(flight.getDepartureTime().format(formatter), formatter));
        flight.setReturnTime(LocalDateTime.parse(flight.getReturnTime().format(formatter), formatter));

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

    @PostMapping("/search")
    public ResponseEntity<List<FlightDTO>> searchFlights(@RequestBody SearchRequestDTO searchRequestDto) {
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        List<FlightDTO> flights = flightService.searchFlights(
                searchRequestDto.getDepartureAirport(),
                searchRequestDto.getArrivalAirport(),
                searchRequestDto.getDepartureTime(),
                searchRequestDto.getReturnTime()
        );

        return ResponseEntity.ok(flights);
    }

}
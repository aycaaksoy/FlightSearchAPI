package com.example.flightsearchapi.service;

import com.example.flightsearchapi.dto.FlightDTO;
import com.example.flightsearchapi.model.Flight;
import com.example.flightsearchapi.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<FlightDTO> searchFlights(String departureAirport, String arrivalAirport, LocalDateTime departureTime, LocalDateTime returnTime) {
        if (returnTime == null) {
            // One-way flight
            return searchOneWayFlights(departureAirport, arrivalAirport, departureTime);
        } else {
            // Two-way flight
            return searchTwoWayFlights(departureAirport, arrivalAirport, departureTime, returnTime);
        }
    }

    private List<FlightDTO> searchOneWayFlights(String departureAirport, String arrivalAirport, LocalDateTime departureTime) {
        List<Flight> flights = flightRepository.findByDepartureAirportAndArrivalAirportAndDepartureTime(departureAirport, arrivalAirport, departureTime);
        return mapToDTOs(flights);
    }

    private List<FlightDTO> searchTwoWayFlights(String departureAirport, String arrivalAirport, LocalDateTime departureTime, LocalDateTime returnTime) {
        List<Flight> flights = flightRepository.findByDepartureAirportAndArrivalAirportAndDepartureTimeAndReturnTime(departureAirport, arrivalAirport, departureTime, returnTime);
        return mapToDTOs(flights);
    }

    private List<FlightDTO> mapToDTOs(List<Flight> flights) {
        return flights.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private FlightDTO mapToDTO(Flight flight) {
        FlightDTO flightDTO = new FlightDTO();
        flightDTO.setId(flight.getId());
        flightDTO.setDepartureAirport(flight.getDepartureAirport());
        flightDTO.setArrivalAirport(flight.getArrivalAirport());
        flightDTO.setDepartureTime(flight.getDepartureTime());
        flightDTO.setReturnTime(flight.getReturnTime());

        return flightDTO;
    }
}

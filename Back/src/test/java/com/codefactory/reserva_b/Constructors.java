package com.codefactory.reserva_b;

import com.codefactory.reserva_b.dto.impl.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Constructors {
    public static CityResponseDTOImpl createCityResponseDTOImpl() {
        CountryResponseDTOImpl country = createCountryResponseDTOImpl();
        return new CityResponseDTOImpl(1L, "City Name", 1L, country, "GMT+1", 40.7128f, 74.0060f);
    }

    public static CountryResponseDTOImpl createCountryResponseDTOImpl() {
        return new CountryResponseDTOImpl(1L, "Country Name", "Europe", "English");
    }

    public static PlaneResponseDTOImpl createPlaneResponseDTOImpl() {
        return new PlaneResponseDTOImpl(1L, "Boeing 747", "Boeing", 200, 50, 10, LocalDate.of(2000, 1, 1), LocalDate.of(2023, 1, 1), "ABC123", 183000.0f);
    }

    public static PilotResponseDTOImpl createPilotResponseDTOImpl() {
        return new PilotResponseDTOImpl(1L, "John", "Doe", "LIC12345", LocalDate.of(1980, 5, 20), "American", "Captain", 10000.0f, LocalDate.of(2010, 6, 1), "Active", "JFK", LocalDate.of(2023, 1, 15));
    }

    public static FlightResponseDTOImpl createFlightResponseDTOImpl() {
        CityResponseDTOImpl departureCity = createCityResponseDTOImpl();
        CityResponseDTOImpl arrivalCity = createCityResponseDTOImpl();
        PlaneResponseDTOImpl plane = createPlaneResponseDTOImpl();
        PilotResponseDTOImpl captain = createPilotResponseDTOImpl();
        PilotResponseDTOImpl subCaptain = createPilotResponseDTOImpl();
        return new FlightResponseDTOImpl(1L, "FL123", 1L, plane, 1L, departureCity, 2L, arrivalCity, LocalDateTime.now(), LocalDateTime.now().plusHours(2), "On Time", "2h", 1500.0f, 180, 1L, captain, 2L, subCaptain, 100.0f, 200.0f, 300.0f);
    }

    public static PassengerResponseDTOImpl createPassengerResponseDTOImpl() {
        SeatResponseDTOImpl seat = createSeatResponseDTOImpl();
        List<LuggageResponseDTOImpl> luggageList = createLuggageResponseDTOList();
        return new PassengerResponseDTOImpl(1L, 1L, seat, "John", "Doe", "1990-01-01", "123456789", "P12345", "American", "Vegetarian meal", true, luggageList);
    }

    public static SeatResponseDTOImpl createSeatResponseDTOImpl() {
        return new SeatResponseDTOImpl(1L, 1L, "A1", false, 150.0f, "Economy");
    }

    public static List<LuggageResponseDTOImpl> createLuggageResponseDTOList() {
        List<LuggageResponseDTOImpl> luggageList = new ArrayList<>();
        luggageList.add(new LuggageResponseDTOImpl(1L, 1L, "Checked", 50.0f, 23.0f, 40.0f, 0.0f));
        luggageList.add(new LuggageResponseDTOImpl(2L, 1L, "Carry-on", 40.0f, 8.0f, 30.0f, 0.0f));
        return luggageList;
    }

    public static PaymentResponseDTOImpl createPaymentResponseDTOImpl() {
        return new PaymentResponseDTOImpl(1L, 1L, 300.0f, LocalDateTime.now(), "Completed");
    }

    public static UsersResponseDTOImpl createUserResponseDTOImpl() {
        return new UsersResponseDTOImpl(1L, "John", "Doe", "123456789", "john.doe@example.com", "American", "1990-01-01", "123456789", "P12345", "2020-01-01", 1001, "123 Main St, New York, NY", "Regular");
    }

    public static BookingResponseDTOImpl createBookingResponseDTOImpl() {
        FlightResponseDTOImpl flight = createFlightResponseDTOImpl();
        UsersResponseDTOImpl user = createUserResponseDTOImpl();
        List<PassengerResponseDTOImpl> passengers = createPassengerResponseDTOList();
        PaymentResponseDTOImpl payment = createPaymentResponseDTOImpl();
        return new BookingResponseDTOImpl(1L, 1L, flight, 1L, user, LocalDateTime.now(), "Confirmed", passengers, payment);
    }

    public static List<PassengerResponseDTOImpl> createPassengerResponseDTOList() {
        List<PassengerResponseDTOImpl> passengers = new ArrayList<>();
        passengers.add(createPassengerResponseDTOImpl());
        passengers.add(new PassengerResponseDTOImpl(2L, 2L, createSeatResponseDTOImpl(), "Jane", "Doe", "1992-02-02", "987654321", "P54321", "Canadian", "No requests", true, createLuggageResponseDTOList()));
        return passengers;
    }

    public static LuggageResponseDTOImpl createLuggageResponseDTOImpl() {
        return new LuggageResponseDTOImpl(1L, 1L, "Checked", 50.0f, 23.0f, 40.0f, 0.0f);
    }

    // Helper para crear una instancia completa de BookingRequestDTOImpl
    public static BookingRequestDTOImpl createBookingRequestDTOImpl() {
        Long idFlight = 1L;
        Long idUser = 1L;
        String bookingDate = "2024-10-22"; // Fecha como String
        String bookingStatus = "Confirmed"; // Estado de la reserva
        List<PassengerRequestDTOImpl> passengers = createPassengerRequestDTOList(); // Creamos una lista de PassengerRequestDTOImpl

        return new BookingRequestDTOImpl(idFlight, idUser, bookingDate, bookingStatus, passengers);
    }

    // Helper para crear una lista de PassengerRequestDTOImpl
    public static List<PassengerRequestDTOImpl> createPassengerRequestDTOList() {
        List<PassengerRequestDTOImpl> passengers = new ArrayList<>();
        passengers.add(createPassengerRequestDTOImpl(1L, "John", "Doe", "1990-01-01", "123456789", "P12345", "American", "Vegetarian meal", true));
        passengers.add(createPassengerRequestDTOImpl(2L, "Jane", "Doe", "1992-02-02", "987654321", "P54321", "Canadian", "No requests", true));
        return passengers;
    }

    // Helper para crear una instancia completa de PassengerRequestDTOImpl
    public static PassengerRequestDTOImpl createPassengerRequestDTOImpl(Long idSeat, String firstName, String lastName, String dateOfBirth,
                                                                  String documentId, String passportNumber, String nationality,
                                                                  String specialRequests, Boolean luggageIncluded) {
        List<LuggageRequestDTOImpl> luggageList = createLuggageRequestDTOList(); // Creamos la lista de LuggageRequestDTOImpl

        return new PassengerRequestDTOImpl(
                idSeat, // idSeat
                firstName, // firstName
                lastName, // lastName
                dateOfBirth, // dateOfBirth
                documentId, // documentId
                passportNumber, // passportNumber
                nationality, // nationality
                specialRequests, // specialRequests
                luggageIncluded, // luggageIncluded
                luggageList // Lista de equipaje
        );
    }

    // Helper para crear una lista de LuggageRequestDTOImpl
    public static List<LuggageRequestDTOImpl> createLuggageRequestDTOList() {
        List<LuggageRequestDTOImpl> luggageList = new ArrayList<>();
        luggageList.add(new LuggageRequestDTOImpl(1L, "Checked Bag", 50.0f, 23.0f, 40.0f, 0.0f));
        luggageList.add(new LuggageRequestDTOImpl(2L, "Carry-on", 40.0f, 8.0f, 30.0f, 0.0f));
        return luggageList;
    }

    // Helper para crear un LuggageRequestDTOImpl
    public static LuggageRequestDTOImpl createLuggageRequestDTO() {
        return new LuggageRequestDTOImpl(1L, "Checked Bag", 50.0f, 23.0f, 40.0f, 0.0f);
    }
}

package com.codefactory.reserva_b.service;

import static com.codefactory.reserva_b.Constructors.createFlightResponseDTOImpl;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.codefactory.reserva_b.dto.impl.FlightResponseDTOImpl;
import com.codefactory.reserva_b.entity.impl.FlightEntityImpl;
import com.codefactory.reserva_b.repository.interfaces.IFlightRepository;
import com.codefactory.reserva_b.service.impl.FlightServiceImpl;
import com.codefactory.reserva_b.util.interfaces.IFlightMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

class FlightServiceImplTest {

    @Mock
    private IFlightRepository flightRepository;

    @Mock
    private IFlightMapper flightMapper;

    @InjectMocks
    private FlightServiceImpl flightService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAllFlights_ShouldReturnFlightResponseDTOs() {
        // Arrange
        List<FlightEntityImpl> flightEntities = new ArrayList<>();
        flightEntities.add(new FlightEntityImpl()); // Mocked Flight Entity

        List<FlightResponseDTOImpl> flightDTOs = new ArrayList<>();
        flightDTOs.add(createFlightResponseDTOImpl()); // Using the helper method to create a FlightResponseDTOImpl

        when(flightRepository.findAllFlights()).thenReturn(flightEntities);
        when(flightMapper.mapFlightEntitiesToFlightResponseDTOs(flightEntities)).thenReturn(flightDTOs);

        // Act
        List<FlightResponseDTOImpl> result = flightService.findAllFlights();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(flightRepository, times(1)).findAllFlights();
        verify(flightMapper, times(1)).mapFlightEntitiesToFlightResponseDTOs(flightEntities);
    }

    @Test
    void findAllFlights_ShouldReturnEmptyList_WhenNoFlightsAvailable() {
        // Arrange
        List<FlightEntityImpl> emptyFlightEntities = new ArrayList<>();
        List<FlightResponseDTOImpl> emptyFlightDTOs = new ArrayList<>();

        when(flightRepository.findAllFlights()).thenReturn(emptyFlightEntities);
        when(flightMapper.mapFlightEntitiesToFlightResponseDTOs(emptyFlightEntities)).thenReturn(emptyFlightDTOs);

        // Act
        List<FlightResponseDTOImpl> result = flightService.findAllFlights();

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(flightRepository, times(1)).findAllFlights();
        verify(flightMapper, times(1)).mapFlightEntitiesToFlightResponseDTOs(emptyFlightEntities);
    }
}

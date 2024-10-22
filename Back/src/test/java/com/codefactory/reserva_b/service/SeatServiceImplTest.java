package com.codefactory.reserva_b.service;

import static com.codefactory.reserva_b.Constructors.createSeatResponseDTOImpl;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.codefactory.reserva_b.dto.impl.SeatResponseDTOImpl;
import com.codefactory.reserva_b.entity.impl.SeatEntityImpl;
import com.codefactory.reserva_b.repository.interfaces.ISeatRepository;
import com.codefactory.reserva_b.service.impl.SeatServiceImpl;
import com.codefactory.reserva_b.util.interfaces.ISeatMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

class SeatServiceImplTest {

    @Mock
    private ISeatRepository seatRepository;

    @Mock
    private ISeatMapper seatMapper;

    @InjectMocks
    private SeatServiceImpl seatService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAllAvailableSeats_ShouldReturnSeatResponseDTOs() {
        // Arrange
        String idFlight = "1";
        List<SeatEntityImpl> seatEntities = new ArrayList<>();
        seatEntities.add(new SeatEntityImpl()); // Mock de SeatEntityImpl

        List<SeatResponseDTOImpl> seatDTOs = new ArrayList<>();
        seatDTOs.add(createSeatResponseDTOImpl()); // Usamos el helper para crear SeatResponseDTOImpl

        when(seatRepository.findAllAvailableSeats(new BigInteger(idFlight))).thenReturn(seatEntities);
        when(seatMapper.mapSeatEntitiesToSeatResponseDTOs(seatEntities)).thenReturn(seatDTOs);

        // Act
        List<SeatResponseDTOImpl> result = seatService.findAllAvailableSeats(idFlight);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(seatRepository, times(1)).findAllAvailableSeats(new BigInteger(idFlight));
        verify(seatMapper, times(1)).mapSeatEntitiesToSeatResponseDTOs(seatEntities);
    }

    @Test
    void findAllAvailableSeats_ShouldReturnEmptyList_WhenNoSeatsAvailable() {
        // Arrange
        String idFlight = "1";
        List<SeatEntityImpl> emptySeatEntities = new ArrayList<>();
        List<SeatResponseDTOImpl> emptySeatDTOs = new ArrayList<>();

        when(seatRepository.findAllAvailableSeats(new BigInteger(idFlight))).thenReturn(emptySeatEntities);
        when(seatMapper.mapSeatEntitiesToSeatResponseDTOs(emptySeatEntities)).thenReturn(emptySeatDTOs);

        // Act
        List<SeatResponseDTOImpl> result = seatService.findAllAvailableSeats(idFlight);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(seatRepository, times(1)).findAllAvailableSeats(new BigInteger(idFlight));
        verify(seatMapper, times(1)).mapSeatEntitiesToSeatResponseDTOs(emptySeatEntities);
    }
}

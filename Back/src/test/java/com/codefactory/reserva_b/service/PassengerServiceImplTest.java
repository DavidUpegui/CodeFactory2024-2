package com.codefactory.reserva_b.service;

import static com.codefactory.reserva_b.Constructors.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.codefactory.reserva_b.dto.impl.BookingResponseDTOImpl;
import com.codefactory.reserva_b.dto.impl.PassengerRequestDTOImpl;
import com.codefactory.reserva_b.dto.impl.PassengerResponseDTOImpl;
import com.codefactory.reserva_b.entity.impl.BookingEntityImpl;
import com.codefactory.reserva_b.entity.impl.PassengerEntityImpl;
import com.codefactory.reserva_b.repository.interfaces.IPassengerRepository;
import com.codefactory.reserva_b.service.impl.PassengerServiceImpl;
import com.codefactory.reserva_b.util.interfaces.IBookingMapper;
import com.codefactory.reserva_b.util.interfaces.IPassengerMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

class PassengerServiceImplTest {

    @Mock
    private IPassengerRepository passengerRepository;

    @Mock
    private IPassengerMapper passengerMapper;

    @Mock
    private IBookingMapper bookingMapper;

    @InjectMocks
    private PassengerServiceImpl passengerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findPassengerByIdPassenger_ShouldReturnPassengerResponseDTO() {
        // Arrange
        String idPassenger = "1";
        PassengerEntityImpl passengerEntity = new PassengerEntityImpl(); // Mock de PassengerEntityImpl
        PassengerResponseDTOImpl passengerResponse = createPassengerResponseDTOImpl(); // Usamos el helper

        when(passengerRepository.findPassengerByIdPassenger(new BigInteger(idPassenger))).thenReturn(passengerEntity);
        when(passengerMapper.mapPassengerEntityToPassengerResponseDTO(passengerEntity)).thenReturn(passengerResponse);

        // Act
        PassengerResponseDTOImpl result = passengerService.findPassengerByIdPassenger(idPassenger);

        // Assert
        assertNotNull(result);
        verify(passengerRepository, times(1)).findPassengerByIdPassenger(new BigInteger(idPassenger));
        verify(passengerMapper, times(1)).mapPassengerEntityToPassengerResponseDTO(passengerEntity);
    }

    @Test
    void findPassengersByIdBooking_ShouldReturnListOfPassengerResponseDTOs() {
        // Arrange
        String idBooking = "1";
        List<PassengerEntityImpl> passengerEntities = new ArrayList<>();
        passengerEntities.add(new PassengerEntityImpl()); // Mock de PassengerEntityImpl

        List<PassengerResponseDTOImpl> passengerDTOs = new ArrayList<>();
        passengerDTOs.add(createPassengerResponseDTOImpl()); // Usamos el helper

        when(passengerRepository.findPassengersByIdBooking(new BigInteger(idBooking))).thenReturn(passengerEntities);
        when(passengerMapper.mapPassengerEntitiesToPassengerResponseDTOs(passengerEntities)).thenReturn(passengerDTOs);

        // Act
        List<PassengerResponseDTOImpl> result = passengerService.findPassengersByIdBooking(idBooking);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(passengerRepository, times(1)).findPassengersByIdBooking(new BigInteger(idBooking));
        verify(passengerMapper, times(1)).mapPassengerEntitiesToPassengerResponseDTOs(passengerEntities);
    }

    @Test
    void addPassenger_ShouldReturnPassengerResponseDTO() {
        // Arrange
        PassengerRequestDTOImpl passengerRequest = createPassengerRequestDTOImpl(1L, "John", "Doe", "1990-01-01",
                "123456789", "P12345", "American", "Vegetarian meal", true); // Usamos el helper con parámetros
        String idBooking = "1";
        PassengerEntityImpl newPassengerEntity = new PassengerEntityImpl(); // Mock de PassengerEntityImpl
        PassengerResponseDTOImpl passengerResponse = createPassengerResponseDTOImpl(); // Usamos el helper

        when(passengerRepository.createPassenger(passengerRequest, new BigInteger(idBooking))).thenReturn(newPassengerEntity);
        when(passengerMapper.mapPassengerEntityToPassengerResponseDTO(newPassengerEntity)).thenReturn(passengerResponse);

        // Act
        PassengerResponseDTOImpl result = passengerService.addPassenger(passengerRequest, idBooking);

        // Assert
        assertNotNull(result);
        verify(passengerRepository, times(1)).createPassenger(passengerRequest, new BigInteger(idBooking));
        verify(passengerMapper, times(1)).mapPassengerEntityToPassengerResponseDTO(newPassengerEntity);
    }

    @Test
    void deletePassenger_ShouldReturnBookingResponseDTO() {
        // Arrange
        String idPassenger = "1";
        String idBooking = "1";
        BookingEntityImpl bookingEntity = new BookingEntityImpl(); // Mock de BookingEntityImpl
        BookingResponseDTOImpl bookingResponse = createBookingResponseDTOImpl(); // Usamos el helper

        when(passengerRepository.deletePassenger(new BigInteger(idPassenger), new BigInteger(idBooking))).thenReturn(bookingEntity);
        when(bookingMapper.mapBookingEntityToBookingResponseDTO(bookingEntity)).thenReturn(bookingResponse);

        // Act
        BookingResponseDTOImpl result = passengerService.deletePassenger(idPassenger, idBooking);

        // Assert
        assertNotNull(result);
        verify(passengerRepository, times(1)).deletePassenger(new BigInteger(idPassenger), new BigInteger(idBooking));
        verify(bookingMapper, times(1)).mapBookingEntityToBookingResponseDTO(bookingEntity);
    }

    @Test
    void editPassengerSeat_ShouldReturnUpdatedPassengerResponseDTO() {
        // Arrange
        String idPassenger = "1";
        String idSeat = "2";
        PassengerEntityImpl updatedPassengerEntity = new PassengerEntityImpl(); // Mock de PassengerEntityImpl
        PassengerResponseDTOImpl updatedPassengerResponse = createPassengerResponseDTOImpl(); // Usamos el helper

        when(passengerRepository.editPassengerSeat(new BigInteger(idPassenger), new BigInteger(idSeat))).thenReturn(updatedPassengerEntity);
        when(passengerMapper.mapPassengerEntityToPassengerResponseDTO(updatedPassengerEntity)).thenReturn(updatedPassengerResponse);

        // Act
        PassengerResponseDTOImpl result = passengerService.editPassengerSeat(idPassenger, idSeat);

        // Assert
        assertNotNull(result);
        verify(passengerRepository, times(1)).editPassengerSeat(new BigInteger(idPassenger), new BigInteger(idSeat));
        verify(passengerMapper, times(1)).mapPassengerEntityToPassengerResponseDTO(updatedPassengerEntity);
    }

    @Test
    void editPassengerInfo_ShouldReturnUpdatedPassengerResponseDTO() {
        // Arrange
        String idPassenger = "1";
        PassengerRequestDTOImpl passengerRequest = createPassengerRequestDTOImpl(1L, "John", "Doe", "1990-01-01",
                "123456789", "P12345", "American", "Vegetarian meal", true); // Usamos el helper con parámetros
        PassengerEntityImpl updatedPassengerEntity = new PassengerEntityImpl(); // Mock de PassengerEntityImpl
        PassengerResponseDTOImpl updatedPassengerResponse = createPassengerResponseDTOImpl(); // Usamos el helper

        when(passengerRepository.editPassengerInfo(new BigInteger(idPassenger), passengerRequest)).thenReturn(updatedPassengerEntity);
        when(passengerMapper.mapPassengerEntityToPassengerResponseDTO(updatedPassengerEntity)).thenReturn(updatedPassengerResponse);
    }
}

package com.codefactory.reserva_b.service;

import com.codefactory.reserva_b.dto.impl.*;
import com.codefactory.reserva_b.entity.impl.BookingEntityImpl;
import com.codefactory.reserva_b.repository.interfaces.IBookingRepository;
import com.codefactory.reserva_b.service.impl.BookingServiceImpl;
import com.codefactory.reserva_b.util.interfaces.IBookingMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.codefactory.reserva_b.Constructors.createBookingRequestDTOImpl;
import static com.codefactory.reserva_b.Constructors.createBookingResponseDTOImpl;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class BookingServiceImplTest {
    @Mock
    private IBookingRepository bookingRepository;
    @Mock
    private IBookingMapper bookingMapper;

    @InjectMocks
    private BookingServiceImpl bookingService;



    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAllBookings_ShouldReturnBookingResponseDTOs() {
        // Arrange
        List<BookingEntityImpl> bookings = new ArrayList<>();
        bookings.add(new BookingEntityImpl()); // Mocked Booking Entity

        List<BookingResponseDTOImpl> bookingDTOs = new ArrayList<>();
        bookingDTOs.add(createBookingResponseDTOImpl());

        when(bookingRepository.findAllBookings()).thenReturn(bookings);
        when(bookingMapper.mapBookingEntitiesToBookingResponseDTOs(bookings)).thenReturn(bookingDTOs);

        // Act
        List<BookingResponseDTOImpl> result = bookingService.findAllBookings();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(bookingRepository, times(1)).findAllBookings();
        verify(bookingMapper, times(1)).mapBookingEntitiesToBookingResponseDTOs(bookings);
    }

    @Test
    void findBookingsByIdUser_ShouldReturnBookingResponseDTOs() {
        // Arrange
        String idUser = "123";
        BigInteger bigIntegerIdUser = new BigInteger(idUser);

        List<BookingEntityImpl> bookings = new ArrayList<>();
        bookings.add(new BookingEntityImpl()); // Mocked Booking Entity

        List<BookingResponseDTOImpl> bookingDTOs = new ArrayList<>();
        bookingDTOs.add(createBookingResponseDTOImpl());

        when(bookingRepository.findBookingsByIdUser(bigIntegerIdUser)).thenReturn(bookings);
        when(bookingMapper.mapBookingEntitiesToBookingResponseDTOs(bookings)).thenReturn(bookingDTOs);

        // Act
        List<BookingResponseDTOImpl> result = bookingService.findBookingsByIdUser(idUser);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(bookingRepository, times(1)).findBookingsByIdUser(bigIntegerIdUser);
        verify(bookingMapper, times(1)).mapBookingEntitiesToBookingResponseDTOs(bookings);
    }

    @Test
    void findBookingByIdBooking_ShouldReturnBookingResponseDTO() {
        // Arrange
        String idBooking = "456";
        BigInteger bigIntegerIdBooking = new BigInteger(idBooking);

        BookingEntityImpl booking = new BookingEntityImpl(); // Mocked Booking Entity
        BookingResponseDTOImpl bookingDTO = createBookingResponseDTOImpl();

        when(bookingRepository.findBookingByIdBooking(bigIntegerIdBooking)).thenReturn(booking);
        when(bookingMapper.mapBookingEntityToBookingResponseDTO(booking)).thenReturn(bookingDTO);

        // Act
        BookingResponseDTOImpl result = bookingService.findBookingByIdBooking(idBooking);

        // Assert
        assertNotNull(result);
        verify(bookingRepository, times(1)).findBookingByIdBooking(bigIntegerIdBooking);
        verify(bookingMapper, times(1)).mapBookingEntityToBookingResponseDTO(booking);
    }

    @Test
    void addBooking_ShouldReturnAddedBookingResponseDTO() {
        // Arrange
        BookingRequestDTOImpl bookingRequest = createBookingRequestDTOImpl(); // Mocked Booking Request
        BookingEntityImpl newBooking = new BookingEntityImpl(); // Mocked Booking Entity
        BookingResponseDTOImpl bookingDTO = createBookingResponseDTOImpl();

        when(bookingRepository.createBooking(bookingRequest)).thenReturn(newBooking);
        when(bookingMapper.mapBookingEntityToBookingResponseDTO(newBooking)).thenReturn(bookingDTO);

        // Act
        BookingResponseDTOImpl result = bookingService.addBooking(bookingRequest);

        // Assert
        assertNotNull(result);
        verify(bookingRepository, times(1)).createBooking(bookingRequest);
        verify(bookingMapper, times(1)).mapBookingEntityToBookingResponseDTO(newBooking);
    }

    @Test
    void deleteBooking_ShouldReturnUpdatedBookingList() {
        // Arrange
        String idBooking = "789";
        BigInteger bigIntegerIdBooking = new BigInteger(idBooking);

        List<BookingEntityImpl> bookings = new ArrayList<>();
        bookings.add(new BookingEntityImpl()); // Mocked Booking Entity

        List<BookingResponseDTOImpl> bookingDTOs = new ArrayList<>();
        bookingDTOs.add(createBookingResponseDTOImpl());

        when(bookingRepository.deleteBooking(bigIntegerIdBooking)).thenReturn(bookings);
        when(bookingMapper.mapBookingEntitiesToBookingResponseDTOs(bookings)).thenReturn(bookingDTOs);

        // Act
        List<BookingResponseDTOImpl> result = bookingService.deleteBooking(idBooking);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(bookingRepository, times(1)).deleteBooking(bigIntegerIdBooking);
        verify(bookingMapper, times(1)).mapBookingEntitiesToBookingResponseDTOs(bookings);
    }

    @Test
    void editBookingStatus_ShouldReturnUpdatedBookingResponseDTO() {
        // Arrange
        String bookingStatus = "Confirmed";
        String idBooking = "456";
        BigInteger bigIntegerIdBooking = new BigInteger(idBooking);

        BookingEntityImpl updatedBooking = new BookingEntityImpl(); // Mocked Booking Entity
        BookingResponseDTOImpl bookingDTO = createBookingResponseDTOImpl();

        when(bookingRepository.editBookingStatus(bookingStatus, bigIntegerIdBooking)).thenReturn(updatedBooking);
        when(bookingMapper.mapBookingEntityToBookingResponseDTO(updatedBooking)).thenReturn(bookingDTO);

        // Act
        BookingResponseDTOImpl result = bookingService.editBookingStatus(bookingStatus, idBooking);

        // Assert
        assertNotNull(result);
        verify(bookingRepository, times(1)).editBookingStatus(bookingStatus, bigIntegerIdBooking);
        verify(bookingMapper, times(1)).mapBookingEntityToBookingResponseDTO(updatedBooking);
    }
}

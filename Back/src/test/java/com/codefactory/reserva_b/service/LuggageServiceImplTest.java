package com.codefactory.reserva_b.service;

import static com.codefactory.reserva_b.Constructors.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.codefactory.reserva_b.dto.impl.LuggageRequestDTOImpl;
import com.codefactory.reserva_b.dto.impl.LuggageResponseDTOImpl;
import com.codefactory.reserva_b.dto.impl.PassengerResponseDTOImpl;
import com.codefactory.reserva_b.entity.impl.LuggageEntityImpl;
import com.codefactory.reserva_b.entity.impl.PassengerEntityImpl;
import com.codefactory.reserva_b.repository.interfaces.ILuggageRepository;
import com.codefactory.reserva_b.service.impl.LuggageServiceImpl;
import com.codefactory.reserva_b.util.interfaces.ILuggageMapper;
import com.codefactory.reserva_b.util.interfaces.IPassengerMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigInteger;

class LuggageServiceImplTest {

    @Mock
    private ILuggageRepository luggageRepository;

    @Mock
    private ILuggageMapper luggageMapper;

    @Mock
    private IPassengerMapper passengerMapper;

    @InjectMocks
    private LuggageServiceImpl luggageService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addLuggage_ShouldReturnLuggageResponseDTO() {
        // Arrange (Todo lo que se necesita para que el test funcione)
        LuggageRequestDTOImpl luggageRequest = createLuggageRequestDTO(); // Petición a la base de datos para que se cree
        LuggageEntityImpl luggageEntity = new LuggageEntityImpl(); // Objeto equipaje
        LuggageResponseDTOImpl luggageResponse = createLuggageResponseDTOImpl(); // Respuesta de la petición

        when(luggageRepository.createLuggage(luggageRequest)).thenReturn(luggageEntity); // Como si hubiera ido a bases de datos pero NO
        when(luggageMapper.mapLuggageEntityToLuggageResponseDTO(luggageEntity)).thenReturn(luggageResponse);

        // Act (Ejecutar los algoritmos que se quieren ejecutar)
        LuggageResponseDTOImpl result = luggageService.addLuggage(luggageRequest); // Método que queremos testear

        // Assert (Revisar o verificar el resultado de los algoritmos)
        assertNotNull(result); // Que result no sea Vacio o Nulo
        verify(luggageRepository, times(1)).createLuggage(luggageRequest); // Verificar que ese Mock se haya llamado una sola vez
        verify(luggageMapper, times(1)).mapLuggageEntityToLuggageResponseDTO(luggageEntity); //Todo haya salido bien
    }

    @Test
    void deleteLuggage_ShouldReturnPassengerResponseDTO() {
        // Arrange
        String idPassenger = "1";
        String idLuggage = "1";
        PassengerEntityImpl passengerEntity = new PassengerEntityImpl(); // Mock de PassengerEntityImpl
        PassengerResponseDTOImpl passengerResponse = createPassengerResponseDTOImpl(); // Usamos el helper

        when(luggageRepository.deleteLuggage(new BigInteger(idPassenger), new BigInteger(idLuggage))).thenReturn(passengerEntity);
        when(passengerMapper.mapPassengerEntityToPassengerResponseDTO(passengerEntity)).thenReturn(passengerResponse);

        // Act
        PassengerResponseDTOImpl result = luggageService.deleteLuggage(idPassenger, idLuggage);

        // Assert
        assertNotNull(result);
        verify(luggageRepository, times(1)).deleteLuggage(new BigInteger(idPassenger), new BigInteger(idLuggage));
        verify(passengerMapper, times(1)).mapPassengerEntityToPassengerResponseDTO(passengerEntity);
    }

    @Test
    void editLuggage_ShouldReturnUpdatedLuggageResponseDTO() {
        // Arrange
        String idLuggage = "1";
        LuggageRequestDTOImpl luggageRequest = createLuggageRequestDTO(); // Usamos el helper
        LuggageEntityImpl luggageEntity = new LuggageEntityImpl(); // Mock de LuggageEntityImpl
        LuggageResponseDTOImpl luggageResponse = createLuggageResponseDTOImpl(); // Usamos el helper

        when(luggageRepository.editLuggage(new BigInteger(idLuggage), luggageRequest)).thenReturn(luggageEntity);
        when(luggageMapper.mapLuggageEntityToLuggageResponseDTO(luggageEntity)).thenReturn(luggageResponse);

        // Act
        LuggageResponseDTOImpl result = luggageService.editLuggage(idLuggage, luggageRequest);

        // Assert
        assertNotNull(result);
        verify(luggageRepository, times(1)).editLuggage(new BigInteger(idLuggage), luggageRequest);
        verify(luggageMapper, times(1)).mapLuggageEntityToLuggageResponseDTO(luggageEntity);
    }
}
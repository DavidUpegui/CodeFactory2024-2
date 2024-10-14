package com.udea.vueloudea;

import com.udea.vueloudea.controller.AirplaneTypeController;
import com.udea.vueloudea.model.AirplaneType;
import com.udea.vueloudea.service.AirplaneTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AirplaneTypeControllerTests {

    @Mock
    private AirplaneTypeService airplaneTypeService;

    @InjectMocks
    private AirplaneTypeController airplaneTypeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllAirplaneTypes() {
        AirplaneType airplaneType1 = new AirplaneType();
        AirplaneType airplaneType2 = new AirplaneType();
        List<AirplaneType> airplaneTypes = Arrays.asList(airplaneType1, airplaneType2);

        when(airplaneTypeService.getAllAirplaneTypes()).thenReturn(airplaneTypes);

        List<AirplaneType> result = airplaneTypeController.getAllAirplaneTypes();
        assertEquals(2, result.size());
    }

    @Test
    void testGetAirplaneTypeById() {
        AirplaneType airplaneType = new AirplaneType();
        when(airplaneTypeService.getAirplaneTypeById("1")).thenReturn(airplaneType);

        AirplaneType result = airplaneTypeController.getAirplaneTypeById("1");
        assertEquals(airplaneType, result);
    }
}
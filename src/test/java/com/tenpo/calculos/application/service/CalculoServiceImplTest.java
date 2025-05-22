package com.tenpo.calculos.application.service;

import com.tenpo.calculos.application.dto.response.*;
import com.tenpo.calculos.application.port.output.PorcentajeDb;
import com.tenpo.calculos.application.port.repository.cache.PorcentajeResponseCache;
import com.tenpo.calculos.domain.Porcentaje;
import com.tenpo.calculos.domain.constant.ResponseCodes;
import com.tenpo.calculos.domain.exception.ApplicationException;
import com.tenpo.calculos.domain.port.out.PorcentajeRestClientPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CalculoServiceImplTest {

    @InjectMocks
    private CalculoServiceImpl calculoService;

    @Mock
    private PorcentajeRestClientPort restClientPort;

    @Mock
    private PorcentajeResponseCache porcentajeResponseCache;

    @Mock
    private PorcentajeDb porcentajeDb;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        setPrivateField(calculoService, "keyPorcentaje", "porcentaje");
        setPrivateField(calculoService, "duracion", 10);

    }


    private void setPrivateField(Object target, String fieldName, Object value) {
        try {
            java.lang.reflect.Field field = target.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(target, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    void testCalcular_conCache() {
        BigDecimal num1 = BigDecimal.TEN;
        BigDecimal num2 = BigDecimal.TEN;
        BigDecimal porcentaje = BigDecimal.valueOf(20);

        when(porcentajeResponseCache.obtenerPorcentaje("porcentaje")).thenReturn(Optional.of(porcentaje));

        ResponseDTO response = calculoService.calcular(num1, num2);

        assertNotNull(response);
        assertEquals("OK", response.getMetadata().getMensaje());
        assertTrue(response.getInfo().getResultado().compareTo(new BigDecimal("24.0000")) == 0);
        assertTrue(response.getInfo().getUsandoCache());
    }

    @Test
    void testCalcular_sinCache() {
        BigDecimal num1 = BigDecimal.TEN;
        BigDecimal num2 = BigDecimal.TEN;
        BigDecimal porcentaje = BigDecimal.valueOf(10);

        when(porcentajeResponseCache.obtenerPorcentaje("porcentaje")).thenReturn(Optional.empty());
        Porcentaje porcentajePOJO = new Porcentaje();
        porcentajePOJO.setPorcentaje(porcentaje);
        when(restClientPort.obtenerPorcentaje()).thenReturn(Mono.just(porcentajePOJO));

        ResponseDTO response = calculoService.calcular(num1, num2);

        assertNotNull(response);
        assertEquals("OK", response.getMetadata().getMensaje());
        assertTrue(response.getInfo().getResultado().compareTo(new BigDecimal("22.0000")) == 0);
        assertFalse(response.getInfo().getUsandoCache());

        verify(porcentajeResponseCache).guardarPorcentaje("porcentaje", porcentaje, 10);
    }

    @Test
    void testCalcular_errorServicioExterno() {
        BigDecimal num1 = BigDecimal.ONE;
        BigDecimal num2 = BigDecimal.ONE;

        when(porcentajeResponseCache.obtenerPorcentaje("porcentaje")).thenReturn(Optional.empty());
        when(restClientPort.obtenerPorcentaje()).thenReturn(Mono.error(
                new ApplicationException("Error", "ERR", HttpStatus.BAD_GATEWAY)));

        ApplicationException ex = assertThrows(ApplicationException.class, () ->
                calculoService.calcular(num1, num2));

        assertEquals(ResponseCodes.SERVICIO_EXTERNO_FALLIDO_CODE, ex.getCodigoError());
    }

    @Test
    void testObtenerHistorial_conResultados() {
        OperacionDTO operacion = new OperacionDTO();
        when(porcentajeDb.obtenerHistorialLlamadas(1, 10)).thenReturn(List.of(operacion));

        HistorialResponseDTO response = calculoService.obtenerHistorial(1, 10);

        assertNotNull(response);
        assertEquals("OK", response.getMetadata().getMensaje());
        assertEquals(1, response.getHistorial().size());
    }

    @Test
    void testObtenerHistorial_sinResultados() {
        when(porcentajeDb.obtenerHistorialLlamadas(1, 10)).thenReturn(Collections.emptyList());

        HistorialResponseDTO response = calculoService.obtenerHistorial(1, 10);

        assertNotNull(response);
        assertEquals("No se encontraron resultados", response.getMetadata().getMensaje());
        assertTrue(response.getHistorial() == null || response.getHistorial().isEmpty());
    }
}

package com.tenpo.calculos.infrastructure.repository.db;

import com.tenpo.calculos.application.dto.response.OperacionDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PorcentajeDbImplTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private PorcentajeDbImpl porcentajeDb;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGuardarLlamada_exito() {
        // Arrange
        BigDecimal num1 = BigDecimal.valueOf(10);
        BigDecimal num2 = BigDecimal.valueOf(5);
        BigDecimal porcentaje = BigDecimal.valueOf(20);
        BigDecimal resultado = BigDecimal.valueOf(12);
        Boolean usandoCache = false;

        porcentajeDb.guardarLlamada(num1, num2, porcentaje, usandoCache, resultado);
        verify(jdbcTemplate, times(1)).update(anyString(), any(), any(), any(), any(), any(), any());
    }

    @Test
    void testGuardarLlamada_lanzaExcepcion() {
        BigDecimal num1 = BigDecimal.ONE;
        BigDecimal num2 = BigDecimal.TEN;
        BigDecimal porcentaje = BigDecimal.ZERO;
        BigDecimal resultado = BigDecimal.TEN;
        Boolean usandoCache = true;

        doThrow(new DataAccessException("Error") {}).when(jdbcTemplate).update(anyString(), any(), any(), any(), any(), any(), any());

        assertThrows(DataAccessException.class, () ->
                porcentajeDb.guardarLlamada(num1, num2, porcentaje, usandoCache, resultado));
    }

    @Test
    void testObtenerHistorialLlamadas_exito() {
        int page = 1;
        int size = 10;
        List<OperacionDTO> expected = List.of(new OperacionDTO());

        when(jdbcTemplate.query(anyString(), any(BeanPropertyRowMapper.class), eq(size), eq(0)))
                .thenReturn(expected);

        List<OperacionDTO> result = porcentajeDb.obtenerHistorialLlamadas(page, size);

        assertEquals(expected, result);
    }

    @Test
    void testObtenerHistorialLlamadas_sinResultados() {
        // Arrange
        when(jdbcTemplate.query(anyString(), any(BeanPropertyRowMapper.class), anyInt(), anyInt()))
                .thenThrow(new EmptyResultDataAccessException(1));

        List<OperacionDTO> result = porcentajeDb.obtenerHistorialLlamadas(1, 10);
        assertTrue(result.isEmpty());
    }

    @Test
    void testObtenerHistorialLlamadas_lanzaExcepcion() {
        when(jdbcTemplate.query(anyString(), any(BeanPropertyRowMapper.class), anyInt(), anyInt()))
                .thenThrow(new RuntimeException("Error inesperado"));
        assertThrows(RuntimeException.class, () -> porcentajeDb.obtenerHistorialLlamadas(1, 10));
    }
}


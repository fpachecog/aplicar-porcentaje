package com.tenpo.calculos.infrastructure.repository.db;

import com.tenpo.calculos.application.dto.response.OperacionDTO;
import com.tenpo.calculos.application.port.output.PorcentajeDb;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@Transactional(rollbackFor = Exception.class)
public class PorcentajeDbImpl implements PorcentajeDb {

    private final JdbcTemplate jdbcTemplate;

    public PorcentajeDbImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }


    private static final Logger log = LoggerFactory.getLogger(PorcentajeDbImpl.class);

    @Override
    @Async
    public void guardarLlamada(BigDecimal num1, BigDecimal num2, BigDecimal porcentajeAplicado, Boolean usandoCache, BigDecimal resultado) {
        log.info("Insertando en tabla operaciones");
        String query = "INSERT INTO operaciones(" +
                "num1, num2, porcentaje_aplicado, usando_cache, resultado, fecha) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try {
            jdbcTemplate.update(
                    query,
                    num1,
                    num2,
                    porcentajeAplicado,
                    usandoCache,
                    resultado,
                    new Timestamp(new Date().getTime())
            );

            log.info("insert correcto");
        }
        catch (DataAccessException e) {
            log.error("Error en INSERT con retorno de ID: {}", e.getMessage(), e);
            throw e;
        }

    }

    @Override
    public List<OperacionDTO> obtenerHistorialLlamadas(Integer page, Integer size) {
        log.info("realizando consulta a tabla operaciones");
        String query = "select id, num1, num2, porcentaje_aplicado, usando_cache, resultado, fecha from operaciones " +
                "ORDER BY fecha DESC " +
                "LIMIT ? OFFSET ?";

        int offset = (page - 1) * size;

        try {
            return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(OperacionDTO.class),
                    size, offset);
        } catch (EmptyResultDataAccessException e) {
            log.info("No se encontraron registros");
            return Collections.emptyList();
        } catch (Exception e) {
            log.error("Error al obtener el historial {}",e.getMessage());
            throw e;
        }

    }
}

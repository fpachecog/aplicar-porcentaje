package com.tenpo.calculos.application.port.repository.cache;

import java.math.BigDecimal;
import java.util.Optional;

public interface PorcentajeResponseCache {
    Optional<BigDecimal> obtenerPorcentaje(String key);
    void guardarPorcentaje(String key, BigDecimal porcentaje, int expiration);
}

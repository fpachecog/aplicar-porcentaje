package com.tenpo.calculos.application.port.output;

import com.tenpo.calculos.application.dto.response.OperacionDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface PorcentajeDb {

    void guardarLlamada(BigDecimal num1, BigDecimal num2, BigDecimal porcentajeAplicado, Boolean usandoCache, BigDecimal resultado);
    List<OperacionDTO> obtenerHistorialLlamadas(Integer page, Integer size);
}

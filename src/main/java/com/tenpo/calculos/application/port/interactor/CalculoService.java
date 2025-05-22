package com.tenpo.calculos.application.port.interactor;

import com.tenpo.calculos.application.dto.response.HistorialResponseDTO;
import com.tenpo.calculos.application.dto.response.ResponseDTO;

import java.math.BigDecimal;

public interface CalculoService {
    public ResponseDTO calcular(BigDecimal num1, BigDecimal num2);
    public HistorialResponseDTO obtenerHistorial(Integer page, Integer size);
}

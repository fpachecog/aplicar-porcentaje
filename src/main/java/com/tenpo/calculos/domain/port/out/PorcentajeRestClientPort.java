package com.tenpo.calculos.domain.port.out;

import com.tenpo.calculos.domain.Porcentaje;
import reactor.core.publisher.Mono;

public interface PorcentajeRestClientPort {
    Mono<Porcentaje> obtenerPorcentaje();
}

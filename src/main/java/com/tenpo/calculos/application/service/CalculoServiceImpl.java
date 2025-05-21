package com.tenpo.calculos.application.service;

import com.tenpo.calculos.application.dto.response.InfoDTO;
import com.tenpo.calculos.application.dto.response.MetadataDTO;
import com.tenpo.calculos.application.dto.response.ResponseDTO;
import com.tenpo.calculos.application.port.repository.cache.PorcentajeResponseCache;
import com.tenpo.calculos.domain.constant.ResponseCodes;
import com.tenpo.calculos.domain.exception.ApplicationException;
import com.tenpo.calculos.application.port.interactor.CalculoService;
import com.tenpo.calculos.domain.Porcentaje;
import com.tenpo.calculos.domain.port.out.PorcentajeRestClientPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;


@Service
public class CalculoServiceImpl implements CalculoService {

    private static final Logger logger = LoggerFactory.getLogger(CalculoServiceImpl.class);

    @Autowired
    private PorcentajeRestClientPort restClientPort;

    @Autowired
    private PorcentajeResponseCache porcentajeResponseCache;

    @Value("${cache.porcentaje.key}")
    private String keyPorcentaje;

    @Value("${cache.porcentaje.duracion}")
    private Integer duracion;


    @Override
    public ResponseDTO calcular(BigDecimal num1, BigDecimal num2) {
        BigDecimal suma = num1.add(num2);
        logger.info("Suma inicial de números: {}", suma);
        Optional<BigDecimal> porcentajeOpt = porcentajeResponseCache.obtenerPorcentaje(keyPorcentaje);

        final BigDecimal[] porcentaje = new BigDecimal[1];
        AtomicReference<Boolean> usandoCache = new AtomicReference<>(false);

        porcentajeOpt.ifPresentOrElse(
                p -> {
                    porcentaje[0] = p;
                    usandoCache.set(true);
                },
                () -> {
                    porcentaje[0] = obtenerPorcentajeExterno();
                    porcentajeResponseCache.guardarPorcentaje(keyPorcentaje, porcentaje[0], duracion);
                }
        );

        BigDecimal montoSumado = suma.multiply(porcentaje[0]).divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);
        BigDecimal valorFinal = suma.add(montoSumado);
        return generarRespuestaFinal(valorFinal, montoSumado, porcentaje[0], usandoCache.get());

    }

    private ResponseDTO generarRespuestaFinal(BigDecimal valorFinal, BigDecimal montoSumado, BigDecimal porcentajeAplicado, Boolean usandoCache){

        InfoDTO infoDTO = new InfoDTO();
        infoDTO.setResultado(valorFinal);
        infoDTO.setPorcentajeAplicado(porcentajeAplicado);
        infoDTO.setMontoSumado(montoSumado);
        infoDTO.setUsandoCache(usandoCache);

        MetadataDTO metadataDTO = new MetadataDTO();
        metadataDTO.setCodigo(ResponseCodes.OK_CODE);
        metadataDTO.setMensaje("OK");
        metadataDTO.setTimestamp(new Date());

        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setInfo(infoDTO);
        responseDTO.setMetadata(metadataDTO);

        return responseDTO;
    }


    private BigDecimal obtenerPorcentajeExterno() {
        return restClientPort.obtenerPorcentaje()
                .map(Porcentaje::getPorcentaje)
                .onErrorResume(WebClientRequestException.class, ex -> {
                    logger.error("Error de red al invocar el servicio externo", ex);
                    return Mono.error(new ApplicationException(
                            "No se pudo conectar al servicio externo", ResponseCodes.SERVICIO_EXTERNO_FALLIDO_CODE, HttpStatus.BAD_GATEWAY));
                })
                .onErrorResume(WebClientResponseException.class, ex -> {
                    logger.error("Error HTTP al obtener el porcentaje", ex);
                    return Mono.error(new ApplicationException(
                            "Error HTTP: "+ex.getMessage(), ResponseCodes.SERVICIO_EXTERNO_FALLIDO_CODE, HttpStatus.BAD_GATEWAY));
                })
                .onErrorResume(Exception.class, ex -> {
                    logger.error("Error inesperado al obtener el porcentaje", ex);
                    return Mono.error(new ApplicationException(
                            "Error inesperado al obtener el porcentaje", ResponseCodes.SERVICIO_EXTERNO_FALLIDO_CODE, HttpStatus.BAD_GATEWAY));
                })
                .block();
    }

}

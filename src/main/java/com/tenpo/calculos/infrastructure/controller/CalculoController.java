package com.tenpo.calculos.infrastructure.controller;

import com.tenpo.calculos.application.dto.request.RequestDTO;
import com.tenpo.calculos.application.dto.response.HistorialResponseDTO;
import com.tenpo.calculos.application.dto.response.ResponseDTO;
import com.tenpo.calculos.application.port.interactor.CalculoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/v1/calculos")
public class CalculoController {

    private final CalculoService calculoService;

    private static final Logger log = LoggerFactory.getLogger(CalculoController.class);

    public CalculoController(CalculoService calculoService){
        this.calculoService = calculoService;
    }

    @PostMapping("/porcentaje")
    public ResponseEntity<ResponseDTO> calcular(@RequestBody @Valid RequestDTO request){
        log.info("Iniciando operacion calcular con parametros num1: {} y num2: {} ",request.getNum1(), request.getNum2());
        return new ResponseEntity<>(calculoService.calcular(request.getNum1(), request.getNum2()),
                HttpStatus.OK);
    }


//    HistorialResponseDTO

    @GetMapping("/historial")
    public ResponseEntity<HistorialResponseDTO> obtenerHistorial(
            @RequestParam @NotNull @Min(1) Integer page,
            @RequestParam @NotNull @Min(1) Integer size
    ){
        log.info("Iniciando operacion obtenerHistorial con parametros page: {} y size: {} ",page, size);
        HistorialResponseDTO responseDTO = calculoService.obtenerHistorial(page, size);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }


}

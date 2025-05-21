package com.tenpo.calculos.infrastructure.exception;

import com.tenpo.calculos.application.dto.response.MetadataDTO;
import com.tenpo.calculos.application.dto.response.ResponseDTO;
import com.tenpo.calculos.application.dto.response.RespuestaTokenDTO;
import com.tenpo.calculos.domain.exception.ApplicationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ResponseDTO> handleWebClientResponseException(ApplicationException ex){
        ResponseDTO responseDTO = new ResponseDTO();
        MetadataDTO metadataDTO = new MetadataDTO();
        metadataDTO.setCodigo(ex.getCodigoError());
        metadataDTO.setMensaje(ex.getMessage());
        metadataDTO.setTimestamp(new Date());
        responseDTO.setMetadata(metadataDTO);

        return new ResponseEntity<>(responseDTO, ex.getHttpStatus());
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<RespuestaTokenDTO> handleException(Exception ex) {
        RespuestaTokenDTO respuestaDTO = new RespuestaTokenDTO();
//        respuestaDTO.setCodigo(CodigosEnum.ERROR_DEL_SERVIDOR.getCode());
        respuestaDTO.setDescripcion(ex.getMessage());
        return new ResponseEntity<>(respuestaDTO, HttpStatus.UNAUTHORIZED);
    }





}

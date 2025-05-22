package com.tenpo.calculos.infrastructure.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.tenpo.calculos.application.dto.response.MetadataDTO;
import com.tenpo.calculos.application.dto.response.ResponseDTO;
import com.tenpo.calculos.application.dto.response.RespuestaTokenDTO;
import com.tenpo.calculos.domain.constant.ResponseCodes;
import com.tenpo.calculos.domain.exception.ApplicationException;
import jakarta.validation.UnexpectedTypeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.time.OffsetDateTime;
import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ResponseDTO> handleWebClientResponseException(ApplicationException ex){
        ResponseDTO responseDTO = new ResponseDTO();
        MetadataDTO metadataDTO = new MetadataDTO();
        metadataDTO.setCodigo(ex.getCodigoError());
        metadataDTO.setMensaje(ex.getMessage());
        metadataDTO.setTimestamp(OffsetDateTime.now());
        responseDTO.setMetadata(metadataDTO);

        return new ResponseEntity<>(responseDTO, ex.getHttpStatus());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResponseDTO> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex){
        ResponseDTO responseDTO = new ResponseDTO();
        MetadataDTO metadataDTO = new MetadataDTO();
        metadataDTO.setCodigo(ResponseCodes.VALIDACION_FALLIDA_CODE);
        metadataDTO.setMensaje("Parametros invalidos o faltantes");
        metadataDTO.setTimestamp(OffsetDateTime.now());
        responseDTO.setMetadata(metadataDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnexpectedTypeException.class)
    public ResponseEntity<ResponseDTO> handleUnexpectedTypeException(UnexpectedTypeException ex){
        ResponseDTO responseDTO = new ResponseDTO();
        MetadataDTO metadataDTO = new MetadataDTO();
        metadataDTO.setCodigo(ResponseCodes.VALIDACION_FALLIDA_CODE);
        metadataDTO.setMensaje("Parametros invalidos o faltantes");
        metadataDTO.setTimestamp(OffsetDateTime.now());
        responseDTO.setMetadata(metadataDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        ResponseDTO responseDTO = new ResponseDTO();
        MetadataDTO metadataDTO = new MetadataDTO();
        metadataDTO.setCodigo(ResponseCodes.VALIDACION_FALLIDA_CODE);
        metadataDTO.setMensaje("Parametros invalidos o faltantes");
        metadataDTO.setTimestamp(OffsetDateTime.now());
        responseDTO.setMetadata(metadataDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ResponseDTO> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex){
        ResponseDTO responseDTO = new ResponseDTO();
        MetadataDTO metadataDTO = new MetadataDTO();
        metadataDTO.setCodigo(ResponseCodes.VALIDACION_FALLIDA_CODE);
        metadataDTO.setMensaje("Parametros invalidos o faltantes");
        metadataDTO.setTimestamp(OffsetDateTime.now());
        responseDTO.setMetadata(metadataDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ResponseDTO> handleMissingServletRequestParameterException(MissingServletRequestParameterException ex){
        ResponseDTO responseDTO = new ResponseDTO();
        MetadataDTO metadataDTO = new MetadataDTO();
        metadataDTO.setCodigo(ResponseCodes.VALIDACION_FALLIDA_CODE);
        metadataDTO.setMensaje("Parametros invalidos o faltantes");
        metadataDTO.setTimestamp(OffsetDateTime.now());
        responseDTO.setMetadata(metadataDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDTO> handleException(Exception ex) {
        ResponseDTO responseDTO = new ResponseDTO();
        MetadataDTO metadataDTO = new MetadataDTO();
        metadataDTO.setCodigo("00500");
        metadataDTO.setMensaje(ex.getMessage());
        metadataDTO.setTimestamp(OffsetDateTime.now());
        responseDTO.setMetadata(metadataDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }





}

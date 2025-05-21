package com.tenpo.calculos.infrastructure.repository.cache;

import com.tenpo.calculos.application.port.repository.cache.PorcentajeResponseCache;
import com.tenpo.calculos.domain.constant.ResponseCodes;
import com.tenpo.calculos.domain.exception.ApplicationException;
import com.tenpo.calculos.infrastructurecross.cache.CacheRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;


import java.math.BigDecimal;
import java.util.Optional;

@Repository
public class PorcentajeResponseCacheImpl implements PorcentajeResponseCache {

    private static final Logger logger = LoggerFactory.getLogger(PorcentajeResponseCacheImpl.class);

    @Autowired
    private CacheRepository cacheRepository;

    @Override
    public Optional<BigDecimal> obtenerPorcentaje(String key) {
        try {

            String valuePorcentaje = cacheRepository.obtener(key);
            if(valuePorcentaje != null){
                BigDecimal porcentaje = new BigDecimal(valuePorcentaje);
                return Optional.of(porcentaje);
            }else {
                return Optional.empty();
            }

        }catch (Exception e){
            throw new ApplicationException(e.getMessage(), ResponseCodes.PORCENTAJE_NO_DISPONIBLE_CACHE_CODE, HttpStatus.BAD_GATEWAY);
        }

    }

    @Override
    public void guardarPorcentaje(String key, BigDecimal porcentaje, int expiration) {
        try {
            cacheRepository.guardar(key, String.valueOf(porcentaje), expiration);
        }catch (Exception e){
            throw new ApplicationException(e.getMessage(), ResponseCodes.PORCENTAJE_NO_DISPONIBLE_CACHE_CODE, HttpStatus.BAD_GATEWAY);
        }
    }
}

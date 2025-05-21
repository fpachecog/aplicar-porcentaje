package com.tenpo.calculos.infrastructurecross.cache;

import com.tenpo.calculos.domain.constant.ResponseCodes;
import com.tenpo.calculos.domain.exception.ApplicationException;
import org.springframework.data.redis.core.StringRedisTemplate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
public class CacheRepositoryImpl implements CacheRepository{

    private static final Logger logger = LoggerFactory.getLogger(CacheRepositoryImpl.class);

    private static final String MENSAJE = ". Message ";
    private final StringRedisTemplate template;

    public CacheRepositoryImpl(StringRedisTemplate template){
        this.template = template;
    }

    @Override
    public String obtener(String key) {
        logger.info("obtener valor para key: {}", key);
        try {
            return template.opsForValue().get(key);
        } catch (Exception e) {
            logger.error("Redis server error al obtenerPorDescripcion key {}" + MENSAJE + "{}", key, e.getMessage());
        }
        return null;
    }

    @Override
    public void borrar(String key) {
        try {
            template.opsForValue().getOperations().delete(key);
        } catch (Exception e) {
            logger.error("Redis server error al borrar {}" + MENSAJE + "{}", key, e.getMessage());
        }
    }

    @Override
    public void guardar(String key, String value, int expiration) {
        try {
            template.opsForValue().set(key, value);
            template.expire(key, expiration, TimeUnit.MINUTES);
        } catch (Exception e) {
            logger.error("Redis server error al guardar key {}" + MENSAJE + "{}", key, e.getMessage());
            throw new ApplicationException(e.getMessage(), ResponseCodes.ERROR_AL_GUARDAR_CACHE, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

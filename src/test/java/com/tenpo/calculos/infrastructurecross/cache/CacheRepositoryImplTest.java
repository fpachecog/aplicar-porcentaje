package com.tenpo.calculos.infrastructurecross.cache;

import com.tenpo.calculos.domain.constant.ResponseCodes;
import com.tenpo.calculos.domain.exception.ApplicationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.RedisOperations;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CacheRepositoryImplTest {

    @Mock
    private StringRedisTemplate redisTemplate;

    @Mock
    private ValueOperations<String, String> valueOperations;

    @Mock
    private RedisOperations<String, String> redisOperations;

    @InjectMocks
    private CacheRepositoryImpl cacheRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(redisTemplate.opsForValue()).thenReturn(valueOperations);
        when(valueOperations.getOperations()).thenReturn(redisOperations);
    }

    @Test
    void testObtener() {
        String key = "testKey";
        String expectedValue = "testValue";

        when(valueOperations.get(key)).thenReturn(expectedValue);

        String result = cacheRepository.obtener(key);
        // Valido que el método obtener(String key) devuelva correctamente el valor almacenado en Redis para una clave
        assertEquals(expectedValue, result);
        // Valido que se llame al metodo get() del valueOperations
        verify(valueOperations).get(key);
    }

    @Test
    void testGuardar() {
        String key = "key";
        String value = "value";
        int expiration = 5;

        cacheRepository.guardar(key, value, expiration);

        verify(valueOperations).set(key, value);
        verify(redisTemplate).expire(key, expiration, TimeUnit.MINUTES);
    }

    @Test
    void testGuardarThrowsException() {
        String key = "key";
        String value = "value";
        int expiration = 5;

        doThrow(new RuntimeException("Redis error")).when(valueOperations).set(key, value);

        ApplicationException exception = assertThrows(ApplicationException.class, () ->
                cacheRepository.guardar(key, value, expiration));

        assertEquals(ResponseCodes.ERROR_AL_GUARDAR_CACHE, exception.getCodigoError());
    }

    @Test
    void testBorrar() {
        String key = "key";

        cacheRepository.borrar(key);

        verify(redisOperations).delete(key);
    }
}


package com.tenpo.calculos.infrastructurecross.cache;

public interface CacheRepository {
    String obtener(String key);
    void borrar(String key);
    void guardar(String key, String value, int expiration);
}

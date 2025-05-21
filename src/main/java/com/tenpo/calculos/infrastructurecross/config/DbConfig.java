package com.tenpo.calculos.infrastructurecross.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;

@Configuration
public class DbConfig {

    private static final Logger log = LoggerFactory.getLogger(DbConfig.class);

    @Value("${spring.datasource.hikari.username}")
    private String hikariUsername;

    @Value("${spring.datasource.hikari.host}")
    private String hikariHost;

    @Value("${spring.datasource.hikari.port}")
    private String hikariPort;

    @Value("${spring.datasource.hikari.password}")
    private String hikariPassword;

    @Value("${spring.datasource.hikari.databasename}")
    private String databaseName;

    @Value("${spring.datasource.hikari.connectionTimeout}")
    private Integer hikariConnTimeOut;

    @Value("${spring.datasource.hikari.maxLifetime}")
    private Integer hikariMaxLifeTime;

    @Value("${spring.datasource.hikari.leakDetectionThreshold}")
    private Integer hikariLeakDetectionThreshold;

    @Value("${spring.datasource.hikari.maximumPoolSize}")
    private Integer hikariMaximumPoolSize;

    @Value("${spring.datasource.hikari.connection-init-sql}")
    private String hikariConnInitSql;

    @Bean
    @Primary
    public DataSource readWriteDataSource() {
        DbCredentials credentials = new DbCredentials();

        credentials.setHost(getHikariHost());
        credentials.setPassword(getHikariPassword());
        credentials.setUsername(getHikariUsername());
        credentials.setDbname(getDatabaseName());
        credentials.setPort(getHikariPort());
        credentials.setDbInstanceIdentifier("localhost");

        return buildDataSource(credentials);

    }

    private DataSource buildDataSource(DbCredentials credentials) {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        String url = "jdbc:postgresql://" + credentials.getHost() + ":" + credentials.getPort() + "/" + credentials.getDbname();
        System.out.println("URL conexion: " + url);
        dataSource.setUrl(url);
        dataSource.setUser(credentials.getUsername());
        dataSource.setPassword(credentials.getPassword());

        return connectionPoolDataSource(dataSource);
    }

    protected HikariConfig hikariConfig(DataSource dataSource) {
        try {
            HikariConfig hikariConfig = new HikariConfig();
            hikariConfig.setDataSource(dataSource);
            hikariConfig.setConnectionTimeout(getHikariConnTimeOut());
            hikariConfig.setConnectionInitSql(getHikariConnInitSql());
            hikariConfig.setMaxLifetime(getHikariMaxLifeTime());
            hikariConfig.setLeakDetectionThreshold(getHikariLeakDetectionThreshold());
            hikariConfig.setMaximumPoolSize(getHikariMaximumPoolSize());
            hikariConfig.setAutoCommit(true);
            log.info("Configuración de HikariConfig completada correctamente");
            return hikariConfig;
        } catch (Exception e) {
            log.error("Error al configurar HikariConfig. Revisa los parámetros proporcionados.", e);
            throw new IllegalStateException("No se pudo configurar HikariConfig correctamente", e);
        }
    }


    protected HikariDataSource connectionPoolDataSource(DataSource dataSource) {
        try {
            HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig(dataSource));
            log.info("HikariDataSource inicializado correctamente");
            return hikariDataSource;
        } catch (Exception e) {
            log.error("Error al inicializar HikariDataSource. Verifica la conexión a la base de datos.", e);
            throw new IllegalStateException("No se pudo establecer conexión con la base de datos", e);
        }
    }

    public String getHikariUsername() {
        return hikariUsername;
    }

    public String getHikariHost() {
        return hikariHost;
    }

    public String getHikariPort() {
        return hikariPort;
    }

    public String getHikariPassword() {
        return hikariPassword;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public Integer getHikariConnTimeOut() {
        return hikariConnTimeOut;
    }

    public Integer getHikariMaxLifeTime() {
        return hikariMaxLifeTime;
    }

    public Integer getHikariLeakDetectionThreshold() {
        return hikariLeakDetectionThreshold;
    }

    public Integer getHikariMaximumPoolSize() {
        return hikariMaximumPoolSize;
    }

    public String getHikariConnInitSql() {
        return hikariConnInitSql;
    }
}

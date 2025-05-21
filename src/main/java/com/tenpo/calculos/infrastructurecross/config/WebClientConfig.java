package com.tenpo.calculos.infrastructurecross.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

import java.time.Duration;

@Configuration
public class WebClientConfig {


    @Value("${app.externo.maxconnections}")
    private Integer maxConnections;

    @Value("${app.externo.timeout}")
    private Integer timeout;

    @Bean
    public WebClient.Builder webClientBuilder() {
        ConnectionProvider provider = ConnectionProvider.builder("fixed")
                .maxConnections(maxConnections)
                .pendingAcquireTimeout(Duration.ofSeconds(timeout))
                .build();

        HttpClient httpClient = HttpClient.create(provider);

        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient));
    }

    @Bean
    public WebClient webClient(WebClient.Builder builder) {
        return builder.build();
    }

}

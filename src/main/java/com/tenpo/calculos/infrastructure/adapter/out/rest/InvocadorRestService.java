package com.tenpo.calculos.infrastructure.adapter.out.rest;

import com.tenpo.calculos.domain.Porcentaje;
import com.tenpo.calculos.domain.port.out.PorcentajeRestClientPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class InvocadorRestService implements PorcentajeRestClientPort {

    private final WebClient webClient;

    @Value("${app.externo.url}")
    private String urlServicioExterno;

    public InvocadorRestService(WebClient webClient){
        this.webClient = webClient;
    }

    @Override
    public Mono<Porcentaje> obtenerPorcentaje() {
        return invocarGet(urlServicioExterno, Porcentaje.class);
    }

    public <T> Mono<T> invocarGet(String url, Class<T> responseType) {
        return webClient.get()
                .uri(url)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(responseType);
    }
}

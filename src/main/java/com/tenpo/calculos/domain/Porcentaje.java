package com.tenpo.calculos.domain;


import java.math.BigDecimal;

public class Porcentaje {
    private BigDecimal porcentaje;

    public BigDecimal getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(BigDecimal porcentaje) {
        this.porcentaje = porcentaje;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Porcentaje{");
        sb.append("porcentaje=").append(porcentaje);
        sb.append('}');
        return sb.toString();
    }
}

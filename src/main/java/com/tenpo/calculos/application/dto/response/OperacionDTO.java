
package com.tenpo.calculos.application.dto.response;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * Root Type for OperacionDTO
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "num1",
    "num2",
    "porcentajeAplicado",
    "usandoCache",
    "resultado",
    "fecha"
})
@Generated("jsonschema2pojo")
public class OperacionDTO {

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("num1")
    private BigDecimal num1;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("num2")
    private BigDecimal num2;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("porcentajeAplicado")
    private BigDecimal porcentajeAplicado;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("usandoCache")
    private Boolean usandoCache;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("resultado")
    private BigDecimal resultado;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("fecha")
    private OffsetDateTime fecha;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("num1")
    public BigDecimal getNum1() {
        return num1;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("num1")
    public void setNum1(BigDecimal num1) {
        this.num1 = num1;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("num2")
    public BigDecimal getNum2() {
        return num2;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("num2")
    public void setNum2(BigDecimal num2) {
        this.num2 = num2;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("porcentajeAplicado")
    public BigDecimal getPorcentajeAplicado() {
        return porcentajeAplicado;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("porcentajeAplicado")
    public void setPorcentajeAplicado(BigDecimal porcentajeAplicado) {
        this.porcentajeAplicado = porcentajeAplicado;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("usandoCache")
    public Boolean getUsandoCache() {
        return usandoCache;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("usandoCache")
    public void setUsandoCache(Boolean usandoCache) {
        this.usandoCache = usandoCache;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("resultado")
    public BigDecimal getResultado() {
        return resultado;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("resultado")
    public void setResultado(BigDecimal resultado) {
        this.resultado = resultado;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("fecha")
    public OffsetDateTime getFecha() {
        return fecha;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("fecha")
    public void setFecha(OffsetDateTime fecha) {
        this.fecha = fecha;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(OperacionDTO.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("num1");
        sb.append('=');
        sb.append(((this.num1 == null)?"<null>":this.num1));
        sb.append(',');
        sb.append("num2");
        sb.append('=');
        sb.append(((this.num2 == null)?"<null>":this.num2));
        sb.append(',');
        sb.append("porcentajeAplicado");
        sb.append('=');
        sb.append(((this.porcentajeAplicado == null)?"<null>":this.porcentajeAplicado));
        sb.append(',');
        sb.append("usandoCache");
        sb.append('=');
        sb.append(((this.usandoCache == null)?"<null>":this.usandoCache));
        sb.append(',');
        sb.append("resultado");
        sb.append('=');
        sb.append(((this.resultado == null)?"<null>":this.resultado));
        sb.append(',');
        sb.append("fecha");
        sb.append('=');
        sb.append(((this.fecha == null)?"<null>":this.fecha));
        sb.append(',');
        sb.append("additionalProperties");
        sb.append('=');
        sb.append(((this.additionalProperties == null)?"<null>":this.additionalProperties));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.fecha == null)? 0 :this.fecha.hashCode()));
        result = ((result* 31)+((this.resultado == null)? 0 :this.resultado.hashCode()));
        result = ((result* 31)+((this.usandoCache == null)? 0 :this.usandoCache.hashCode()));
        result = ((result* 31)+((this.num1 == null)? 0 :this.num1 .hashCode()));
        result = ((result* 31)+((this.porcentajeAplicado == null)? 0 :this.porcentajeAplicado.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.num2 == null)? 0 :this.num2 .hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof OperacionDTO) == false) {
            return false;
        }
        OperacionDTO rhs = ((OperacionDTO) other);
        return ((((((((this.fecha == rhs.fecha)||((this.fecha!= null)&&this.fecha.equals(rhs.fecha)))&&((this.resultado == rhs.resultado)||((this.resultado!= null)&&this.resultado.equals(rhs.resultado))))&&((this.usandoCache == rhs.usandoCache)||((this.usandoCache!= null)&&this.usandoCache.equals(rhs.usandoCache))))&&((this.num1 == rhs.num1)||((this.num1 != null)&&this.num1 .equals(rhs.num1))))&&((this.porcentajeAplicado == rhs.porcentajeAplicado)||((this.porcentajeAplicado!= null)&&this.porcentajeAplicado.equals(rhs.porcentajeAplicado))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.num2 == rhs.num2)||((this.num2 != null)&&this.num2 .equals(rhs.num2))));
    }

}

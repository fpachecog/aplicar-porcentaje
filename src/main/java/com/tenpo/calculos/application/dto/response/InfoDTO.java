
package com.tenpo.calculos.application.dto.response;

import java.math.BigDecimal;
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
 * Root Type for InfoDTO
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "resultado",
    "porcentajeAplicado",
    "montoSumado",
    "usandoCache"
})
@Generated("jsonschema2pojo")
public class InfoDTO {

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
    @JsonProperty("porcentajeAplicado")
    private BigDecimal porcentajeAplicado;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("montoSumado")
    private BigDecimal montoSumado;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("usandoCache")
    private Boolean usandoCache;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

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
    @JsonProperty("montoSumado")
    public BigDecimal getMontoSumado() {
        return montoSumado;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("montoSumado")
    public void setMontoSumado(BigDecimal montoSumado) {
        this.montoSumado = montoSumado;
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
        sb.append(InfoDTO.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("resultado");
        sb.append('=');
        sb.append(((this.resultado == null)?"<null>":this.resultado));
        sb.append(',');
        sb.append("porcentajeAplicado");
        sb.append('=');
        sb.append(((this.porcentajeAplicado == null)?"<null>":this.porcentajeAplicado));
        sb.append(',');
        sb.append("montoSumado");
        sb.append('=');
        sb.append(((this.montoSumado == null)?"<null>":this.montoSumado));
        sb.append(',');
        sb.append("usandoCache");
        sb.append('=');
        sb.append(((this.usandoCache == null)?"<null>":this.usandoCache));
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
        result = ((result* 31)+((this.montoSumado == null)? 0 :this.montoSumado.hashCode()));
        result = ((result* 31)+((this.porcentajeAplicado == null)? 0 :this.porcentajeAplicado.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.resultado == null)? 0 :this.resultado.hashCode()));
        result = ((result* 31)+((this.usandoCache == null)? 0 :this.usandoCache.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof InfoDTO) == false) {
            return false;
        }
        InfoDTO rhs = ((InfoDTO) other);
        return ((((((this.montoSumado == rhs.montoSumado)||((this.montoSumado!= null)&&this.montoSumado.equals(rhs.montoSumado)))&&((this.porcentajeAplicado == rhs.porcentajeAplicado)||((this.porcentajeAplicado!= null)&&this.porcentajeAplicado.equals(rhs.porcentajeAplicado))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.resultado == rhs.resultado)||((this.resultado!= null)&&this.resultado.equals(rhs.resultado))))&&((this.usandoCache == rhs.usandoCache)||((this.usandoCache!= null)&&this.usandoCache.equals(rhs.usandoCache))));
    }

}

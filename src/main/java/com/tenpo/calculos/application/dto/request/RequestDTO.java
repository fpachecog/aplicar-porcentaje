
package com.tenpo.calculos.application.dto.request;

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
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


/**
 * Root Type for RequestDTO
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "num1",
    "num2"
})
@Generated("jsonschema2pojo")
public class RequestDTO {

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("num1")
    @NotNull(message = "El campo num1 no puede ser nulo")
    private BigDecimal num1;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("num2")
    @NotNull(message = "El campo num2 no puede ser nulo")
    private BigDecimal num2;
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
        sb.append(RequestDTO.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("num1");
        sb.append('=');
        sb.append(((this.num1 == null)?"<null>":this.num1));
        sb.append(',');
        sb.append("num2");
        sb.append('=');
        sb.append(((this.num2 == null)?"<null>":this.num2));
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
        result = ((result* 31)+((this.num1 == null)? 0 :this.num1 .hashCode()));
        result = ((result* 31)+((this.num2 == null)? 0 :this.num2 .hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof RequestDTO) == false) {
            return false;
        }
        RequestDTO rhs = ((RequestDTO) other);
        return ((((this.num1 == rhs.num1)||((this.num1 != null)&&this.num1 .equals(rhs.num1)))&&((this.num2 == rhs.num2)||((this.num2 != null)&&this.num2 .equals(rhs.num2))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
    }

}


package com.tenpo.calculos.application.dto.response;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * Root Type for HistorialResponseDTO
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "historial",
    "metadata"
})
@Generated("jsonschema2pojo")
public class HistorialResponseDTO {

    @JsonProperty("historial")
    private List<OperacionDTO> historial = new ArrayList<OperacionDTO>();
    /**
     * Root Type for MetadataDTO
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("metadata")
    @JsonPropertyDescription("")
    private MetadataDTO metadata;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("historial")
    public List<OperacionDTO> getHistorial() {
        return historial;
    }

    @JsonProperty("historial")
    public void setHistorial(List<OperacionDTO> historial) {
        this.historial = historial;
    }

    /**
     * Root Type for MetadataDTO
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("metadata")
    public MetadataDTO getMetadata() {
        return metadata;
    }

    /**
     * Root Type for MetadataDTO
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("metadata")
    public void setMetadata(MetadataDTO metadata) {
        this.metadata = metadata;
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
        sb.append(HistorialResponseDTO.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("historial");
        sb.append('=');
        sb.append(((this.historial == null)?"<null>":this.historial));
        sb.append(',');
        sb.append("metadata");
        sb.append('=');
        sb.append(((this.metadata == null)?"<null>":this.metadata));
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
        result = ((result* 31)+((this.metadata == null)? 0 :this.metadata.hashCode()));
        result = ((result* 31)+((this.historial == null)? 0 :this.historial.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof HistorialResponseDTO) == false) {
            return false;
        }
        HistorialResponseDTO rhs = ((HistorialResponseDTO) other);
        return ((((this.metadata == rhs.metadata)||((this.metadata!= null)&&this.metadata.equals(rhs.metadata)))&&((this.historial == rhs.historial)||((this.historial!= null)&&this.historial.equals(rhs.historial))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
    }

}

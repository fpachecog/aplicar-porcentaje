
package com.tenpo.calculos.application.dto.response;

import java.util.LinkedHashMap;
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
 * Root Type for ResponseDTO
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "info",
    "metadata"
})
@Generated("jsonschema2pojo")
public class ResponseDTO {

    /**
     * Root Type for InfoDTO
     * <p>
     * 
     * 
     */
    @JsonProperty("info")
    @JsonPropertyDescription("")
    private InfoDTO info;
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

    /**
     * Root Type for InfoDTO
     * <p>
     * 
     * 
     */
    @JsonProperty("info")
    public InfoDTO getInfo() {
        return info;
    }

    /**
     * Root Type for InfoDTO
     * <p>
     * 
     * 
     */
    @JsonProperty("info")
    public void setInfo(InfoDTO info) {
        this.info = info;
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
        sb.append(ResponseDTO.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("info");
        sb.append('=');
        sb.append(((this.info == null)?"<null>":this.info));
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
        result = ((result* 31)+((this.info == null)? 0 :this.info.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ResponseDTO) == false) {
            return false;
        }
        ResponseDTO rhs = ((ResponseDTO) other);
        return ((((this.metadata == rhs.metadata)||((this.metadata!= null)&&this.metadata.equals(rhs.metadata)))&&((this.info == rhs.info)||((this.info!= null)&&this.info.equals(rhs.info))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
    }

}

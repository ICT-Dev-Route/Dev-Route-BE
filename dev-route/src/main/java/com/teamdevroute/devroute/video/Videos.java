package com.teamdevroute.devroute.video;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Converter;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.IOException;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
//@Setter
public class Videos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "video_id")
    private Long id;
    private String url;
    private String titile;
    private String thumnail_url;
    private Long price;
    @Column(name = "video_rank")
    private Long rank;
    private String platform_name;
    private Long count;
    @Convert(converter = JsonConverter.class)
    @Column(columnDefinition = "json")
    private Map<String, Object> teck_stacks;
    public Videos(){
    }

}
@Converter
class JsonConverter implements AttributeConverter<Map<String, Object>, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Map<String, Object> attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Error converting map to JSON string.", e);
        }
    }

    @Override
    public Map<String, Object> convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, Map.class);
        } catch (IOException e) {
            throw new IllegalArgumentException("Error converting JSON string to map.", e);
        }
    }
}

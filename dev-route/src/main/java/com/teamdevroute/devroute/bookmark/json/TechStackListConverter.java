package com.teamdevroute.devroute.bookmark.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teamdevroute.devroute.bookmark.domain.BookmarkTech;
import com.teamdevroute.devroute.video.domain.TechnologyStack;
import com.teamdevroute.devroute.video.domain.Videos;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Converter
public class TechStackListConverter implements AttributeConverter<List<BookmarkTech>, String> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<BookmarkTech> attribute) {
        if(attribute == null) {
            return null;
        }

        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Video를 list로 컨버팅하는 과정에 오류 발생", e);
        }
    }

    @Override
    public List<BookmarkTech> convertToEntityAttribute(String dbData) {
        if(dbData == null) {
            return new ArrayList<>();
        }

        try {
            return objectMapper.readValue(dbData, objectMapper.getTypeFactory().constructCollectionType(List.class, BookmarkTech.class));
        } catch (IOException e) {
            throw new RuntimeException("Failed to convert JSON string to list.", e);
        }
    }
}

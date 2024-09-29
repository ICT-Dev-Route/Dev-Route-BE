package com.teamdevroute.devroute.bookmark.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teamdevroute.devroute.bookmark.domain.BookmarkCompany;
import com.teamdevroute.devroute.company.domain.Company;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Converter
public class CompanyListConverter implements AttributeConverter<List<BookmarkCompany>, String> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<BookmarkCompany> attribute) {
        if(attribute == null || attribute.isEmpty()) {
            return null;
        }

        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Company를 list로 컨버팅하는 과정에 오류 발생", e);
        }
    }

    @Override
    public List<BookmarkCompany> convertToEntityAttribute(String dbData) {
        if(dbData == null) {
            return new ArrayList<>();
        }

        try {
            return new ObjectMapper().readValue(dbData, new TypeReference<List<BookmarkCompany>>() {});
            //return objectMapper.readValue(dbData, objectMapper.getTypeFactory().constructCollectionType(List.class, BookmarkCompany.class));
        } catch (IOException e) {
            throw new RuntimeException("Failed to convert JSON string to list.", e);
        }
    }
}

package com.github.angel.raa.utils;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class GeneroConverter  implements AttributeConverter<Genero, String>{
    @Override
    public String convertToDatabaseColumn(Genero attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.name();
    }

    @Override
    public Genero convertToEntityAttribute(String dbData) {
        if(dbData == null){
            return null;
        }
        return dbData.isEmpty() ? null : Stream.of(Genero.values()).filter(c -> c.name().equals(dbData)).findFirst().orElseThrow(IllegalArgumentException::new);

    }

}

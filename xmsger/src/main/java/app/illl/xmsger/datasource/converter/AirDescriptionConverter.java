package app.illl.xmsger.datasource.converter;

import app.illl.xmsger.exception.InternalServerErrorException;
import app.illl.xmsger.struct.AirDescription;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;
import java.io.IOException;

public class AirDescriptionConverter implements AttributeConverter<AirDescription, String> {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();


    @Override
    public String convertToDatabaseColumn(AirDescription attribute) {
        try {
            return OBJECT_MAPPER.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            throw new InternalServerErrorException(e);
        }
    }

    @Override
    public AirDescription convertToEntityAttribute(String dbData) {
        try {
            return OBJECT_MAPPER.readValue(dbData, AirDescription.class);
        } catch (IOException e) {
            throw new InternalServerErrorException(e);
        }
    }
}

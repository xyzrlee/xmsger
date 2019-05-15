/*
 *     XMsger
 *     Copyright (C) 2019  Ricky Li
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 */

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

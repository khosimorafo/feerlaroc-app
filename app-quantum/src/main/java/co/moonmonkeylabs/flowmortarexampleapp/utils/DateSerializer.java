package co.moonmonkeylabs.flowmortarexampleapp.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Date;

/**
 * Created by root on 2016/01/27.
 */
public class DateSerializer extends JsonSerializer<Date> {

//    private static DateTimeFormatter formatter =
//            DateTimeFormat.forPattern("dd-MM-yyyy");

    @Override
    public void serialize(Date value, JsonGenerator gen,
                          SerializerProvider arg2)
            throws IOException, JsonProcessingException {

        gen.writeString(value.toString());
    }
}

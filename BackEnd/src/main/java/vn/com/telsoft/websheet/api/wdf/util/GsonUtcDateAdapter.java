package vn.com.telsoft.websheet.api.wdf.util;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.TimeZone;

/**
 * Project: WDF
 * Author: LAMLT
 * Created on: 28/2/25
 */

public class GsonUtcDateAdapter implements JsonSerializer<Date>, JsonDeserializer<Date> {

    private final DateFormat dateFormat;

    public GsonUtcDateAdapter() {
        dateFormat = new SimpleDateFormat(Const.DFT.DATETIME_FORMAT);
        dateFormat.setTimeZone(TimeZone.getTimeZone(ZoneOffset.UTC));
    }

    @Override
    public synchronized JsonElement serialize(Date date, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(dateFormat.format(date));
    }

    @Override
    public synchronized Date deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {
        try {
            return dateFormat.parse(jsonElement.getAsString());
        } catch (ParseException e) {
            throw new JsonParseException(e);
        }
    }
}
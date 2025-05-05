package vn.com.telsoft.websheet.api.wdf.util;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneOffset;
import java.util.TimeZone;

/**
 * Project: WDF
 * Author: LAMLT
 * Created on: 28/2/25
 */

public class GsonUtcTimestampAdapter implements JsonSerializer<Timestamp>, JsonDeserializer<Timestamp> {

    private final DateFormat dateFormat;

    public GsonUtcTimestampAdapter() {
        dateFormat = new SimpleDateFormat(Const.DFT.DATETIME_FORMAT);
        dateFormat.setTimeZone(TimeZone.getTimeZone(ZoneOffset.UTC));
    }

    @Override
    public synchronized JsonElement serialize(Timestamp date, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(dateFormat.format(date));
    }

    @Override
    public synchronized Timestamp deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {
        try {
            return new Timestamp(dateFormat.parse(jsonElement.getAsString()).getTime());
        } catch (ParseException e) {
            throw new JsonParseException(e);
        }
    }
}

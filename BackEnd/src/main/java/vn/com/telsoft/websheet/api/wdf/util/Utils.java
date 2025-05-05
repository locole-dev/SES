package vn.com.telsoft.websheet.api.wdf.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

/**
 * Project: WDF
 * Author: LAMLT
 * Created on: 28/2/25
 */

public class Utils {
    public static Gson getGson() {
        return getGson(false);
    }

    public static Gson getGson(boolean serializeNulls) {
        Gson gson = new GsonBuilder()
                .disableHtmlEscaping()
                .setDateFormat(Const.DFT.DATETIME_FORMAT)
                .registerTypeAdapter(Date.class, new GsonUtcDateAdapter())
                .registerTypeAdapter(Timestamp.class, new GsonUtcTimestampAdapter())
                .registerTypeAdapter(new TypeToken<Map<String, Object>>() {}.getType(), new MapDeserializerDoubleAsIntFix())
                .create();

        if (serializeNulls) {
            gson.serializeNulls();
        }

        return gson;
    }
}

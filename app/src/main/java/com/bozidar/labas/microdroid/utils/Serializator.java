package com.bozidar.labas.microdroid.utils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * Created by Bozidar on 12.11.2015..
 */
public class Serializator {
    private static Gson gson;

    static {
        gson = new Gson();
    }

    /**
     * Deserializes the JSON string and turns it into object
     *
     * @param json     JSON String value
     * @param classOfT Class of object being deserialized
     * @param <T>      Type of deserialized object
     * @return Deserialized object
     */
    public static <T> T deserialize(String json, Class<T> classOfT) {
        if (json == null)
            return null;
        return gson.fromJson(json, classOfT);
    }

    /**
     * Serializes the object into JSON String
     *
     * @param object Object being serialized
     * @return JSON String value of serialized object
     */
    public static String serialize(Object object) {
        if (object == null)
            return null;
        return gson.toJson(object);
    }

    /**
     * Serializes the object into JSON Object
     *
     * @param object Object being serialized
     * @return JSON Object value of serialized object
     */
    public static JsonObject serializeToJsonObject(Object object) {
        JsonElement element = gson.toJsonTree(object);
        return element.getAsJsonObject();
    }

    /**
     * Returns Gson instance
     *
     * @return
     */
    public static Gson GSON() {
        return gson;
    }
}

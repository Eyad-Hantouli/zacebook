package com.zacebook.zacebook.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public abstract class ObjectSpecializer {
    protected String getStringValue(Map<String, Object> map, String key) {
        Object value = map.get(key);
        if (value != null) {
            return value.toString();
        }
        return null;
    }

    protected LocalDate getLocalDateValue(Map<String, Object> map, String key, String format) {
        Object value = map.get(key);
        if (value instanceof String) {
            return LocalDate.parse((String) value, DateTimeFormatter.ofPattern(format));
        }
        return null;
    }
}

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

    protected Long getLongValue(Map<String, Object> map, String key) {
        Object value = map.get(key);
        if (value != null) {
            try {
                return Long.parseLong(value.toString());
            }
            catch (Exception exception) {
                throw new IllegalStateException("key = " + key + " must be in integer format between 1 - " + Long. MAX_VALUE + ".");
            }
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

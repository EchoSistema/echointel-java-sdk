package com.echosistema.echointel.responses.common;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public abstract class EchoIntelResponse {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @JsonIgnore
    protected Map<String, Object> data = new HashMap<>();

    protected EchoIntelResponse() {
    }

    protected EchoIntelResponse(Map<String, Object> data) {
        this.data = data != null ? new HashMap<>(data) : new HashMap<>();
        hydrate(this.data);
    }

    protected abstract void hydrate(Map<String, Object> data);

    @JsonAnyGetter
    public Map<String, Object> getData() {
        return data;
    }

    @JsonAnySetter
    public void setData(String key, Object value) {
        this.data.put(key, value);
    }

    public Map<String, Object> toMap() {
        return new HashMap<>(data);
    }

    public String toJson() {
        try {
            return OBJECT_MAPPER.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize response to JSON", e);
        }
    }

    public Object get(String key) {
        return data.get(key);
    }

    public boolean has(String key) {
        return data.containsKey(key);
    }

    protected String getString(Map<String, Object> data, String key) {
        return getString(data, key, "");
    }

    protected String getString(Map<String, Object> data, String key, String defaultValue) {
        Object value = data.get(key);
        return value != null ? String.valueOf(value) : defaultValue;
    }

    protected int getInt(Map<String, Object> data, String key) {
        return getInt(data, key, 0);
    }

    protected int getInt(Map<String, Object> data, String key, int defaultValue) {
        Object value = data.get(key);
        if (value instanceof Number) {
            return ((Number) value).intValue();
        }
        return defaultValue;
    }

    protected long getLong(Map<String, Object> data, String key) {
        return getLong(data, key, 0L);
    }

    protected long getLong(Map<String, Object> data, String key, long defaultValue) {
        Object value = data.get(key);
        if (value instanceof Number) {
            return ((Number) value).longValue();
        }
        return defaultValue;
    }

    protected double getDouble(Map<String, Object> data, String key) {
        return getDouble(data, key, 0.0);
    }

    protected double getDouble(Map<String, Object> data, String key, double defaultValue) {
        Object value = data.get(key);
        if (value instanceof Number) {
            return ((Number) value).doubleValue();
        }
        return defaultValue;
    }

    protected boolean getBoolean(Map<String, Object> data, String key) {
        return getBoolean(data, key, false);
    }

    protected boolean getBoolean(Map<String, Object> data, String key, boolean defaultValue) {
        Object value = data.get(key);
        if (value instanceof Boolean) {
            return (Boolean) value;
        }
        return defaultValue;
    }

    @SuppressWarnings("unchecked")
    protected Map<String, Object> getMap(Map<String, Object> data, String key) {
        Object value = data.get(key);
        if (value instanceof Map) {
            return (Map<String, Object>) value;
        }
        return new HashMap<>();
    }

    @SuppressWarnings("unchecked")
    protected java.util.List<Map<String, Object>> getList(Map<String, Object> data, String key) {
        Object value = data.get(key);
        if (value instanceof java.util.List) {
            return (java.util.List<Map<String, Object>>) value;
        }
        return new java.util.ArrayList<>();
    }
}

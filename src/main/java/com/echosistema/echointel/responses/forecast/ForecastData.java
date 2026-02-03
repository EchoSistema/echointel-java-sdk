package com.echosistema.echointel.responses.forecast;

import com.echosistema.echointel.responses.common.EchoIntelResponse;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public class ForecastData extends EchoIntelResponse {

    private List<ForecastValue> calendar;
    private List<ForecastValue> business;

    public ForecastData() {
        super();
    }

    public ForecastData(Map<String, Object> data) {
        super(data);
    }

    @Override
    protected void hydrate(Map<String, Object> data) {
        this.calendar = getList(data, "calendar").stream()
                .map(ForecastValue::fromMap)
                .collect(Collectors.toList());
        this.business = getList(data, "business").stream()
                .map(ForecastValue::fromMap)
                .collect(Collectors.toList());
    }

    public static ForecastData fromMap(Map<String, Object> data) {
        return new ForecastData(data);
    }
}

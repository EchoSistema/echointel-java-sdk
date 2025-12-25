package com.echosistema.echointel.responses.forecast;

import com.echosistema.echointel.responses.common.EchoIntelResponse;
import lombok.Getter;

import java.util.Map;

@Getter
public class ForecastValue extends EchoIntelResponse {

    private String date;
    private double predicted;
    private Double lowerBound;
    private Double upperBound;

    public ForecastValue() {
        super();
    }

    public ForecastValue(Map<String, Object> data) {
        super(data);
    }

    @Override
    protected void hydrate(Map<String, Object> data) {
        this.date = getString(data, "date");
        this.predicted = getDouble(data, "predicted");
        Object lowerVal = data.get("lower_bound");
        this.lowerBound = lowerVal instanceof Number ? ((Number) lowerVal).doubleValue() : null;
        Object upperVal = data.get("upper_bound");
        this.upperBound = upperVal instanceof Number ? ((Number) upperVal).doubleValue() : null;
    }

    public static ForecastValue fromMap(Map<String, Object> data) {
        return new ForecastValue(data);
    }
}

package com.echosistema.echointel.responses.forecast;

import com.echosistema.echointel.responses.common.EchoIntelResponse;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public class ForecastUnitsResponse extends EchoIntelResponse {

    private int forecastPeriod;
    private List<ForecastAlgorithmResult> forecasts;
    private double executionTimeSeconds;

    public ForecastUnitsResponse() {
        super();
    }

    public ForecastUnitsResponse(Map<String, Object> data) {
        super(data);
    }

    @Override
    protected void hydrate(Map<String, Object> data) {
        this.forecastPeriod = getInt(data, "forecast_period");
        this.executionTimeSeconds = getDouble(data, "execution_time_seconds");
        this.forecasts = getList(data, "forecasts").stream()
                .map(ForecastAlgorithmResult::fromMap)
                .collect(Collectors.toList());
    }

    public static ForecastUnitsResponse fromMap(Map<String, Object> data) {
        return new ForecastUnitsResponse(data);
    }
}

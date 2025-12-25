package com.echosistema.echointel.responses.forecast;

import com.echosistema.echointel.responses.common.EchoIntelResponse;
import lombok.Getter;

import java.util.Map;

@Getter
public class ForecastAlgorithmResult extends EchoIntelResponse {

    private String productCode;
    private String bestAlgorithm;
    private ForecastEvaluationMetrics evaluationMetrics;
    private ForecastData forecast;

    public ForecastAlgorithmResult() {
        super();
    }

    public ForecastAlgorithmResult(Map<String, Object> data) {
        super(data);
    }

    @Override
    protected void hydrate(Map<String, Object> data) {
        this.productCode = getString(data, "product_code");
        this.bestAlgorithm = getString(data, "best_algorithm");
        this.evaluationMetrics = ForecastEvaluationMetrics.fromMap(getMap(data, "evaluation_metrics"));
        this.forecast = ForecastData.fromMap(getMap(data, "forecast"));
    }

    public static ForecastAlgorithmResult fromMap(Map<String, Object> data) {
        return new ForecastAlgorithmResult(data);
    }
}

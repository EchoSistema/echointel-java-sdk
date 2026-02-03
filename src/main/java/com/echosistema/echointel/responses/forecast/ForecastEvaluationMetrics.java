package com.echosistema.echointel.responses.forecast;

import com.echosistema.echointel.responses.common.EchoIntelResponse;
import lombok.Getter;

import java.util.Map;

@Getter
public class ForecastEvaluationMetrics extends EchoIntelResponse {

    private Double rmse;
    private Double mae;
    private Double r2;
    private Double averageDailySales;
    private String interpretation;

    public ForecastEvaluationMetrics() {
        super();
    }

    public ForecastEvaluationMetrics(Map<String, Object> data) {
        super(data);
    }

    @Override
    protected void hydrate(Map<String, Object> data) {
        Object rmseVal = data.get("RMSE");
        this.rmse = rmseVal instanceof Number ? ((Number) rmseVal).doubleValue() : null;
        Object maeVal = data.get("MAE");
        this.mae = maeVal instanceof Number ? ((Number) maeVal).doubleValue() : null;
        Object r2Val = data.get("R2");
        this.r2 = r2Val instanceof Number ? ((Number) r2Val).doubleValue() : null;
        Object avgVal = data.get("average_daily_sales");
        this.averageDailySales = avgVal instanceof Number ? ((Number) avgVal).doubleValue() : null;
        this.interpretation = getString(data, "Interpretation");
    }

    public static ForecastEvaluationMetrics fromMap(Map<String, Object> data) {
        return new ForecastEvaluationMetrics(data);
    }
}

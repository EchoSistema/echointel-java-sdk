package com.echosistema.echointel.responses.customer;

import com.echosistema.echointel.responses.common.EchoIntelResponse;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
public class ClvForecastCustomer extends EchoIntelResponse {

    private String customerId;
    private double predictedClv;
    private Double predictedTxns;
    private Double predictedAvgMargin;
    private String algorithmUsed;
    private String clvTier;
    private String interpretation;
    private List<String> explanations;

    public ClvForecastCustomer() {
        super();
    }

    public ClvForecastCustomer(Map<String, Object> data) {
        super(data);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void hydrate(Map<String, Object> data) {
        this.customerId = getString(data, "customer_id");
        this.predictedClv = getDouble(data, "predicted_clv");
        Object txnsVal = data.get("predicted_txns");
        this.predictedTxns = txnsVal instanceof Number ? ((Number) txnsVal).doubleValue() : null;
        Object marginVal = data.get("predicted_avg_margin");
        this.predictedAvgMargin = marginVal instanceof Number ? ((Number) marginVal).doubleValue() : null;
        this.algorithmUsed = getString(data, "algorithm_used");
        this.clvTier = getString(data, "clv_tier");
        this.interpretation = getString(data, "interpretation");
        Object explanationsData = data.get("explanations");
        this.explanations = explanationsData instanceof List ? (List<String>) explanationsData : List.of();
    }

    public static ClvForecastCustomer fromMap(Map<String, Object> data) {
        return new ClvForecastCustomer(data);
    }
}

package com.echosistema.echointel.responses.customer;

import com.echosistema.echointel.responses.common.EchoIntelResponse;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
public class ClvFeatureOut extends EchoIntelResponse {

    private String customerId;
    private int frequency;
    private double recency;
    private double t;
    private double monetaryValue;
    private boolean churned;
    private String clvPotentialTier;
    private String interpretation;
    private List<String> explanations;

    public ClvFeatureOut() {
        super();
    }

    public ClvFeatureOut(Map<String, Object> data) {
        super(data);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void hydrate(Map<String, Object> data) {
        this.customerId = getString(data, "customer_id");
        this.frequency = getInt(data, "frequency");
        this.recency = getDouble(data, "recency");
        this.t = getDouble(data, "T");
        this.monetaryValue = getDouble(data, "monetary_value");
        this.churned = getBoolean(data, "churned");
        this.clvPotentialTier = getString(data, "clv_potential_tier");
        this.interpretation = getString(data, "interpretation");
        Object explanationsData = data.get("explanations");
        this.explanations = explanationsData instanceof List ? (List<String>) explanationsData : List.of();
    }

    public static ClvFeatureOut fromMap(Map<String, Object> data) {
        return new ClvFeatureOut(data);
    }
}

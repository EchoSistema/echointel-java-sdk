package com.echosistema.echointel.responses.propensity;

import com.echosistema.echointel.responses.common.EchoIntelResponse;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
public class PropensityResponse extends EchoIntelResponse {

    private String customerId;
    private double propensityScore;
    private int propensityDecile;
    private String interpretation;
    private List<String> topFactors;

    public PropensityResponse() {
        super();
    }

    public PropensityResponse(Map<String, Object> data) {
        super(data);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void hydrate(Map<String, Object> data) {
        this.customerId = getString(data, "customer_id");
        this.propensityScore = getDouble(data, "propensity_score");
        this.propensityDecile = getInt(data, "propensity_decile");
        this.interpretation = getString(data, "interpretation");
        Object factorsData = data.get("top_factors");
        this.topFactors = factorsData instanceof List ? (List<String>) factorsData : List.of();
    }

    public static PropensityResponse fromMap(Map<String, Object> data) {
        return new PropensityResponse(data);
    }
}

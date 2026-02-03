package com.echosistema.echointel.responses.customer;

import com.echosistema.echointel.responses.common.EchoIntelResponse;
import lombok.Getter;

import java.util.Map;

@Getter
public class LoyaltySummary extends EchoIntelResponse {

    private double meanScore;
    private double medianScore;
    private double topDecileThreshold;
    private Map<String, Object> segmentCounts;

    public LoyaltySummary() {
        super();
    }

    public LoyaltySummary(Map<String, Object> data) {
        super(data);
    }

    @Override
    protected void hydrate(Map<String, Object> data) {
        this.meanScore = getDouble(data, "mean_score");
        this.medianScore = getDouble(data, "median_score");
        this.topDecileThreshold = getDouble(data, "top_decile_threshold");
        this.segmentCounts = getMap(data, "segment_counts");
    }

    public static LoyaltySummary fromMap(Map<String, Object> data) {
        return new LoyaltySummary(data);
    }
}

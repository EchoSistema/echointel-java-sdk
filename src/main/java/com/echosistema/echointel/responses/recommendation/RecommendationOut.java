package com.echosistema.echointel.responses.recommendation;

import com.echosistema.echointel.responses.common.EchoIntelResponse;
import lombok.Getter;

import java.util.Map;

@Getter
public class RecommendationOut extends EchoIntelResponse {

    private String itemCode;
    private double score;
    private String reason;

    public RecommendationOut() {
        super();
    }

    public RecommendationOut(Map<String, Object> data) {
        super(data);
    }

    @Override
    protected void hydrate(Map<String, Object> data) {
        this.itemCode = getString(data, "item_code");
        this.score = getDouble(data, "score");
        this.reason = getString(data, "reason");
    }

    public static RecommendationOut fromMap(Map<String, Object> data) {
        return new RecommendationOut(data);
    }
}

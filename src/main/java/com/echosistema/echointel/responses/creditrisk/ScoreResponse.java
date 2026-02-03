package com.echosistema.echointel.responses.creditrisk;

import com.echosistema.echointel.responses.common.EchoIntelResponse;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public class ScoreResponse extends EchoIntelResponse {

    private double auc;
    private String bestModel;
    private List<Prediction> predictions;
    private String interpretation;

    public ScoreResponse() {
        super();
    }

    public ScoreResponse(Map<String, Object> data) {
        super(data);
    }

    @Override
    protected void hydrate(Map<String, Object> data) {
        this.auc = getDouble(data, "auc");
        this.bestModel = getString(data, "best_model");
        this.interpretation = getString(data, "interpretation");
        this.predictions = getList(data, "predictions").stream()
                .map(Prediction::fromMap)
                .collect(Collectors.toList());
    }

    public static ScoreResponse fromMap(Map<String, Object> data) {
        return new ScoreResponse(data);
    }
}

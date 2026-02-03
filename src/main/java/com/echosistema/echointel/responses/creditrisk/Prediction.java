package com.echosistema.echointel.responses.creditrisk;

import com.echosistema.echointel.responses.common.EchoIntelResponse;
import lombok.Getter;

import java.util.Map;

@Getter
public class Prediction extends EchoIntelResponse {

    private String id;
    private double probability;
    private Map<String, Object> shapValues;
    private String shapInterpretation;

    public Prediction() {
        super();
    }

    public Prediction(Map<String, Object> data) {
        super(data);
    }

    @Override
    protected void hydrate(Map<String, Object> data) {
        this.id = getString(data, "id");
        this.probability = getDouble(data, "probability");
        this.shapValues = getMap(data, "shap_values");
        this.shapInterpretation = getString(data, "shap_interpretation");
    }

    public static Prediction fromMap(Map<String, Object> data) {
        return new Prediction(data);
    }
}

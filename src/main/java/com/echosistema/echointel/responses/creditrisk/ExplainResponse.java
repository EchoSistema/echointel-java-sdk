package com.echosistema.echointel.responses.creditrisk;

import com.echosistema.echointel.responses.common.EchoIntelResponse;
import lombok.Getter;

import java.util.Map;

@Getter
public class ExplainResponse extends EchoIntelResponse {

    private double probability;
    private Map<String, Object> explanation;
    private String interpretation;

    public ExplainResponse() {
        super();
    }

    public ExplainResponse(Map<String, Object> data) {
        super(data);
    }

    @Override
    protected void hydrate(Map<String, Object> data) {
        this.probability = getDouble(data, "probability");
        this.explanation = getMap(data, "explanation");
        this.interpretation = getString(data, "interpretation");
    }

    public static ExplainResponse fromMap(Map<String, Object> data) {
        return new ExplainResponse(data);
    }
}

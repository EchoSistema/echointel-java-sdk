package com.echosistema.echointel.responses.journey;

import com.echosistema.echointel.responses.common.EchoIntelResponse;
import lombok.Getter;

import java.util.Map;

@Getter
public class Transition extends EchoIntelResponse {

    private String fromState;
    private String toState;
    private double probability;

    public Transition() {
        super();
    }

    public Transition(Map<String, Object> data) {
        super(data);
    }

    @Override
    protected void hydrate(Map<String, Object> data) {
        this.fromState = getString(data, "from_state");
        this.toState = getString(data, "to_state");
        this.probability = getDouble(data, "probability");
    }

    public static Transition fromMap(Map<String, Object> data) {
        return new Transition(data);
    }
}

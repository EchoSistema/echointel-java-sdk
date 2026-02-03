package com.echosistema.echointel.responses.journey;

import com.echosistema.echointel.responses.common.EchoIntelResponse;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public class MarkovResponse extends EchoIntelResponse {

    private List<Transition> transitions;
    private String interpretation;

    public MarkovResponse() {
        super();
    }

    public MarkovResponse(Map<String, Object> data) {
        super(data);
    }

    @Override
    protected void hydrate(Map<String, Object> data) {
        this.interpretation = getString(data, "interpretation");
        this.transitions = getList(data, "transitions").stream()
                .map(Transition::fromMap)
                .collect(Collectors.toList());
    }

    public static MarkovResponse fromMap(Map<String, Object> data) {
        return new MarkovResponse(data);
    }
}

package com.echosistema.echointel.responses.crosssell;

import com.echosistema.echointel.responses.common.EchoIntelResponse;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public class UpsellResponse extends EchoIntelResponse {

    private List<UpsellPair> suggestions;
    private String metric;
    private String generatedAt;

    public UpsellResponse() {
        super();
    }

    public UpsellResponse(Map<String, Object> data) {
        super(data);
    }

    @Override
    protected void hydrate(Map<String, Object> data) {
        this.suggestions = getList(data, "suggestions").stream()
                .map(UpsellPair::fromMap)
                .collect(Collectors.toList());
        this.metric = getString(data, "metric");
        this.generatedAt = getString(data, "generated_at");
    }

    public static UpsellResponse fromMap(Map<String, Object> data) {
        return new UpsellResponse(data);
    }
}

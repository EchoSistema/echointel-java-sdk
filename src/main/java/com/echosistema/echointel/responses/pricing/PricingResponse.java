package com.echosistema.echointel.responses.pricing;

import com.echosistema.echointel.responses.common.EchoIntelResponse;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public class PricingResponse extends EchoIntelResponse {

    private List<PriceOutcome> recommendations;
    private String generatedAt;

    public PricingResponse() {
        super();
    }

    public PricingResponse(Map<String, Object> data) {
        super(data);
    }

    @Override
    protected void hydrate(Map<String, Object> data) {
        this.generatedAt = getString(data, "generated_at");
        this.recommendations = getList(data, "recommendations").stream()
                .map(PriceOutcome::fromMap)
                .collect(Collectors.toList());
    }

    public static PricingResponse fromMap(Map<String, Object> data) {
        return new PricingResponse(data);
    }
}

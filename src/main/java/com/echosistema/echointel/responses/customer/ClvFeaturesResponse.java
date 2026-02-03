package com.echosistema.echointel.responses.customer;

import com.echosistema.echointel.responses.common.EchoIntelResponse;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public class ClvFeaturesResponse extends EchoIntelResponse {

    private List<ClvFeatureOut> customers;

    public ClvFeaturesResponse() {
        super();
    }

    public ClvFeaturesResponse(Map<String, Object> data) {
        super(data);
    }

    @Override
    protected void hydrate(Map<String, Object> data) {
        this.customers = getList(data, "customers").stream()
                .map(ClvFeatureOut::fromMap)
                .collect(Collectors.toList());
    }

    public static ClvFeaturesResponse fromMap(Map<String, Object> data) {
        return new ClvFeaturesResponse(data);
    }
}

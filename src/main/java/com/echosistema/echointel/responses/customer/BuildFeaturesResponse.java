package com.echosistema.echointel.responses.customer;

import com.echosistema.echointel.responses.common.EchoIntelResponse;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public class BuildFeaturesResponse extends EchoIntelResponse {

    private List<FeaturesObject> customers;

    public BuildFeaturesResponse() {
        super();
    }

    public BuildFeaturesResponse(Map<String, Object> data) {
        super(data);
    }

    @Override
    protected void hydrate(Map<String, Object> data) {
        this.customers = getList(data, "customers").stream()
                .map(FeaturesObject::fromMap)
                .collect(Collectors.toList());
    }

    public static BuildFeaturesResponse fromMap(Map<String, Object> data) {
        return new BuildFeaturesResponse(data);
    }
}

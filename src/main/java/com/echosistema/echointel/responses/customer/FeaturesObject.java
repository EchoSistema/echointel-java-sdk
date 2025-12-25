package com.echosistema.echointel.responses.customer;

import com.echosistema.echointel.responses.common.EchoIntelResponse;
import lombok.Getter;

import java.util.Map;

@Getter
public class FeaturesObject extends EchoIntelResponse {

    private String customerId;
    private Map<String, Object> features;

    public FeaturesObject() {
        super();
    }

    public FeaturesObject(Map<String, Object> data) {
        super(data);
    }

    @Override
    protected void hydrate(Map<String, Object> data) {
        this.customerId = getString(data, "customer_id");
        this.features = getMap(data, "features");
    }

    public static FeaturesObject fromMap(Map<String, Object> data) {
        return new FeaturesObject(data);
    }
}

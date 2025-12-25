package com.echosistema.echointel.responses.crosssell;

import com.echosistema.echointel.responses.common.EchoIntelResponse;
import lombok.Getter;

import java.util.Map;

@Getter
public class UpsellPair extends EchoIntelResponse {

    private String baseItem;
    private String upsellItem;
    private double probability;

    public UpsellPair() {
        super();
    }

    public UpsellPair(Map<String, Object> data) {
        super(data);
    }

    @Override
    protected void hydrate(Map<String, Object> data) {
        this.baseItem = getString(data, "base_item");
        this.upsellItem = getString(data, "upsell_item");
        this.probability = getDouble(data, "probability");
    }

    public static UpsellPair fromMap(Map<String, Object> data) {
        return new UpsellPair(data);
    }
}

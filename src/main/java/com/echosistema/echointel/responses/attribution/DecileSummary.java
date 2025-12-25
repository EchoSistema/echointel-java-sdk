package com.echosistema.echointel.responses.attribution;

import com.echosistema.echointel.responses.common.EchoIntelResponse;
import lombok.Getter;

import java.util.Map;

@Getter
public class DecileSummary extends EchoIntelResponse {

    private int decile;
    private double meanUplift;
    private int nCustomers;

    public DecileSummary() {
        super();
    }

    public DecileSummary(Map<String, Object> data) {
        super(data);
    }

    @Override
    protected void hydrate(Map<String, Object> data) {
        this.decile = getInt(data, "decile");
        this.meanUplift = getDouble(data, "mean_uplift");
        this.nCustomers = getInt(data, "n_customers");
    }

    public static DecileSummary fromMap(Map<String, Object> data) {
        return new DecileSummary(data);
    }
}

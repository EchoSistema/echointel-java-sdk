package com.echosistema.echointel.responses.customer;

import com.echosistema.echointel.responses.common.EchoIntelResponse;
import lombok.Getter;

import java.util.Map;

@Getter
public class CustomerScore extends EchoIntelResponse {

    private String customerId;
    private double loyaltyScore;
    private String segment;

    public CustomerScore() {
        super();
    }

    public CustomerScore(Map<String, Object> data) {
        super(data);
    }

    @Override
    protected void hydrate(Map<String, Object> data) {
        this.customerId = getString(data, "customer_id");
        this.loyaltyScore = getDouble(data, "loyalty_score");
        this.segment = getString(data, "segment");
    }

    public static CustomerScore fromMap(Map<String, Object> data) {
        return new CustomerScore(data);
    }
}

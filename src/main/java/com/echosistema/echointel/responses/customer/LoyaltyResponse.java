package com.echosistema.echointel.responses.customer;

import com.echosistema.echointel.responses.common.EchoIntelResponse;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public class LoyaltyResponse extends EchoIntelResponse {

    private List<CustomerScore> customers;
    private LoyaltySummary summary;

    public LoyaltyResponse() {
        super();
    }

    public LoyaltyResponse(Map<String, Object> data) {
        super(data);
    }

    @Override
    protected void hydrate(Map<String, Object> data) {
        this.customers = getList(data, "customers").stream()
                .map(CustomerScore::fromMap)
                .collect(Collectors.toList());
        this.summary = LoyaltySummary.fromMap(getMap(data, "summary"));
    }

    public static LoyaltyResponse fromMap(Map<String, Object> data) {
        return new LoyaltyResponse(data);
    }
}

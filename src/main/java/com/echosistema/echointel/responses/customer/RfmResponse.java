package com.echosistema.echointel.responses.customer;

import com.echosistema.echointel.responses.common.EchoIntelResponse;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public class RfmResponse extends EchoIntelResponse {

    private List<RfmCustomerOut> customers;
    private Map<String, Object> thresholds;

    public RfmResponse() {
        super();
    }

    public RfmResponse(Map<String, Object> data) {
        super(data);
    }

    @Override
    protected void hydrate(Map<String, Object> data) {
        this.customers = getList(data, "customers").stream()
                .map(RfmCustomerOut::fromMap)
                .collect(Collectors.toList());
        this.thresholds = getMap(data, "thresholds");
    }

    public static RfmResponse fromMap(Map<String, Object> data) {
        return new RfmResponse(data);
    }
}

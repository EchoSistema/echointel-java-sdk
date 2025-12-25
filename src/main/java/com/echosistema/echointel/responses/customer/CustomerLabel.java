package com.echosistema.echointel.responses.customer;

import com.echosistema.echointel.responses.common.EchoIntelResponse;
import lombok.Getter;

import java.util.Map;

@Getter
public class CustomerLabel extends EchoIntelResponse {

    private String customerId;
    private int clusterId;

    public CustomerLabel() {
        super();
    }

    public CustomerLabel(Map<String, Object> data) {
        super(data);
    }

    @Override
    protected void hydrate(Map<String, Object> data) {
        this.customerId = getString(data, "customer_id");
        this.clusterId = getInt(data, "cluster_id");
    }

    public static CustomerLabel fromMap(Map<String, Object> data) {
        return new CustomerLabel(data);
    }
}

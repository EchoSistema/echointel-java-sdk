package com.echosistema.echointel.responses.admin;

import com.echosistema.echointel.responses.common.EchoIntelResponse;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
public class CustomerOut extends EchoIntelResponse {

    private String customerApiId;
    private String secret;
    private boolean enabled;
    private String tsCreate;
    private String tsUpdate;
    private List<String> allowedRoutes;

    public CustomerOut() {
        super();
    }

    public CustomerOut(Map<String, Object> data) {
        super(data);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void hydrate(Map<String, Object> data) {
        this.customerApiId = getString(data, "customer_api_id");
        this.secret = getString(data, "secret");
        this.enabled = getBoolean(data, "enabled");
        this.tsCreate = getString(data, "ts_create");
        this.tsUpdate = getString(data, "ts_update");
        Object routes = data.get("allowed_routes");
        this.allowedRoutes = routes instanceof List ? (List<String>) routes : List.of();
    }

    public static CustomerOut fromMap(Map<String, Object> data) {
        return new CustomerOut(data);
    }
}

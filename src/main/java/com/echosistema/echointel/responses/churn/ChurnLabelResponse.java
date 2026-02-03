package com.echosistema.echointel.responses.churn;

import com.echosistema.echointel.responses.common.EchoIntelResponse;
import lombok.Getter;

import java.util.Map;

@Getter
public class ChurnLabelResponse extends EchoIntelResponse {

    private String customerId;
    private String snapshotDate;
    private int churned;

    public ChurnLabelResponse() {
        super();
    }

    public ChurnLabelResponse(Map<String, Object> data) {
        super(data);
    }

    @Override
    protected void hydrate(Map<String, Object> data) {
        this.customerId = getString(data, "customer_id");
        this.snapshotDate = getString(data, "snapshot_date");
        this.churned = getInt(data, "churned");
    }

    public static ChurnLabelResponse fromMap(Map<String, Object> data) {
        return new ChurnLabelResponse(data);
    }
}

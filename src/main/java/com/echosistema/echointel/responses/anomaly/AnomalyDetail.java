package com.echosistema.echointel.responses.anomaly;

import com.echosistema.echointel.responses.common.EchoIntelResponse;
import lombok.Getter;

import java.util.Map;

@Getter
public class AnomalyDetail extends EchoIntelResponse {

    private String id;
    private double anomalyScore;
    private boolean fraudFlag;

    public AnomalyDetail() {
        super();
    }

    public AnomalyDetail(Map<String, Object> data) {
        super(data);
    }

    @Override
    protected void hydrate(Map<String, Object> data) {
        this.id = getString(data, "id");
        this.anomalyScore = getDouble(data, "anomaly_score");
        this.fraudFlag = getBoolean(data, "fraud_flag");
    }

    public static AnomalyDetail fromMap(Map<String, Object> data) {
        return new AnomalyDetail(data);
    }
}

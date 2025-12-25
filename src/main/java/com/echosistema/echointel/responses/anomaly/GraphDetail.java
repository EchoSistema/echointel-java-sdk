package com.echosistema.echointel.responses.anomaly;

import com.echosistema.echointel.responses.common.EchoIntelResponse;
import lombok.Getter;

import java.util.Map;

@Getter
public class GraphDetail extends EchoIntelResponse {

    private String node;
    private double anomalyScore;
    private boolean flag;

    public GraphDetail() {
        super();
    }

    public GraphDetail(Map<String, Object> data) {
        super(data);
    }

    @Override
    protected void hydrate(Map<String, Object> data) {
        this.node = getString(data, "node");
        this.anomalyScore = getDouble(data, "anomaly_score");
        this.flag = getBoolean(data, "flag");
    }

    public static GraphDetail fromMap(Map<String, Object> data) {
        return new GraphDetail(data);
    }
}

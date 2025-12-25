package com.echosistema.echointel.responses.anomaly;

import com.echosistema.echointel.responses.common.EchoIntelResponse;
import lombok.Getter;

import java.util.Map;

@Getter
public class AnomalyKpiBlock extends EchoIntelResponse {

    private double detectedPct;
    private Double auc;

    public AnomalyKpiBlock() {
        super();
    }

    public AnomalyKpiBlock(Map<String, Object> data) {
        super(data);
    }

    @Override
    protected void hydrate(Map<String, Object> data) {
        this.detectedPct = getDouble(data, "detected_pct");
        Object aucValue = data.get("auc");
        this.auc = aucValue instanceof Number ? ((Number) aucValue).doubleValue() : null;
    }

    public static AnomalyKpiBlock fromMap(Map<String, Object> data) {
        return new AnomalyKpiBlock(data);
    }
}

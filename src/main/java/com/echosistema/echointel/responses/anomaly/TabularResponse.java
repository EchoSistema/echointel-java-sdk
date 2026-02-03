package com.echosistema.echointel.responses.anomaly;

import com.echosistema.echointel.responses.common.EchoIntelResponse;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public class TabularResponse extends EchoIntelResponse {

    private AnomalyKpiBlock kpi;
    private List<AnomalyDetail> details;
    private String interpretation;

    public TabularResponse() {
        super();
    }

    public TabularResponse(Map<String, Object> data) {
        super(data);
    }

    @Override
    protected void hydrate(Map<String, Object> data) {
        this.kpi = AnomalyKpiBlock.fromMap(getMap(data, "kpi"));
        this.interpretation = getString(data, "interpretation");
        this.details = getList(data, "details").stream()
                .map(AnomalyDetail::fromMap)
                .collect(Collectors.toList());
    }

    public static TabularResponse fromMap(Map<String, Object> data) {
        return new TabularResponse(data);
    }
}

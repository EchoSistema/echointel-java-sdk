package com.echosistema.echointel.responses.sentiment;

import com.echosistema.echointel.responses.common.EchoIntelResponse;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public class ReportResponse extends EchoIntelResponse {

    private String periodStart;
    private String periodEnd;
    private SentimentKpiBlock kpis;
    private List<SentimentDetail> details;
    private String interpretation;

    public ReportResponse() {
        super();
    }

    public ReportResponse(Map<String, Object> data) {
        super(data);
    }

    @Override
    protected void hydrate(Map<String, Object> data) {
        this.periodStart = getString(data, "period_start");
        this.periodEnd = getString(data, "period_end");
        this.kpis = SentimentKpiBlock.fromMap(getMap(data, "kpis"));
        this.interpretation = getString(data, "interpretation");
        this.details = getList(data, "details").stream()
                .map(SentimentDetail::fromMap)
                .collect(Collectors.toList());
    }

    public static ReportResponse fromMap(Map<String, Object> data) {
        return new ReportResponse(data);
    }
}

package com.echosistema.echointel.responses.sentiment;

import com.echosistema.echointel.responses.common.EchoIntelResponse;
import lombok.Getter;

import java.util.Map;

@Getter
public class SentimentKpiBlock extends EchoIntelResponse {

    private int totalRecords;
    private int positiveCount;
    private int negativeCount;
    private int neutralCount;
    private double positivePct;
    private double negativePct;
    private double neutralPct;

    public SentimentKpiBlock() {
        super();
    }

    public SentimentKpiBlock(Map<String, Object> data) {
        super(data);
    }

    @Override
    protected void hydrate(Map<String, Object> data) {
        this.totalRecords = getInt(data, "total_records");
        this.positiveCount = getInt(data, "positive_count");
        this.negativeCount = getInt(data, "negative_count");
        this.neutralCount = getInt(data, "neutral_count");
        this.positivePct = getDouble(data, "positive_pct");
        this.negativePct = getDouble(data, "negative_pct");
        this.neutralPct = getDouble(data, "neutral_pct");
    }

    public static SentimentKpiBlock fromMap(Map<String, Object> data) {
        return new SentimentKpiBlock(data);
    }
}

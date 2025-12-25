package com.echosistema.echointel.responses.sentiment;

import com.echosistema.echointel.responses.common.EchoIntelResponse;
import lombok.Getter;

import java.util.Map;

@Getter
public class SentimentDetail extends EchoIntelResponse {

    private String id;
    private String text;
    private String sentiment;
    private double score;

    public SentimentDetail() {
        super();
    }

    public SentimentDetail(Map<String, Object> data) {
        super(data);
    }

    @Override
    protected void hydrate(Map<String, Object> data) {
        this.id = getString(data, "id");
        this.text = getString(data, "text");
        this.sentiment = getString(data, "sentiment");
        this.score = getDouble(data, "score");
    }

    public static SentimentDetail fromMap(Map<String, Object> data) {
        return new SentimentDetail(data);
    }
}

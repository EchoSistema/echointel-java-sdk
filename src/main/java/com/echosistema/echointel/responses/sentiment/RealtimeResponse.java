package com.echosistema.echointel.responses.sentiment;

import com.echosistema.echointel.responses.common.EchoIntelResponse;
import lombok.Getter;

import java.util.Map;

@Getter
public class RealtimeResponse extends EchoIntelResponse {

    private String text;
    private String sentiment;
    private double score;
    private String interpretation;

    public RealtimeResponse() {
        super();
    }

    public RealtimeResponse(Map<String, Object> data) {
        super(data);
    }

    @Override
    protected void hydrate(Map<String, Object> data) {
        this.text = getString(data, "text");
        this.sentiment = getString(data, "sentiment");
        this.score = getDouble(data, "score");
        this.interpretation = getString(data, "interpretation");
    }

    public static RealtimeResponse fromMap(Map<String, Object> data) {
        return new RealtimeResponse(data);
    }
}

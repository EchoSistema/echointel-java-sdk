package com.echosistema.echointel.responses.journey;

import com.echosistema.echointel.responses.common.EchoIntelResponse;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
public class Path extends EchoIntelResponse {

    private List<String> sequence;
    private int count;
    private double percentage;

    public Path() {
        super();
    }

    public Path(Map<String, Object> data) {
        super(data);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void hydrate(Map<String, Object> data) {
        Object seqData = data.get("sequence");
        this.sequence = seqData instanceof List ? (List<String>) seqData : List.of();
        this.count = getInt(data, "count");
        this.percentage = getDouble(data, "percentage");
    }

    public static Path fromMap(Map<String, Object> data) {
        return new Path(data);
    }
}

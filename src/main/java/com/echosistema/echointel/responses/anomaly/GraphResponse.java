package com.echosistema.echointel.responses.anomaly;

import com.echosistema.echointel.responses.common.EchoIntelResponse;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public class GraphResponse extends EchoIntelResponse {

    private List<GraphDetail> details;
    private String interpretation;

    public GraphResponse() {
        super();
    }

    public GraphResponse(Map<String, Object> data) {
        super(data);
    }

    @Override
    protected void hydrate(Map<String, Object> data) {
        this.interpretation = getString(data, "interpretation");
        this.details = getList(data, "details").stream()
                .map(GraphDetail::fromMap)
                .collect(Collectors.toList());
    }

    public static GraphResponse fromMap(Map<String, Object> data) {
        return new GraphResponse(data);
    }
}

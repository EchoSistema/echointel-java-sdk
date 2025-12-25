package com.echosistema.echointel.responses.recommendation;

import com.echosistema.echointel.responses.common.EchoIntelResponse;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public class RecResponse extends EchoIntelResponse {

    private List<RecommendationOut> recommendations;
    private ModelInfo modelInfo;

    public RecResponse() {
        super();
    }

    public RecResponse(Map<String, Object> data) {
        super(data);
    }

    @Override
    protected void hydrate(Map<String, Object> data) {
        this.recommendations = getList(data, "recommendations").stream()
                .map(RecommendationOut::fromMap)
                .collect(Collectors.toList());
        this.modelInfo = ModelInfo.fromMap(getMap(data, "model_info"));
    }

    public static RecResponse fromMap(Map<String, Object> data) {
        return new RecResponse(data);
    }
}

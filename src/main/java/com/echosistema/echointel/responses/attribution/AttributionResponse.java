package com.echosistema.echointel.responses.attribution;

import com.echosistema.echointel.responses.common.EchoIntelResponse;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public class AttributionResponse extends EchoIntelResponse {

    private List<ChannelContribution> contributions;
    private Double globalAuc;
    private String interpretation;

    public AttributionResponse() {
        super();
    }

    public AttributionResponse(Map<String, Object> data) {
        super(data);
    }

    @Override
    protected void hydrate(Map<String, Object> data) {
        Object aucValue = data.get("global_auc");
        this.globalAuc = aucValue instanceof Number ? ((Number) aucValue).doubleValue() : null;
        this.interpretation = getString(data, "interpretation");
        this.contributions = getList(data, "contributions").stream()
                .map(ChannelContribution::fromMap)
                .collect(Collectors.toList());
    }

    public static AttributionResponse fromMap(Map<String, Object> data) {
        return new AttributionResponse(data);
    }
}

package com.echosistema.echointel.responses.crosssell;

import com.echosistema.echointel.responses.common.EchoIntelResponse;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
public class CrossSellResponse extends EchoIntelResponse {

    private List<String> itemsOrCategories;
    private List<List<Double>> matrix;
    private String metric;
    private String generatedAt;

    public CrossSellResponse() {
        super();
    }

    public CrossSellResponse(Map<String, Object> data) {
        super(data);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void hydrate(Map<String, Object> data) {
        Object items = data.get("items_or_categories");
        this.itemsOrCategories = items instanceof List ? (List<String>) items : List.of();
        Object matrixData = data.get("matrix");
        this.matrix = matrixData instanceof List ? (List<List<Double>>) matrixData : List.of();
        this.metric = getString(data, "metric");
        this.generatedAt = getString(data, "generated_at");
    }

    public static CrossSellResponse fromMap(Map<String, Object> data) {
        return new CrossSellResponse(data);
    }
}

package com.echosistema.echointel.responses.recommendation;

import com.echosistema.echointel.responses.common.EchoIntelResponse;
import lombok.Getter;

import java.util.Map;

@Getter
public class ModelInfo extends EchoIntelResponse {

    private String algorithmUsed;
    private String trainedAt;
    private int nItems;
    private int nUsers;

    public ModelInfo() {
        super();
    }

    public ModelInfo(Map<String, Object> data) {
        super(data);
    }

    @Override
    protected void hydrate(Map<String, Object> data) {
        this.algorithmUsed = getString(data, "algorithm_used");
        this.trainedAt = getString(data, "trained_at");
        this.nItems = getInt(data, "n_items");
        this.nUsers = getInt(data, "n_users");
    }

    public static ModelInfo fromMap(Map<String, Object> data) {
        return new ModelInfo(data);
    }
}

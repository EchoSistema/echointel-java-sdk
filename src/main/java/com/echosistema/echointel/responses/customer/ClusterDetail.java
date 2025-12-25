package com.echosistema.echointel.responses.customer;

import com.echosistema.echointel.responses.common.EchoIntelResponse;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
public class ClusterDetail extends EchoIntelResponse {

    private int clusterId;
    private int size;
    private List<Double> centroid;
    private String personaLabel;

    public ClusterDetail() {
        super();
    }

    public ClusterDetail(Map<String, Object> data) {
        super(data);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void hydrate(Map<String, Object> data) {
        this.clusterId = getInt(data, "cluster_id");
        this.size = getInt(data, "size");
        Object centroidData = data.get("centroid");
        this.centroid = centroidData instanceof List ? (List<Double>) centroidData : List.of();
        this.personaLabel = getString(data, "persona_label");
    }

    public static ClusterDetail fromMap(Map<String, Object> data) {
        return new ClusterDetail(data);
    }
}

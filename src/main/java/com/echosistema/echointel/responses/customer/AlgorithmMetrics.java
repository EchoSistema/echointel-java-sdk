package com.echosistema.echointel.responses.customer;

import com.echosistema.echointel.responses.common.EchoIntelResponse;
import lombok.Getter;

import java.util.Map;

@Getter
public class AlgorithmMetrics extends EchoIntelResponse {

    private String algorithm;
    private Map<String, Object> params;
    private Double silhouette;
    private Double daviesBouldin;
    private Double calinskiHarabasz;
    private Integer nClusters;

    public AlgorithmMetrics() {
        super();
    }

    public AlgorithmMetrics(Map<String, Object> data) {
        super(data);
    }

    @Override
    protected void hydrate(Map<String, Object> data) {
        this.algorithm = getString(data, "algorithm");
        this.params = getMap(data, "params");
        Object silhouetteVal = data.get("silhouette");
        this.silhouette = silhouetteVal instanceof Number ? ((Number) silhouetteVal).doubleValue() : null;
        Object dbVal = data.get("davies_bouldin");
        this.daviesBouldin = dbVal instanceof Number ? ((Number) dbVal).doubleValue() : null;
        Object chVal = data.get("calinski_harabasz");
        this.calinskiHarabasz = chVal instanceof Number ? ((Number) chVal).doubleValue() : null;
        Object ncVal = data.get("n_clusters");
        this.nClusters = ncVal instanceof Number ? ((Number) ncVal).intValue() : null;
    }

    public static AlgorithmMetrics fromMap(Map<String, Object> data) {
        return new AlgorithmMetrics(data);
    }
}

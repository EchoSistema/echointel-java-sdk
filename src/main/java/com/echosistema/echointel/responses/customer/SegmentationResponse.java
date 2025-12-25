package com.echosistema.echointel.responses.customer;

import com.echosistema.echointel.responses.common.EchoIntelResponse;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public class SegmentationResponse extends EchoIntelResponse {

    private String bestAlgorithm;
    private List<AlgorithmMetrics> evaluationMetrics;
    private List<ClusterDetail> clusters;
    private List<CustomerLabel> customerLabels;

    public SegmentationResponse() {
        super();
    }

    public SegmentationResponse(Map<String, Object> data) {
        super(data);
    }

    @Override
    protected void hydrate(Map<String, Object> data) {
        this.bestAlgorithm = getString(data, "best_algorithm");
        this.evaluationMetrics = getList(data, "evaluation_metrics").stream()
                .map(AlgorithmMetrics::fromMap)
                .collect(Collectors.toList());
        this.clusters = getList(data, "clusters").stream()
                .map(ClusterDetail::fromMap)
                .collect(Collectors.toList());
        this.customerLabels = getList(data, "customer_labels").stream()
                .map(CustomerLabel::fromMap)
                .collect(Collectors.toList());
    }

    public static SegmentationResponse fromMap(Map<String, Object> data) {
        return new SegmentationResponse(data);
    }
}

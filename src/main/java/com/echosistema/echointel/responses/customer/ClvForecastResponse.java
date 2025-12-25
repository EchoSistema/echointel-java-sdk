package com.echosistema.echointel.responses.customer;

import com.echosistema.echointel.responses.common.EchoIntelResponse;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public class ClvForecastResponse extends EchoIntelResponse {

    private String bestAlgorithm;
    private int horizonMonths;
    private Map<String, Object> evaluationMae;
    private List<ClvForecastCustomer> customers;

    public ClvForecastResponse() {
        super();
    }

    public ClvForecastResponse(Map<String, Object> data) {
        super(data);
    }

    @Override
    protected void hydrate(Map<String, Object> data) {
        this.bestAlgorithm = getString(data, "best_algorithm");
        this.horizonMonths = getInt(data, "horizon_months");
        this.evaluationMae = getMap(data, "evaluation_mae");
        this.customers = getList(data, "customers").stream()
                .map(ClvForecastCustomer::fromMap)
                .collect(Collectors.toList());
    }

    public static ClvForecastResponse fromMap(Map<String, Object> data) {
        return new ClvForecastResponse(data);
    }
}

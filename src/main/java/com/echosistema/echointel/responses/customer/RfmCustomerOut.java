package com.echosistema.echointel.responses.customer;

import com.echosistema.echointel.responses.common.EchoIntelResponse;
import lombok.Getter;

import java.util.Map;

@Getter
public class RfmCustomerOut extends EchoIntelResponse {

    private String customerId;
    private int recencyDays;
    private int frequency;
    private double monetaryTotal;
    private int recencyScore;
    private int frequencyScore;
    private int monetaryScore;
    private String rfmClass;
    private int rfmTotal;
    private String rfmTier;
    private String interpretation;

    public RfmCustomerOut() {
        super();
    }

    public RfmCustomerOut(Map<String, Object> data) {
        super(data);
    }

    @Override
    protected void hydrate(Map<String, Object> data) {
        this.customerId = getString(data, "customer_id");
        this.recencyDays = getInt(data, "recency_days");
        this.frequency = getInt(data, "frequency");
        this.monetaryTotal = getDouble(data, "monetary_total");
        this.recencyScore = getInt(data, "recency_score");
        this.frequencyScore = getInt(data, "frequency_score");
        this.monetaryScore = getInt(data, "monetary_score");
        this.rfmClass = getString(data, "rfm_class");
        this.rfmTotal = getInt(data, "rfm_total");
        this.rfmTier = getString(data, "rfm_tier");
        this.interpretation = getString(data, "interpretation");
    }

    public static RfmCustomerOut fromMap(Map<String, Object> data) {
        return new RfmCustomerOut(data);
    }
}

package com.echosistema.echointel.responses.pricing;

import com.echosistema.echointel.responses.common.EchoIntelResponse;
import lombok.Getter;

import java.util.Map;

@Getter
public class PriceOutcome extends EchoIntelResponse {

    private String productCode;
    private double currentPrice;
    private double recommendedPrice;
    private double expectedRevenueLift;
    private String interpretation;

    public PriceOutcome() {
        super();
    }

    public PriceOutcome(Map<String, Object> data) {
        super(data);
    }

    @Override
    protected void hydrate(Map<String, Object> data) {
        this.productCode = getString(data, "product_code");
        this.currentPrice = getDouble(data, "current_price");
        this.recommendedPrice = getDouble(data, "recommended_price");
        this.expectedRevenueLift = getDouble(data, "expected_revenue_lift");
        this.interpretation = getString(data, "interpretation");
    }

    public static PriceOutcome fromMap(Map<String, Object> data) {
        return new PriceOutcome(data);
    }
}

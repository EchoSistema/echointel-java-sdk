package com.echosistema.echointel.responses.inventory;

import com.echosistema.echointel.responses.common.EchoIntelResponse;
import lombok.Getter;

import java.util.Map;

@Getter
public class ProductDetails extends EchoIntelResponse {

    private String productCode;
    private String productName;
    private String category;

    public ProductDetails() {
        super();
    }

    public ProductDetails(Map<String, Object> data) {
        super(data);
    }

    @Override
    protected void hydrate(Map<String, Object> data) {
        this.productCode = getString(data, "product_code");
        this.productName = getString(data, "product_name");
        this.category = getString(data, "category");
    }

    public static ProductDetails fromMap(Map<String, Object> data) {
        return new ProductDetails(data);
    }
}

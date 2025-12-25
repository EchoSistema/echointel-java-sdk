package com.echosistema.echointel.responses.inventory;

import com.echosistema.echointel.responses.common.EchoIntelResponse;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public class InventoryHistoryResponse extends EchoIntelResponse {

    private List<DailyInventoryRecord> dailyInventory;
    private Map<String, Object> inventoryAnalysis;
    private Map<String, Object> inventoryAging;
    private ProductDetails productDetails;
    private ProcessingInfo processingInfo;

    public InventoryHistoryResponse() {
        super();
    }

    public InventoryHistoryResponse(Map<String, Object> data) {
        super(data);
    }

    @Override
    protected void hydrate(Map<String, Object> data) {
        this.dailyInventory = getList(data, "daily_inventory").stream()
                .map(DailyInventoryRecord::fromMap)
                .collect(Collectors.toList());
        this.inventoryAnalysis = getMap(data, "inventory_analysis");
        this.inventoryAging = getMap(data, "inventory_aging");
        this.productDetails = ProductDetails.fromMap(getMap(data, "product_details"));
        this.processingInfo = ProcessingInfo.fromMap(getMap(data, "processing_info"));
    }

    public static InventoryHistoryResponse fromMap(Map<String, Object> data) {
        return new InventoryHistoryResponse(data);
    }
}

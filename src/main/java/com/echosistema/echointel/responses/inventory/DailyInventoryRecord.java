package com.echosistema.echointel.responses.inventory;

import com.echosistema.echointel.responses.common.EchoIntelResponse;
import lombok.Getter;

import java.util.Map;

@Getter
public class DailyInventoryRecord extends EchoIntelResponse {

    private String date;
    private int inventoryLevel;
    private int salesQty;
    private int receivedQty;

    public DailyInventoryRecord() {
        super();
    }

    public DailyInventoryRecord(Map<String, Object> data) {
        super(data);
    }

    @Override
    protected void hydrate(Map<String, Object> data) {
        this.date = getString(data, "date");
        this.inventoryLevel = getInt(data, "inventory_level");
        this.salesQty = getInt(data, "sales_qty");
        this.receivedQty = getInt(data, "received_qty");
    }

    public static DailyInventoryRecord fromMap(Map<String, Object> data) {
        return new DailyInventoryRecord(data);
    }
}

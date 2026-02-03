package com.echosistema.echointel.responses.inventory;

import com.echosistema.echointel.responses.common.EchoIntelResponse;
import lombok.Getter;

import java.util.Map;

@Getter
public class ProcessingInfo extends EchoIntelResponse {

    private String startDate;
    private String endDate;
    private int totalDays;
    private double executionTimeSeconds;

    public ProcessingInfo() {
        super();
    }

    public ProcessingInfo(Map<String, Object> data) {
        super(data);
    }

    @Override
    protected void hydrate(Map<String, Object> data) {
        this.startDate = getString(data, "start_date");
        this.endDate = getString(data, "end_date");
        this.totalDays = getInt(data, "total_days");
        this.executionTimeSeconds = getDouble(data, "execution_time_seconds");
    }

    public static ProcessingInfo fromMap(Map<String, Object> data) {
        return new ProcessingInfo(data);
    }
}

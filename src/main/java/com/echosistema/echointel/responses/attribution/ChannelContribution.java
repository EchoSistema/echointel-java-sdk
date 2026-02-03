package com.echosistema.echointel.responses.attribution;

import com.echosistema.echointel.responses.common.EchoIntelResponse;
import lombok.Getter;

import java.util.Map;

@Getter
public class ChannelContribution extends EchoIntelResponse {

    private String channel;
    private int nEvents;
    private double rawCr;
    private double incrementalLift;
    private String description;

    public ChannelContribution() {
        super();
    }

    public ChannelContribution(Map<String, Object> data) {
        super(data);
    }

    @Override
    protected void hydrate(Map<String, Object> data) {
        this.channel = getString(data, "channel");
        this.nEvents = getInt(data, "n_events");
        this.rawCr = getDouble(data, "raw_cr");
        this.incrementalLift = getDouble(data, "incremental_lift");
        this.description = getString(data, "description");
    }

    public static ChannelContribution fromMap(Map<String, Object> data) {
        return new ChannelContribution(data);
    }
}

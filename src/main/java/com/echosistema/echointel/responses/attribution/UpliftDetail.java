package com.echosistema.echointel.responses.attribution;

import com.echosistema.echointel.responses.common.EchoIntelResponse;
import lombok.Getter;

import java.util.Map;

@Getter
public class UpliftDetail extends EchoIntelResponse {

    private String id;
    private double upliftScore;
    private int upliftDecile;

    public UpliftDetail() {
        super();
    }

    public UpliftDetail(Map<String, Object> data) {
        super(data);
    }

    @Override
    protected void hydrate(Map<String, Object> data) {
        this.id = getString(data, "id");
        this.upliftScore = getDouble(data, "uplift_score");
        this.upliftDecile = getInt(data, "uplift_decile");
    }

    public static UpliftDetail fromMap(Map<String, Object> data) {
        return new UpliftDetail(data);
    }
}

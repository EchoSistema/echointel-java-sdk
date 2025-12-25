package com.echosistema.echointel.responses.attribution;

import com.echosistema.echointel.responses.common.EchoIntelResponse;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public class UpliftResponse extends EchoIntelResponse {

    private double averageTreatmentEffect;
    private double treatmentCr;
    private double controlCr;
    private List<DecileSummary> decileSummary;
    private List<UpliftDetail> details;
    private String interpretation;

    public UpliftResponse() {
        super();
    }

    public UpliftResponse(Map<String, Object> data) {
        super(data);
    }

    @Override
    protected void hydrate(Map<String, Object> data) {
        this.averageTreatmentEffect = getDouble(data, "average_treatment_effect");
        this.treatmentCr = getDouble(data, "treatment_cr");
        this.controlCr = getDouble(data, "control_cr");
        this.interpretation = getString(data, "interpretation");
        this.decileSummary = getList(data, "decile_summary").stream()
                .map(DecileSummary::fromMap)
                .collect(Collectors.toList());
        this.details = getList(data, "details").stream()
                .map(UpliftDetail::fromMap)
                .collect(Collectors.toList());
    }

    public static UpliftResponse fromMap(Map<String, Object> data) {
        return new UpliftResponse(data);
    }
}

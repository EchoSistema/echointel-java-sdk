package com.echosistema.echointel.responses.journey;

import com.echosistema.echointel.responses.common.EchoIntelResponse;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public class SequenceResponse extends EchoIntelResponse {

    private List<Path> frequentPaths;
    private String interpretation;

    public SequenceResponse() {
        super();
    }

    public SequenceResponse(Map<String, Object> data) {
        super(data);
    }

    @Override
    protected void hydrate(Map<String, Object> data) {
        this.interpretation = getString(data, "interpretation");
        this.frequentPaths = getList(data, "frequent_paths").stream()
                .map(Path::fromMap)
                .collect(Collectors.toList());
    }

    public static SequenceResponse fromMap(Map<String, Object> data) {
        return new SequenceResponse(data);
    }
}

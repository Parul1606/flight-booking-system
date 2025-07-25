package com.fbs.central_api.models;

import lombok.Data;
import java.util.List;
@Data
public class GeminiApiResponse {
    private List<ResponseCandidate> candidates;
    private String modelVersion;
    private String responseId;
}

package com.fbs.central_api.models;

import lombok.Data;
import java.util.List;

@Data
public class GeminiApiRequest {
    private List<RequestContent> contents;
}

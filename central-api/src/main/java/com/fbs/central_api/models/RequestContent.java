package com.fbs.central_api.models;

import lombok.Data;
import java.util.List;

@Data
public class RequestContent {
    private List<RequestPart> parts;
}

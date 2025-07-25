package com.fbs.central_api.models;

import lombok.Data;
import java.util.List;

@Data
public class ResponseContent {
    private List<ResponsePart> parts;
    private String role;
}

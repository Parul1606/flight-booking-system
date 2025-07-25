package com.fbs.central_api.models;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseCandidate {
    private ResponseContent content;
    private String finishReason;
    private double avgLogprobs;
}
package com.fbs.central_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatMappingDto {
    String className;

    String range;  // 1-20

    int basePrice;

    int windowPrice;

    int totalWindow;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}

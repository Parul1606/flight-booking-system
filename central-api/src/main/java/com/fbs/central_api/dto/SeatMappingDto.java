package com.fbs.central_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatMappingDto {
    String className;

    String range;  // 1-20

    int basePrice;

    int windowPrice;

    int totalWindow;
}

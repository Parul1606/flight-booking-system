package com.fbs.central_api.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatMapping {
    String className;

    String range;  // 1-20

    int basePrice;

    int windowPrice;

    int totalWindow;
}

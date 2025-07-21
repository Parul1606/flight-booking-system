package com.fbs.central_api.dto;

import com.fbs.central_api.models.Airline;
import com.fbs.central_api.models.AppUser;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AirlineRegistrationReqDto {
    AppUser admin;
    Airline airline;
}

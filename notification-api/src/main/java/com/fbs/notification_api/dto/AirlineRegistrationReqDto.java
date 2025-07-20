package com.fbs.notification_api.dto;

import com.fbs.notification_api.models.Airline;
import com.fbs.notification_api.models.AppUser;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AirlineRegistrationReqDto {
    AppUser appAdmin;
    Airline airline;
}

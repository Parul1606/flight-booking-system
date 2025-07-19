package com.fbs.central_api.utility;

import com.fbs.central_api.dto.AirlineRegistrationDto;
import com.fbs.central_api.enums.UserStatus;
import com.fbs.central_api.enums.UserType;
import com.fbs.central_api.models.AppUser;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class Mapper {

    public AppUser mapAirlineDetailsDtoToAppUser(AirlineRegistrationDto airlineDetails){
        AppUser airlineAdmin = new AppUser();
        airlineAdmin.setEmail(airlineDetails.getEmail());
        airlineAdmin.setName(airlineDetails.getAirlineName() + " Admin");
        airlineAdmin.setUserType(UserType.AIRLINE_ADMIN.toString());
        airlineAdmin.setNumber(airlineDetails.getContactNumber());
        airlineAdmin.setVerified(false);
        airlineAdmin.setStatus(UserStatus.INACTIVE.toString());
        airlineAdmin.setPassword(airlineAdmin.getPassword());
        airlineAdmin.setCreatedAt(LocalDateTime.now());
        airlineAdmin.setUpdatedAt(LocalDateTime.now());
        return airlineAdmin;
    }
}

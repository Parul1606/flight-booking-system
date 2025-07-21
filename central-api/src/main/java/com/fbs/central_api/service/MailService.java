package com.fbs.central_api.service;

import com.fbs.central_api.configurations.NotificationApiConnector;
import com.fbs.central_api.dto.AirlineRegistrationReqDto;
import com.fbs.central_api.models.Airline;
import com.fbs.central_api.models.AppUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class MailService {

    NotificationApiConnector notificationApiConnector;

    @Autowired
    public MailService(NotificationApiConnector notificationApiConnector){
        this.notificationApiConnector = notificationApiConnector;
    }

    /*
    This function is responsible for sending mail to all the system admins regarding airline registration
     */
    public void mailSystemAdminForAirlineRegistration(List<AppUser> systemAdmins, Airline airline){
        // we will apply one loop over all the system admins
        for (AppUser systemAdmin : systemAdmins){
            // we need to call notification api one by one for all the systemAdmins
            // so to call notification api from central api we require -> notificationApiConnector class
            AirlineRegistrationReqDto airlineRegistrationReqDto = new AirlineRegistrationReqDto();
            airlineRegistrationReqDto.setAirline(airline);
            airlineRegistrationReqDto.setAdmin(systemAdmin);
            notificationApiConnector.notifySystemAdminForAirlineRegistration(airlineRegistrationReqDto);
        }
    }

}

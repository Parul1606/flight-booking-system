package com.fbs.notification_api.controllers;

/*
This particular controller is created to send notification mails to App Admin
*/

import com.fbs.notification_api.dto.AirlineRegistrationReqDto;
import com.fbs.notification_api.service.AppAdminNotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/notify/appadmin")
public class AppAdminNotificationController {

    AppAdminNotificationService appAdminNotificationService;

    @Autowired
    public AppAdminNotificationController(AppAdminNotificationService appAdminNotificationService){
        this.appAdminNotificationService = appAdminNotificationService;
    }

    /*
    to send registration request email of a airline to application admin.
     */
    @PutMapping("/airline-registration")
    public void airlineRegistrationRequestNotification(@RequestBody AirlineRegistrationReqDto airlineRegistrationReqDto){
        log.info("Inside airlineRegistrationRequestNotification with payload: " + airlineRegistrationReqDto.toString());
        // from here we need to call AppAdminNotificationService
        // we will be calling service layer which will be sending email to the applicaiton admin
        appAdminNotificationService.sendAirlineRegistrationRequestNotification(airlineRegistrationReqDto);
    }
}

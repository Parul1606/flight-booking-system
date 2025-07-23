package com.fbs.central_api.connectors;

import com.fbs.central_api.dto.AirlineRegistrationReqDto;
import com.fbs.central_api.models.Airline;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Configuration
public class NotificationApiConnector {

    RestTemplate restTemplate;

    @Autowired
    public NotificationApiConnector(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Value("${notification.api.url}")
    String notificationBaseUrl;

    public void notifySystemAdminForAirlineRegistration(AirlineRegistrationReqDto airlineRegistrationReqDto){
        String url = notificationBaseUrl + "/appadmin/airline-registration";
        RequestEntity request = RequestEntity.put(url).body(airlineRegistrationReqDto);
        ResponseEntity<Object> resp = restTemplate.exchange(url, HttpMethod.PUT, request, Object.class);
    }

    public void notifyAcceptRequestToAirlineAdmin(Airline airline){
        String url = notificationBaseUrl + "/airline/admin/accept-request";
        RequestEntity request = RequestEntity.put(url).body(airline);
        ResponseEntity<Object> resp = restTemplate.exchange(url, HttpMethod.PUT, request, Object.class);
    }

}

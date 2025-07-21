package com.fbs.central_api.configurations;

import com.fbs.central_api.dto.AirlineRegistrationReqDto;
import com.fbs.central_api.models.Airline;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpServerErrorException;
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

    public void notifySystemAdminForAirlineRegistration(AirlineRegistrationReqDto dto) {
        try {
            String url = notificationBaseUrl + "/appadmin/airline-registration";
            RequestEntity<AirlineRegistrationReqDto> request = RequestEntity.put(url).body(dto);
            restTemplate.exchange(url, HttpMethod.PUT, request, Object.class);
        } catch (HttpServerErrorException e) {
            log.error("Notification API returned 500: {}", e.getResponseBodyAsString());
        } catch (Exception e) {
            log.error("Error calling Notification API", e);
        }
    }

}

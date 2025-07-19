package com.fbs.central_api.connectors;

import com.fbs.central_api.models.AppUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/*
* Purpose of this class is to connect with db api endpoints.
*/
@Component
@Slf4j
public class DBApiConnector {

    @Value("${db.api.url}")
    String dbApiBaseUrl;  // for this vairable pick the value for application.properties

    public AppUser callCreateUserEndpoint(AppUser user){
        log.info("Inside callCreateUserEndpoint method with user object: " + user.toString());
        // 1. Create a url that you want to call
        String url = dbApiBaseUrl + "/user/create";
        // 2. we want to tell what rest method we want to use and what request body we want to pass
        //so for that we use requestEntity class
        RequestEntity request = RequestEntity.post(url).body(user);
        log.info("Created request: " + request.toString());
        // 3. Hit or make the request on postman to do this step,
        // we click send button but here we are going to use a class called RestTemplate
        RestTemplate restTemplate = new RestTemplate();
        log.info("Calling dbApi create user endpoint");
        // send button (postman) -> restTemplate class exchange method
        ResponseEntity<AppUser> response = restTemplate.exchange(url, HttpMethod.POST, request, AppUser.class);
        log.info("Response: " + response.toString());
        return response.getBody();
    }
}

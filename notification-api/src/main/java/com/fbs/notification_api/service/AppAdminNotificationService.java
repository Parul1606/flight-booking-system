package com.fbs.notification_api.service;

import com.fbs.notification_api.dto.AirlineRegistrationReqDto;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@Slf4j
public class AppAdminNotificationService {

    JavaMailSender javaMailSender;
    TemplateEngine templateEngine;

    @Autowired
    public AppAdminNotificationService(JavaMailSender javaMailSender,
                                       TemplateEngine templateEngine){
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

    public void sendAirlineRegistrationRequestNotification(AirlineRegistrationReqDto airlineRegistrationReqDto){
        // To send the mail we require javaMailSender Library Object
        // to send the amil we need to set mail content
        // to set mailContent there is a class called Mimemessage so, we will set all the mail content itno this mimeMessage class
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        // to set the values inside the Mimemessage we need MimeMessage helper class object
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        // now by using the mimemessage helper class object we will be setting all the mail content
        Context context = new Context();  // This class object help us to set values of the variables present inside the html template
        context.setVariable("airlineName", airlineRegistrationReqDto.getAirline().getAirlineName());
        context.setVariable("companyName", airlineRegistrationReqDto.getAirline().getCompanyName());
        context.setVariable("website", airlineRegistrationReqDto.getAirline().getWebsite());
        context.setVariable("employees", airlineRegistrationReqDto.getAirline().getEmployees());
        context.setVariable("totalFlights", airlineRegistrationReqDto.getAirline().getTotalFlights());
        context.setVariable("airlineAdminName", airlineRegistrationReqDto.getAirline().getAdmin().getName());
        context.setVariable("adminEmail", airlineRegistrationReqDto.getAirline().getAdmin().getEmail());
        context.setVariable("requestedTime", airlineRegistrationReqDto.getAirline().getCreatedAt().toString());
        // we need to load the html template inside this function and populate the values of all the variable
        // so to load html template inside this function we will use library called thymeleaf.
        // to load html we require object of TemplateEngine class (Present inside your thymeleaf)
        //  I want to get that thymeleaf object from springboot. So, we need to create a bean of thymeleaf class and store it in the ioc container
        String htmlContent = templateEngine.process("airline-registration-request", context); // we use templateEngine.process method to load the template inside our java function

        /*
        we have learned abt 2 exceptions checked & unchecked
        so here we are getting compile time checked exceptions so we need to keep our code in try n catch
        */
        try{
            mimeMessageHelper.setTo(airlineRegistrationReqDto.getAppAdmin().getEmail());  // by using this method we will be setting the mailId to whom we want to send email... this is how we will be sending notification to the appAdmin
            mimeMessageHelper.setSubject(airlineRegistrationReqDto.getAirline().getAirlineName() + " Registration Request");
            //mimeMessageHelper.setText("Hey, there is the new Registration request");  // -> this line is sending normal email with normal body
            mimeMessageHelper.setText(htmlContent,true);  // when we need to send html content in this setText method we will pass another boolean parameter
            // if we are passing boolean parameter as true that means im passing html content.
        } catch (Exception e){
            log.error(e.getMessage());
        }
        //by calling below method we will be sending email to the users.
        javaMailSender.send(mimeMessage);
    }
}

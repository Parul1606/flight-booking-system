package com.fbs.notification_api.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.Thymeleaf;

import java.util.HashMap;
import java.util.Properties;

@Configuration
public class AppConfiguration {

    /*
    In SpringBoot we start calling object as Beans.
    @Bean annotation will ask springboot to save the hashmap object inside the IOC container.
    and whenever someone will try to autowire hashmap object from the ioc container
    */
    @Bean
    public HashMap<Integer, Integer> generateHashMap(){
        return new HashMap<>();
    }

    /*
    JavaMailSender is the class or library that will help our spring Boot code to send mails to emailIds
    this is how we are creating the javaMailSender object and after creating this object spring Boot will store/save it in ioc container
     */
    @Bean
    public JavaMailSender generateJavaMailSender(){
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        // Our Backend api needs to send mail to the suer for sending mail
        // we need to provide credentials of the email id
        // by using which our backend will send mail to  the users of our application.
        javaMailSender.setHost("smtp.gmail.com");  // For now email which I'm using belongs to gamil so, the host will be smtp.gmail.com
        javaMailSender.setPort(587);  // generally to send mail from our computer we require some port number so, the port number which we will use is 587
        javaMailSender.setUsername("accioshoppingwebsite@gmail.com");  // we will be sending email so, by what email our spring application will send mail to the users
        javaMailSender.setPassword("relcfdwhahhcvokv");   // password of our email.... It is app password, not actual password
        Properties props = javaMailSender.getJavaMailProperties();  // it is a properties class with the help of which we can set java mail sender properties
        props.put("mail.smtp.auth", "true");  // our springboot api will connect with gmail to send email via password so, mai.smtp.auth is true
        props.put("mail.smtp.starttls.enable", "true"); // this property we are setting for secure connection
        return javaMailSender;
    }

    /*
    below method will create a bean of template engine class  else and we will store it in inside the IOC contianer.

    */
    @Bean
    public TemplateEngine getThymeleafBean(){
        return new TemplateEngine();
    }
}

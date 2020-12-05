package com.bank.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {

    @Value("${spring.mail.host}")
    private String host;
    @Value("${spring.mail.username}")
    private String username;
    @Value("${spring.mail.password}")
    private String password;
    @Value("${spring.mail.port}")
    private int port;
    @Value("${spring.mail.debug}")
    private String debug;
    private final int timeout = 5000;
    private final int writeTimeout = 5000;
    private final int connectionTimeout = 5000;
    @Value("${spring.mail.transport.protocol}")
    private String protocol;
    @Value("${spring.mail.smtp.auth}")
    private String auth;
    private final String starttlsEnable = "true";


    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(port);

        mailSender.setUsername(username);
        mailSender.setPassword(password);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", protocol);
        props.put("mail.smtp.auth", auth);
        props.put("mail.smtp.starttls.enable", starttlsEnable);
        props.put("mail.debug", debug);
        props.put("mail.properties.mail.smtp.timeout", timeout);
        props.put("mail.properties.mail.smtp.writetimeout", writeTimeout);
        props.put("mail.properties.mail.smtp.connectiontimeout", connectionTimeout);

        return mailSender;
    }
}

package com.bank.config;

import com.bank.util.Constant;
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
    @Value("${spring.mail.transport.protocol}")
    private String protocol;
    @Value("${spring.mail.smtp.auth}")
    private String auth;


    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(port);

        mailSender.setUsername(username);
        mailSender.setPassword(password);

        Properties props = mailSender.getJavaMailProperties();
        props.put(Constant.Mail.MAIL_TRANSPORT_PROTOCOL, protocol);
        props.put(Constant.Mail.MAIL_SMTP_AUTH, auth);
        props.put(Constant.Mail.MAIL_SMTP_STARTTLS, Constant.Mail.STARTTLS_ENABLE);
        props.put(Constant.Mail.MAIL_DEBUG, debug);
        props.put(Constant.Mail.MAIL_TIMEOUT, Constant.Mail.TIMEOUT);
        props.put(Constant.Mail.MAIL_WRITE_TIMEOUT, Constant.Mail.WRITE_TIMEOUT);
        props.put(Constant.Mail.MAIL_CONNECTION_TIMEOUT, Constant.Mail.CONNECTION_TIMEOUT);

        return mailSender;
    }
}

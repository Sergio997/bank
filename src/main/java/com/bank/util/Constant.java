package com.bank.util;

public class Constant {

    public static class Mail {
        public static final int TIMEOUT = 5000;
        public static final int WRITE_TIMEOUT = 5000;
        public static final int CONNECTION_TIMEOUT = 5000;
        public static final String STARTTLS_ENABLE = "true";
        public static final String MAIL_TRANSPORT_PROTOCOL = "mail.transport.protocol";
        public static final String MAIL_SMTP_AUTH = "mail.smtp.auth";
        public static final String MAIL_SMTP_STARTTLS = "mail.smtp.starttls.enable";
        public static final String MAIL_DEBUG = "mail.debug";
        public static final String MAIL_TIMEOUT = "mail.properties.mail.smtp.timeout";
        public static final String MAIL_WRITE_TIMEOUT = "mail.properties.mail.smtp.writetimeout";
        public static final String MAIL_CONNECTION_TIMEOUT = "mail.properties.mail.smtp.connectiontimeout";
    }

    public static class Endpoint {
        public static final String CONFIRM_ENDPOINT = "http://localhost:4001/bank/auth/confirm-account/";
        public static final String FORGOT_PASSWORD_ENDPOINT = "http://localhost:4001/bank/auth/change-password/";
    }

    public static class Message {
        public static final String COMPLETE_SUBJECT = "Complete Registration!";
        public static final String CONFIRM_TEXT = "To confirm your account, please click here : ";
        public static final String FORGOT_PASSWORD_SUBJECT = "You forgot password!?";
        public static final String FORGOT_PASSWORD_TEXT = "If you forgot password, please click here : ";
    }

    public static class Token {
        public static final String AUTHORIZATION = "Authorization!";
    }
}

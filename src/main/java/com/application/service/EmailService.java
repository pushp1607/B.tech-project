package com.application.service;

import com.application.dto.EmailData;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.HtmlEmail;

public class EmailService {
    public String setAlertMail(EmailData emailData) {
        String senderEmailId = emailData.getSenderEmail();
        String password = emailData.getPassword();
        String emailBody = emailData.getEmailBody();
        String recipient = emailData.getRecipientEmail();
        try {
            Email email = new HtmlEmail();
            email.setHostName("smtp.gmail.com");
            email.setSmtpPort(465);
            email.setSSLOnConnect(true);
            email.setAuthenticator(new DefaultAuthenticator(senderEmailId, password));
            email.setFrom(senderEmailId);
            email.setSubject("Alert Email");
            ((HtmlEmail) email).setHtmlMsg(emailBody);
            email.addTo(recipient);
            email.send();
            return "Alert Mail Sent successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "failed to send alert";
        }

    }
    public String setAlertMail2(String inputURL) {
        String senderEmailId = "";
        String password = "";
        String emailBody = "unrecognized url : "+ inputURL;
        String recipient = "";
        try {
            Email email = new HtmlEmail();
            email.setHostName("smtp.gmail.com");
            email.setSmtpPort(465);
            email.setSSLOnConnect(true);
            email.setAuthenticator(new DefaultAuthenticator(senderEmailId, password));
            email.setFrom(senderEmailId);
            email.setSubject("Alert Email");
            ((HtmlEmail) email).setHtmlMsg(emailBody);
            email.addTo(recipient);
            email.send();
            return "Mail Sent successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "failed to send mail";
        }

    }
}
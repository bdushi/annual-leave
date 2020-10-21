package de.dlh.lhind.annualleave.service;

import org.springframework.mail.MailException;

import java.io.IOException;
import java.util.Map;

public interface EmailService {
    void sendSimpleMessage(String to, String subject, String text) throws MailException;

    void sendSimpleMessageUsingTemplate(String to, String subject,String messages);

    void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment);

    <T> void sendMessageUsingThymeleafTemplate(String to, String subject, Map<String, T> templateModel) throws IOException;

    <T> void  sendMessageUsingFreemarkerTemplate(String to, String subject, Map<String, T> templateModel) throws IOException;
}

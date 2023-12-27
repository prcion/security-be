package com.findork.preclinical.integrations;

import com.findork.preclinical.features.account.User;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.net.URI;
import java.nio.charset.StandardCharsets;

@Service
@Slf4j
@RequiredArgsConstructor
public class ThymeleafMailService {

    private final JavaMailSenderImpl javaMailSender;
    private final SpringTemplateEngine templateEngine;
    @Value("${spring.mail.from}")
    private String from;

    public void sendEmail(String to, String subject, String content, boolean isMultipart, boolean isHtml) {

        log.debug("Send e-mail[multipart '{}' and html '{}'] to '{}' with subject '{}' and content={}", isMultipart, isHtml, to, subject, content);

        // Prepare message using a Spring helper
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, isMultipart, StandardCharsets.UTF_8.name());
            message.setTo(to);
            message.setFrom(from);
            message.setSubject(subject);
            message.setText(content, isHtml);
            javaMailSender.send(mimeMessage);
            log.debug("Sent e-mail to User [{}]", to);
        } catch (Exception e) {
            log.warn("E-mail could not be sent to user [{}], exception is: {}", to, e.getMessage());
            throw new MailSendException(e.getMessage());
        }
    }

    public void sendActivationEmail(User user, URI origin, String token) {
        log.debug("Sending activation e-mail to '{}'", user.getEmail());
        var context = new Context();
        context.setVariable("confirmationURL", origin + "/confirmation-required/" + token);
        String content = templateEngine.process("registrationConfirm", context);
        sendEmail(user.getEmail(), "Activation email", content, false, true);
    }
}

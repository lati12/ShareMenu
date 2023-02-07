package com.server.sharemenu.services;

import com.server.sharemenu.common.EmailDetails;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

@Service
public class EmailService {

    public static final String RECIPIENT = "recipient";
    private final Configuration configuration;
    private final JavaMailSender javaMailSender;


    public EmailService(Configuration configuration, JavaMailSender javaMailSender) {
        this.configuration = configuration;
        this.javaMailSender = javaMailSender;
    }

    public CompletableFuture<Boolean> sendMailFuture(EmailDetails recipient, String template)
            throws MessagingException, TemplateException, IOException {
        return CompletableFuture.supplyAsync(() -> {
            try {
                MimeMessage mimeMessage = javaMailSender.createMimeMessage();

                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
                helper.setSubject(recipient.getSubject());
                helper.setTo(recipient.getToInternetAddress());
                String emailContent = getEmailContentConfirmation(recipient, template);
                helper.setText(emailContent, true);
                javaMailSender.send(mimeMessage);
                return true;
            } catch (Exception e) {
                throw new CompletionException(e);
            }
        });
    }

    String getEmailContentConfirmation(EmailDetails recipientConfirmation, String template)
            throws IOException, TemplateException {
        StringWriter stringWriter = new StringWriter();
        Map<String, Object> model = new HashMap<>();
        model.put(RECIPIENT, recipientConfirmation);
        configuration.getTemplate(template).process(model, stringWriter);
        return stringWriter.getBuffer().toString();
    }
}

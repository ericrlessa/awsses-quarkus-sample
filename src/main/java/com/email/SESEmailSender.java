package com.email;

import com.email.Email;
import com.email.EmailNotificationService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import software.amazon.awssdk.services.ses.SesClient;

@ApplicationScoped
public class SESEmailSender implements EmailNotificationService {
    @Inject
    SesClient ses;
    public void send(Email email) {
        final String messageId = ses.sendEmail(req -> req
                .source(email.getFrom().value())
                .destination(d -> d.toAddresses(email.getTo().value()))
                .message(msg -> msg
                        .subject(sub -> sub.data(email.getSubject()))
                        .body(b -> b.text(txt -> txt.data(email.getBody()))))).messageId();
    }
}

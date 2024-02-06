package com.email;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/email")
public class EmailResource {
    @Inject
    EmailNotificationService emailNotificationService;
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from RESTEasy Reactive";
    }
    @Path("/{from}/{to}/{subject}/{body}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public void sendEmail(@PathParam("from") String from,
    @PathParam("to") String to,
    @PathParam("subject") String subject,
    @PathParam("body") String body)
    {
        EmailAddress fromEmail = new EmailAddress(from);
        EmailAddress toEmail = new EmailAddress(to);

        Email email = new Email(fromEmail,
                toEmail,
                subject,
                body);

        emailNotificationService.send(email);
    }

}

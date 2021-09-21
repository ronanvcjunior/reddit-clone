package com.ronan.redditclone.domain;

public class NotificationEmail {
    private String subject;
    private String recipient;
    private String body;

    public NotificationEmail() {
    }

    public NotificationEmail(String subject, String recipient, String body) {
        this.subject = subject;
        this.recipient = recipient;
        this.body = body;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getRecipient() {
        return this.recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getBody() {
        return this.body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public NotificationEmail subject(String subject) {
        setSubject(subject);
        return this;
    }

    public NotificationEmail recipient(String recipient) {
        setRecipient(recipient);
        return this;
    }

    public NotificationEmail body(String body) {
        setBody(body);
        return this;
    }
}
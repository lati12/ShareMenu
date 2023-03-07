package com.server.sharemenu.common;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * The class serves to package information for sending an email to a registered user.*
 */
public class EmailDetails {
    private String hash;
    protected String subject;
    protected Collection<String> toList;
    protected Collection<String> ccList;
    protected String text;
    protected String email;

    public EmailDetails(String hash, List<String> singletonList, String subject, String text, String email) {
        this.hash = hash;
        this.subject = subject;
        this.toList = singletonList;
        this.text = text;
        this.email = email;
    }

    public InternetAddress[] getToInternetAddress() throws AddressException {
        List<InternetAddress> list = new ArrayList<>();
        for (String email : toList) {
            InternetAddress internetAddress = new InternetAddress(email);
            list.add(internetAddress);
        }
        return list.toArray(new InternetAddress[0]);
    }

    public InternetAddress[] getCcInternetAddress() throws AddressException {
        List<InternetAddress> list = new ArrayList<>();
        for (String email : ccList) {
            InternetAddress internetAddress = new InternetAddress(email);
            list.add(internetAddress);
        }
        return list.toArray(new InternetAddress[0]);
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Collection<String> getToList() {
        return toList;
    }

    public void setToList(Collection<String> toList) {
        this.toList = toList;
    }

    public Collection<String> getCcList() {
        return ccList;
    }

    public void setCcList(Collection<String> ccList) {
        this.ccList = ccList;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

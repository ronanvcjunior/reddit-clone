package com.ronan.redditclone.domain;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationEmail implements Serializable {
    private static final long serialVersionUID = 1L;
    private String subject;
    private String recipient;
    private String body;
}
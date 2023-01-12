package com.magellan.user;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class LoginParameterBean {
    @XmlElement
    public String username;
    @XmlElement public String password;
}
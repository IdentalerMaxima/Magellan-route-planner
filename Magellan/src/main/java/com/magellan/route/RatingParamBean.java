package com.magellan.route;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RatingParamBean {
    @XmlElement public String username;
    @XmlElement public String comment;
    @XmlElement public int rating;

}

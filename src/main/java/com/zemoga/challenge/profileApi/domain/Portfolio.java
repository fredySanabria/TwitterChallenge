package com.zemoga.challenge.profileApi.domain;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter(value = AccessLevel.PACKAGE)
@Getter
@Entity
public class Portfolio {
    @Id
    @GeneratedValue
    @Column(name = "idportfolio")
    private int id;
    private String description;
    private String imageUrl;
    private String twitterUserName;
    private String title;
}

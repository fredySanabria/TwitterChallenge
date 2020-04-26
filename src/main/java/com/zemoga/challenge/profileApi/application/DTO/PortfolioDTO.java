package com.zemoga.challenge.profileApi.application.DTO;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PortfolioDTO {
    private int id;
    private String description;
    private String imageUrl;
    private String twitterUserName;
    private String title;
}

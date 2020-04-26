package com.zemoga.challenge.profileApi.application.DTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class Profile {
    private PortfolioDTO portfolioDTO;
    private List<TwitterDTO> twitterList;
}

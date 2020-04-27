package com.zemoga.challenge.profileApi.application;

import com.zemoga.challenge.profileApi.application.DTO.PortfolioDTO;
import com.zemoga.challenge.profileApi.application.DTO.Profile;
import com.zemoga.challenge.profileApi.application.DTO.TwitterDTO;
import com.zemoga.challenge.profileApi.exception.TwitterListNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import twitter4j.TwitterException;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProfileService {
    @Autowired
    PortfolioService portfolioService;

    @Autowired
    TwitterService twitterService;

    public Profile getProfile(int id){
        PortfolioDTO portfolio = portfolioService.getPortfolio(id);
        return mapToDto(portfolio);
    }

    public List<Profile> findAllProfiles(){
        List<PortfolioDTO> portfolios = portfolioService.getAllPortfolios();
        return portfolios.stream().map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public int createProfile(Profile profile) {
        return portfolioService.createPortfolio(profile.getPortfolioDTO());
    }

    public void updateProfile(Profile profile) {
        portfolioService.updatePortfolio(profile.getPortfolioDTO());
    }

    public void deleteProfile(int id) {
        portfolioService.deletePortfolio(id);
    }

    private Profile mapToDto(PortfolioDTO portfolio) {
        try {
            return Profile.builder()
                    .portfolioDTO(portfolio)
                    .twitterList(getTwitterList(portfolio))
                    .build();
        } catch (TwitterException e) {
            throw new TwitterListNotFoundException("Error Getting all users", e);
        }
    }

    private List<TwitterDTO> getTwitterList(PortfolioDTO portfolio) throws TwitterException {
        return twitterService.getTwitterList(portfolio.getTwitterUserName());
    }
}

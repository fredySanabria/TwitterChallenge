package com.zemoga.challenge.profileApi.application;

import com.zemoga.challenge.profileApi.application.DTO.PortfolioDTO;
import com.zemoga.challenge.profileApi.application.DTO.Profile;
import com.zemoga.challenge.profileApi.application.DTO.TwitterDTO;
import com.zemoga.challenge.profileApi.exception.TwitterListNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import twitter4j.TwitterException;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class ProfileServiceTest {

    @Mock
    PortfolioService portfolioService;

    @Mock
    TwitterService twitterService;

    @InjectMocks
    ProfileService service;

    private List<TwitterDTO> twitterList;
    private PortfolioDTO portfolio;

    @BeforeEach
    public void init() {
        TwitterDTO item1 = TwitterDTO.builder().id(3).text("test text").build();
        twitterList = new ArrayList<>();
        twitterList.add(item1);
        twitterList.add(item1);
        portfolio = PortfolioDTO.builder()
                .id(4)
                .twitterUserName("test text")
                .title("Test tile")
                .imageUrl("image Text")
                .description("description test")
                .build();
    }

    @Test
    public void GetProfileFromExistingIdSuccessfully() throws TwitterException {
        final String userName = "TestTwitterName";
        final int id = 4;
        when(twitterService.getTwitterList(anyString())).thenReturn(twitterList);
        when(portfolioService.getPortfolio(anyInt())).thenReturn(portfolio);
        Profile profile = service.getProfile(id);
        assertThat(profile.getTwitterList()).isNotNull();
        assertThat(profile.getTwitterList().size()).isGreaterThan(0);
    }

    @Test()
    public void GetTwitterListThrowsExceptionWhenListIsNull() throws TwitterException {
        Assertions.assertThrows(TwitterListNotFoundException.class, () -> {
            final String userName = "TestTwitterName";
            final int id = 4;
            when(twitterService.getTwitterList(anyString())).thenThrow(TwitterException.class);
            when(portfolioService.getPortfolio(anyInt())).thenReturn(portfolio);
            Profile profile = service.getProfile(id);
        });
    }
}

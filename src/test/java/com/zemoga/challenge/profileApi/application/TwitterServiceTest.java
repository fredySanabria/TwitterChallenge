package com.zemoga.challenge.profileApi.application;

import com.zemoga.challenge.profileApi.application.DTO.TwitterDTO;
import com.zemoga.challenge.profileApi.exception.TwitterListNotFoundException;
import com.zemoga.challenge.profileApi.repository.TwitterReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import twitter4j.TwitterException;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class TwitterServiceTest {
    @Mock
    private TwitterReader reader;

    @InjectMocks
    TwitterService service;

    private List<TwitterDTO> twitterList;

    @BeforeEach
    public void init() {
        TwitterDTO item1 = TwitterDTO.builder().id(3).text("test text").build();
        twitterList = new ArrayList<>();
        twitterList.add(item1);
        twitterList.add(item1);
    }

    @Test
    public void GetTwitterListReturnsValidListSuccessfully() throws TwitterException {
        final String userName = "TestTwitterName";
        when(reader.getTwitterList(anyString())).thenReturn(twitterList);
        List<TwitterDTO> twitterList = service.getTwitterList(userName);
        assertThat(twitterList.size()).isNotNull();
        assertThat(twitterList.size()).isGreaterThan(0);
    }

    @Test()
    public void GetTwitterListThrowsExceptionWhenListIsNull(){
        Assertions.assertThrows(TwitterListNotFoundException.class, () -> {
            final String userName = "TestTwitterName";
            when(reader.getTwitterList(anyString())).thenReturn(null);
            List<TwitterDTO> twitterList = service.getTwitterList(userName);
        });
    }
}

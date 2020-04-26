package com.zemoga.challenge.profileApi.repository.impl;

import com.zemoga.challenge.profileApi.application.DTO.TwitterDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.json.DataObjectFactory;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class TwitterReaderApiClientTest {

    @Mock
    Twitter twitter;

    @Mock
    ResponseList<Status> responseList;

    @InjectMocks
    TwitterReaderApiClient apiClient;

    List<Status> statusList = new ArrayList<>();
    @BeforeEach
    void setUp() throws TwitterException {

        Status status1 = DataObjectFactory.createStatus("{\n" +
                "\t\"user\":{\"id\": 270673659},\n" +
                "    \"text\": \"@BrutalTruth0 I’m assuming you had no Septa to teach you chivalry, for no true knight would speak to me thus.\"\n" +
                "}");
        Status status2 = DataObjectFactory.createStatus("{\n" +
                "\t\"user\":{\"id\": 270673659},\n" +
                "    \"text\": \"@BrutalTruth0 I’m assuming you had no Septa to teach you chivalry, for no true knight would speak to me thus.\"\n" +
                "}");
        Status status3 = DataObjectFactory.createStatus("{\n" +
                "\t\"user\":{\"id\": 270673659},\n" +
                "    \"text\": \"@BrutalTruth0 I’m assuming you had no Septa to teach you chivalry, for no true knight would speak to me thus.\"\n" +
                "}");
        statusList.add(status1);
        statusList.add(status2);
        statusList.add(status3);
    }

    @Test
    void getTwitterListFromExistingName() throws TwitterException {

        when(twitter.getUserTimeline(anyString())).thenReturn(responseList);
        when(responseList.stream()).thenReturn(statusList.stream());
        List<TwitterDTO> twitterList = apiClient.getTwitterList("testName");
        Assertions.assertThat(twitterList).isNotNull();
    }
}
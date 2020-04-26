package com.zemoga.challenge.profileApi.repository.impl;

import com.zemoga.challenge.profileApi.application.DTO.TwitterDTO;
import com.zemoga.challenge.profileApi.repository.TwitterReader;
import org.springframework.stereotype.Component;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class TwitterReaderApiClient implements TwitterReader {
    Twitter twitter = TwitterFactory.getSingleton();
    private static Function<Status, TwitterDTO> mapToDto = item -> TwitterDTO.builder()
            .id(item.getUser().getId())
            .text(item.getText())
            .build();

    @Override
    public List<TwitterDTO> getTwitterList(String screenName) throws TwitterException {
        return getTimeLine(screenName);
    }

    private List<TwitterDTO> getTimeLine(String screenName) throws TwitterException {
        return twitter.getUserTimeline(screenName)
                .stream()
                .map(item -> mapToDto.apply(item))
                .limit(5)
                .collect(Collectors.toList());
    }
}

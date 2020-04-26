package com.zemoga.challenge.profileApi.repository;

import com.zemoga.challenge.profileApi.application.DTO.TwitterDTO;
import twitter4j.TwitterException;

import java.util.List;

public interface TwitterReader {
    List<TwitterDTO> getTwitterList(String screenName) throws TwitterException;
}

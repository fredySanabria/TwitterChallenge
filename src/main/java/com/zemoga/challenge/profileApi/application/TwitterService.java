package com.zemoga.challenge.profileApi.application;

import com.zemoga.challenge.profileApi.application.DTO.TwitterDTO;
import com.zemoga.challenge.profileApi.exception.TwitterListNotFoundException;
import com.zemoga.challenge.profileApi.repository.TwitterReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import twitter4j.TwitterException;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@Service
public class TwitterService {
    private static Supplier<? extends RuntimeException> exceptionSupplier =
            () -> new TwitterListNotFoundException("No twitters found for this user", new Exception());

    @Autowired
    private TwitterReader reader;

    public List<TwitterDTO> getTwitterList(String twitterName) throws TwitterException {
        return Optional.ofNullable(reader.getTwitterList(twitterName)).orElseThrow(exceptionSupplier);
    }
}

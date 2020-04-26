package com.zemoga.challenge.profileApi.rest_interface;

import com.zemoga.challenge.profileApi.application.DTO.Profile;
import com.zemoga.challenge.profileApi.application.ProfileService;
import com.zemoga.challenge.profileApi.exception.ProfileNotFoundException;
import com.zemoga.challenge.profileApi.exception.TwitterListNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import twitter4j.TwitterException;

import java.util.List;
import java.util.Objects;

@RestController
@Slf4j
@CrossOrigin(origins = "*")
@RequestMapping("/profiles")
public class ProfilesController {
    @Autowired
    ProfileService service;

    @GetMapping(path = "/{id}")
    public Profile getProfile(@PathVariable Integer id){
        Profile profileResult = null;
        try {
            profileResult = service.getProfile(id);
            if(profileResult == null){
                throw new ProfileNotFoundException(String.format("id {0} not found, please try with another", id) );
            }
        } catch (TwitterException e) {
            log.error("Error consuming Twitter Api", e);
            throw new TwitterListNotFoundException("Error consuming Twitter Api", e);
        }
        return profileResult;
    }

    @GetMapping
    public List<Profile> findAll() throws TwitterException {
        return service.findAllProfiles();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer create(@RequestBody Profile resource) {
        Objects.requireNonNull(resource);
        return service.createProfile(resource);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable( "id" ) int id, @RequestBody Profile resource) throws TwitterException {
        Objects.requireNonNull(resource);
        Profile profileResult = service.getProfile(id);
        service.updateProfile(resource);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") int id) {
        service.deleteProfile(id);
    }
}

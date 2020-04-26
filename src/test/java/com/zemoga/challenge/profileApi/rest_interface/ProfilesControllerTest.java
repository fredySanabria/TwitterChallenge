package com.zemoga.challenge.profileApi.rest_interface;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zemoga.challenge.profileApi.application.DTO.PortfolioDTO;
import com.zemoga.challenge.profileApi.application.DTO.Profile;
import com.zemoga.challenge.profileApi.application.DTO.TwitterDTO;
import com.zemoga.challenge.profileApi.application.PortfolioService;
import com.zemoga.challenge.profileApi.application.ProfileService;
import com.zemoga.challenge.profileApi.application.TwitterService;
import com.zemoga.challenge.profileApi.domain.Portfolio;
import com.zemoga.challenge.profileApi.repository.PortfolioRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class ProfilesControllerTest {

    @Autowired
    ProfileService service;

    @Autowired
    PortfolioService portfolioService;

    @Autowired
    TwitterService twitterService;

    @Autowired
    private PortfolioRepository repository;

    @Autowired
    private MockMvc mockMvc;

    ArrayList<TwitterDTO> twitterList;
    PortfolioDTO portfolio;
    Portfolio portfolioEntity;

    @Before
    public void setUp() {
        portfolio = PortfolioDTO.builder()
                .id(114)
                .twitterUserName("@ClaudiaLopez")
                .title("Bogota")
                .imageUrl("https://pbs.twimg.com/profile_images/1212157426696433666/fh7BLx6k_400x400.jpg")
                .description("Primera Alcaldesa de Bogotá. Orgullosa bogotana y ciudadana.")
                .build();
        portfolioEntity = Portfolio.builder()
                .twitterUserName("@ClaudiaLopez")
                .title("Bogota")
                .imageUrl("https://pbs.twimg.com/profile_images/1212157426696433666/fh7BLx6k_400x400.jpg")
                .description("Primera Alcaldesa de Bogotá. Orgullosa bogotana y ciudadana.")
                .build();
        TwitterDTO item1 = TwitterDTO.builder().id(3).text("test text").build();
        twitterList = new ArrayList<>();
        twitterList.add(item1);
        twitterList.add(item1);
    }

    @Test
    public void WhenBrowserSendGetProfilePetitionThenReturnExpectedResult() throws Exception {
        Profile profile = Profile.builder()
                .portfolioDTO(portfolio)
                .twitterList(twitterList)
                .build();
        mockMvc.perform( MockMvcRequestBuilders
                .get("/profiles/4")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString("Sansa like true knights")));
    }

    @Test
    @Transactional
    public void WhenBrowserSendAddProfilePetitionThenReturnExpectedResult() throws Exception {
        Profile profile = Profile.builder()
                .portfolioDTO(portfolio)
                .twitterList(twitterList)
                .build();
        mockMvc.perform( MockMvcRequestBuilders
                .post("/profiles")
                .content(asJsonString(profile))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        // clean test
        repository.deleteByTwitterUserName(portfolioEntity.getTwitterUserName());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
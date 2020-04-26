package com.zemoga.challenge.profileApi.repository;

import com.zemoga.challenge.profileApi.domain.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio,Integer> {
    void deleteByTwitterUserName(String twitterUserName);
}

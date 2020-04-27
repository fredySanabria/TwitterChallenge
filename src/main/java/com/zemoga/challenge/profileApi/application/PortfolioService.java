package com.zemoga.challenge.profileApi.application;

import com.zemoga.challenge.profileApi.application.DTO.PortfolioDTO;
import com.zemoga.challenge.profileApi.domain.Portfolio;
import com.zemoga.challenge.profileApi.repository.PortfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class PortfolioService {

    final static Function<Portfolio, PortfolioDTO> mapToDto = portfolio -> PortfolioDTO.builder()
            .id(portfolio.getId())
            .description(portfolio.getDescription())
            .imageUrl(portfolio.getImageUrl())
            .title(portfolio.getTitle())
            .twitterUserName(portfolio.getTwitterUserName())
            .build();
    final static Function<PortfolioDTO, Portfolio> mapToEntity = dto -> Portfolio.builder()
            .id(dto.getId())
            .description(dto.getDescription())
            .imageUrl(dto.getImageUrl())
            .title(dto.getTitle())
            .twitterUserName(dto.getTwitterUserName())
            .build();

    @Autowired
    private PortfolioRepository repository;

    public PortfolioDTO getPortfolio(int id) {
        return  mapToDto.apply(repository.getOne(id));
    }

    public List<PortfolioDTO> getAllPortfolios() {
        return repository.findAll().stream()
                .map(mapToDto)
                .collect(Collectors.toList());
    }

    public int createPortfolio(PortfolioDTO portfolioDTO) {
        Portfolio result = repository.save(mapToEntity.apply(portfolioDTO));
        return result.getId();
    }

    public void updatePortfolio(PortfolioDTO portfolioDTO) {
        repository.save(mapToEntity.apply(portfolioDTO));
    }

    public void deletePortfolio(int id) {
        repository.deleteById(id);
    }

}

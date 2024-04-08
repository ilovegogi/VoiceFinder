package com.ilovegogi.VoiceFinder.domain.market.service;

import com.ilovegogi.VoiceFinder.domain.market.dto.MarketListResponseDto;
import com.ilovegogi.VoiceFinder.domain.market.dto.MarketRegistrationRequestDto;
import com.ilovegogi.VoiceFinder.domain.market.dto.MarketRegistrationResponseDto;
import com.ilovegogi.VoiceFinder.domain.market.dto.MarketResponseDto;
import com.ilovegogi.VoiceFinder.domain.market.entity.Market;
import com.ilovegogi.VoiceFinder.domain.market.repository.MarketRepository;
import com.ilovegogi.VoiceFinder.domain.user.entity.Address;
import com.ilovegogi.VoiceFinder.global.exception.CustomException;
import com.ilovegogi.VoiceFinder.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class MarketService {

    private final MarketRepository marketRepository;

    @Transactional
    public MarketRegistrationResponseDto registrationMarket(MarketRegistrationRequestDto marketRegistrationRequestDto) {
        validateMarketExist(marketRegistrationRequestDto.getName(), marketRegistrationRequestDto.getOwnerId());
        Address address = new Address(marketRegistrationRequestDto.getAddress().getCity(), marketRegistrationRequestDto.getAddress().getStreet(), marketRegistrationRequestDto.getAddress().getDetail());
        Market market = Market.builder()
                .category(marketRegistrationRequestDto.getCategory())
                .address(address)
                .ownerId(marketRegistrationRequestDto.getOwnerId())
                .name(marketRegistrationRequestDto.getName())
                .description(marketRegistrationRequestDto.getDescription())
                .wayDescription(marketRegistrationRequestDto.getWayDescription())
                .build();
        marketRepository.save(market);
        return new MarketRegistrationResponseDto(market.getOwnerId());
    }

    private void validateMarketExist(String name, Long ownerId) {
        marketRepository.findByNameAndOwnerId(name, ownerId)
                .ifPresent(exist -> {
                    throw new CustomException(ErrorCode.EXISTED_MARKET);
                });
    }


    public MarketResponseDto getMarketList() {
        List<Market> markets = marketRepository.findAll();
        List<MarketListResponseDto> list = markets.stream()
                .map(m -> new MarketListResponseDto(m.getId(), m.getCategory(), m.getOwnerId(), m.getAddress(), m.getName(), m.getDescription(), m.getWayDescription()))
                .collect(Collectors.toList());
        return new MarketResponseDto(list);
    }
}

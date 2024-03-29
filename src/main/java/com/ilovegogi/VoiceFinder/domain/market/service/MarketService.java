package com.ilovegogi.VoiceFinder.domain.market.service;

import com.ilovegogi.VoiceFinder.domain.market.dto.MarketRegistrationRequestDto;
import com.ilovegogi.VoiceFinder.domain.market.dto.MarketRegistrationResponseDto;
import com.ilovegogi.VoiceFinder.domain.market.entity.Market;
import com.ilovegogi.VoiceFinder.domain.market.repository.MarketRepository;
import com.ilovegogi.VoiceFinder.domain.user.entity.Address;
import com.ilovegogi.VoiceFinder.global.exception.CustomException;
import com.ilovegogi.VoiceFinder.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class MarketService {

    private final MarketRepository marketRepository;

    @Transactional
    public MarketRegistrationResponseDto registrationMarket(MarketRegistrationRequestDto marketRegistrationRequestDto) {
        validateMarketExist(marketRegistrationRequestDto.getName(), marketRegistrationRequestDto.getOwnerId());
        Address location = new Address(marketRegistrationRequestDto.getLocation().getCity(), marketRegistrationRequestDto.getLocation().getStreet(), marketRegistrationRequestDto.getLocation().getDetail());
        Market market = Market.builder()
                .category(marketRegistrationRequestDto.getCategory())
                .location(location)
                .ownerId(marketRegistrationRequestDto.getOwnerId())
                .name(marketRegistrationRequestDto.getName())
                .description(marketRegistrationRequestDto.getDescription())
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


}

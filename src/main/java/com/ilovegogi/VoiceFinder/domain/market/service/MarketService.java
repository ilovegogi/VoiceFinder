package com.ilovegogi.VoiceFinder.domain.market.service;

import com.ilovegogi.VoiceFinder.domain.business.entity.Business;
import com.ilovegogi.VoiceFinder.domain.business.repository.BusinessRepository;
import com.ilovegogi.VoiceFinder.domain.market.dto.MarketListResponseDto;
import com.ilovegogi.VoiceFinder.domain.market.dto.MarketRegistrationRequestDto;
import com.ilovegogi.VoiceFinder.domain.market.dto.MarketRegistrationResponseDto;
import com.ilovegogi.VoiceFinder.domain.market.dto.MarketResponseDto;
import com.ilovegogi.VoiceFinder.domain.market.entity.Market;
import com.ilovegogi.VoiceFinder.domain.market.entity.QMarket;
import com.ilovegogi.VoiceFinder.domain.market.repository.MarketRepository;
import com.ilovegogi.VoiceFinder.domain.user.entity.Address;
import com.ilovegogi.VoiceFinder.domain.user.entity.User;
import com.ilovegogi.VoiceFinder.global.exception.CustomException;
import com.ilovegogi.VoiceFinder.global.exception.ErrorCode;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    private final BusinessRepository businessRepository;

    /*@Transactional
    public MarketRegistrationResponseDto registrationMarket(MarketRegistrationRequestDto marketRegistrationRequestDto) {
        validateMarketExist(marketRegistrationRequestDto.getName());
        Business business = validateBusinessById(marketRegistrationRequestDto.getBizId());
        Address address = new Address(marketRegistrationRequestDto.getAddress().getCity(), marketRegistrationRequestDto.getAddress().getStreet(), marketRegistrationRequestDto.getAddress().getDetail());
        Market market = Market.builder()
                .business(business)
                .category(marketRegistrationRequestDto.getCategory())
                .address(address)
                .name(marketRegistrationRequestDto.getName())
                .description(marketRegistrationRequestDto.getDescription())
                .wayDescription(marketRegistrationRequestDto.getWayDescription())
                .topics(marketRegistrationRequestDto.getTopics())
                .atmosphere(marketRegistrationRequestDto.getAtmosphere())
                .purpose(marketRegistrationRequestDto.getPurpose())
                .build();
        marketRepository.save(market);
        return new MarketRegistrationResponseDto(market.getId());
    }*/

    private Business validateBusinessByUser(User user) {
        return businessRepository.findByUser(user).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_BUSINESS));
    }

    private void validateMarketExist(String name) {
        marketRepository.findByName(name)
                .ifPresent(exist -> {
                    throw new CustomException(ErrorCode.EXISTED_MARKET);
                });
    }


    /*public List<MarketListResponseDto> getMarketList() {
        List<Market> markets = marketRepository.findAll();
        List<MarketListResponseDto> list = markets.stream()
                .map(m -> new MarketListResponseDto(m.getId(), m.getCategory(), m.getAddress(), m.getName(), m.getDescription(), m.getWayDescription(), m.getTopics(), m.getAtmosphere(), m.getPurpose()))
                .collect(Collectors.toList());
        return list;
    }*/

    @Transactional
    public MarketRegistrationResponseDto registrationMarket(User user, List<String> fileUrls, MarketRegistrationRequestDto marketRegistrationRequestDto) {
        Business business = validateBusinessByUser(user);
        Market market = Market.builder()
                .business(business)
                .times(marketRegistrationRequestDto.getTimes())
                .services(marketRegistrationRequestDto.getServices())
                .imageUrls(fileUrls)
                .category(marketRegistrationRequestDto.getCategory())
                .address(marketRegistrationRequestDto.getAddress())
                .name(marketRegistrationRequestDto.getName())
                .phoneNumber(marketRegistrationRequestDto.getPhoneNumber())
                .description(marketRegistrationRequestDto.getDescription())
                .wayDescription(marketRegistrationRequestDto.getWayDescription())
                .isParking(marketRegistrationRequestDto.getIsParking())
                .parkingDescription(marketRegistrationRequestDto.getParkingDescription())
                .sns(marketRegistrationRequestDto.getSns())
                .detailDescription(marketRegistrationRequestDto.getDetailDescription())
                .build();
        marketRepository.save(market);
        return new MarketRegistrationResponseDto(market.getId());
    }

    public Page<MarketResponseDto> getMarkets(int page, int size, String sortBy, boolean isAsc, String keyword) {
        Sort sort = Sort.by(isAsc ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);

        if (keyword != null && !keyword.isEmpty()) {
            QMarket qMarket = QMarket.market;
            BooleanBuilder builder = new BooleanBuilder();
            builder.and(qMarket.name.containsIgnoreCase(keyword)
                    .or(qMarket.description.containsIgnoreCase(keyword)));
            return marketRepository.findAll(builder, pageable).map(MarketResponseDto::new);
        } else {
            return marketRepository.findAll(pageable).map(MarketResponseDto::new);
        }
    }

    public MarketResponseDto getMarketDto(Long id) {
        Market market = findMarket(id);
        MarketResponseDto marketResponseDto = new MarketResponseDto(market);
        return marketResponseDto;
    }

    private Market findMarket(Long id) {
        return marketRepository.findById(id).orElseThrow(() ->
                new CustomException(ErrorCode.NOT_FOUND_MARKET)
        );
    }

}

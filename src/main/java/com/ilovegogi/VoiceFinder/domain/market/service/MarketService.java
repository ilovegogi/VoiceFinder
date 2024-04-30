package com.ilovegogi.VoiceFinder.domain.market.service;

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
import com.ilovegogi.VoiceFinder.global.security.UserDetailsImpl;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class MarketService {

    private final MarketRepository marketRepository;

//    @Transactional
//    public MarketRegistrationResponseDto registrationMarket(MarketRegistrationRequestDto marketRegistrationRequestDto) {
//        validateMarketExist(marketRegistrationRequestDto.getName(), marketRegistrationRequestDto.getOwnerId());
//        Address address = new Address(marketRegistrationRequestDto.getAddress().getCity(), marketRegistrationRequestDto.getAddress().getStreet(), marketRegistrationRequestDto.getAddress().getDetail());
//        Market market = Market.builder()
//                .category(marketRegistrationRequestDto.getCategory())
//                .address(address)
//                .ownerId(marketRegistrationRequestDto.getOwnerId())
//                .name(marketRegistrationRequestDto.getName())
//                .description(marketRegistrationRequestDto.getDescription())
//                .wayDescription(marketRegistrationRequestDto.getWayDescription())
//                .build();
//        marketRepository.save(market);
//        return new MarketRegistrationResponseDto(market.getId());
//    }

//    private void validateMarketExist(String name, Long ownerId) {
//        marketRepository.findByNameAndOwnerId(name, ownerId)
//                .ifPresent(exist -> {
//                    throw new CustomException(ErrorCode.EXISTED_MARKET);
//                });
//    }


//    public MarketResponseDto getMarketList() {
//        List<Market> markets = marketRepository.findAll();
//        List<MarketListResponseDto> list = markets.stream()
//                .map(m -> new MarketListResponseDto(m.getId(), m.getCategory(), m.getOwnerId(), m.getAddress(), m.getName(), m.getDescription(), m.getWayDescription()))
//                .collect(Collectors.toList());
//        return new MarketResponseDto(list);
//    }

    public MarketResponseDto createMarket(User user, MarketRegistrationRequestDto requestDto) {
        // RequestDto -> Entity
        Market market = new Market(user, requestDto);
        // DB 저장
        Market saveMarket = marketRepository.save(market);

        return new MarketResponseDto(saveMarket);
    }

    public Page<MarketResponseDto> getMarkets(int page, int size, String sortBy, boolean isAsc, String keyword) {
        Sort sort = Sort.by(isAsc ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
//        System.out.println("keyword = " + keyword);

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

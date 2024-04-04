package com.ilovegogi.VoiceFinder.domain.campaign.service;

import com.ilovegogi.VoiceFinder.domain.campaign.dto.CampaignTimeVisitInfoRequestDto;
import com.ilovegogi.VoiceFinder.domain.campaign.dto.CampaignTimeVisitInfoResponseDto;
import com.ilovegogi.VoiceFinder.domain.campaign.entity.Campaign;
import com.ilovegogi.VoiceFinder.domain.campaign.repository.CampaignRepository;
import com.ilovegogi.VoiceFinder.domain.market.entity.Market;
import com.ilovegogi.VoiceFinder.domain.market.repository.MarketRepository;
import com.ilovegogi.VoiceFinder.global.exception.CustomException;
import com.ilovegogi.VoiceFinder.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CampaignService {

    private final CampaignRepository campaignRepository;
    private final MarketRepository marketRepository;

    @Transactional
    public CampaignTimeVisitInfoResponseDto registrationCampaignTimeVisitInfo(Long id, CampaignTimeVisitInfoRequestDto campaignTimeVisitInfoRequestDto) {
        Market market = validateMarketById(id);
        Campaign campaign = Campaign.builder()
                .applyStartTime(campaignTimeVisitInfoRequestDto.getApplyStartTime())
                .applyEndTime(campaignTimeVisitInfoRequestDto.getApplyEndTime())
                .resultAnnouncementTime(campaignTimeVisitInfoRequestDto.getResultAnnouncementTime())
                .registrationStartTime(campaignTimeVisitInfoRequestDto.getRegistrationStartTime())
                .registrationEndTime(campaignTimeVisitInfoRequestDto.getRegistrationEndTime())
                .provision(campaignTimeVisitInfoRequestDto.getProvision())
                .visitingTime(campaignTimeVisitInfoRequestDto.getVisitingTime())
                .day(campaignTimeVisitInfoRequestDto.getDay())
                .reservationDescription(campaignTimeVisitInfoRequestDto.getReservationDescription())
                .market(market)
                .build();
        campaignRepository.save(campaign);
        return new CampaignTimeVisitInfoResponseDto(campaign.getId());
    }

    private Market validateMarketById(Long id) {
        return marketRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_MARKET));
    }
}

package com.ilovegogi.VoiceFinder.domain.campaign.controller;

import com.ilovegogi.VoiceFinder.domain.campaign.dto.CampaignMissionRequestDto;
import com.ilovegogi.VoiceFinder.domain.campaign.dto.CampaignTimeVisitInfoRequestDto;
import com.ilovegogi.VoiceFinder.domain.campaign.dto.CampaignIdResponseDto;
import com.ilovegogi.VoiceFinder.domain.campaign.service.CampaignService;
import com.ilovegogi.VoiceFinder.global.response.ApiResponse;
import com.ilovegogi.VoiceFinder.global.response.SuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/markets")
public class CampaignController {

    private final CampaignService campaignService;

    @PostMapping("/{marketId}/campaigns")
    public ResponseEntity<ApiResponse> registrationCampaignTimeVisitInfo(@PathVariable("marketId") Long marketId, @Valid @RequestBody CampaignTimeVisitInfoRequestDto campaignTimeVisitInfoRequestDto) {
        CampaignIdResponseDto campaignIdResponseDto = campaignService.registrationCampaignTimeVisitInfo(marketId, campaignTimeVisitInfoRequestDto);
        SuccessCode successCode = SuccessCode.SUCCESS_CAMPAIGN_TIME_VISIT_INFO_REGISTRATION;
        return ResponseEntity.status(successCode.getHttpStatus())
                .body(ApiResponse.of(successCode.getCode(), successCode.getMessage(), campaignIdResponseDto));
    }

    @PostMapping("/{marketId}/campaigns/{campaignId}")
    public ResponseEntity<ApiResponse> registrationCampaignMission(@PathVariable("marketId") Long marketId, @PathVariable("campaignId") Long campaignId, @Valid @RequestBody CampaignMissionRequestDto campaignMissionRequestDto) {
        CampaignIdResponseDto campaignIdResponseDto = campaignService.registrationCampaignMission(marketId, campaignId, campaignMissionRequestDto);
        SuccessCode successCode = SuccessCode.SUCCESS_CAMPAIGN_MISSION_REGISTRATION;
        return ResponseEntity.status(successCode.getHttpStatus())
                .body(ApiResponse.of(successCode.getCode(), successCode.getMessage(), campaignIdResponseDto));
    }

}

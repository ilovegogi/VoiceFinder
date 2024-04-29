package com.ilovegogi.VoiceFinder.domain.campaign.controller;

import com.ilovegogi.VoiceFinder.domain.campaign.dto.*;
import com.ilovegogi.VoiceFinder.domain.campaign.service.CampaignService;
import com.ilovegogi.VoiceFinder.domain.market.dto.MarketResponseDto;
import com.ilovegogi.VoiceFinder.global.response.ApiResponse;
import com.ilovegogi.VoiceFinder.global.response.SuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/campaigns")
public class CampaignController {

    private final CampaignService campaignService;

    @PostMapping("/new")
    public ResponseEntity<ApiResponse> registrationCampaignTimeVisitInfo(@Valid @RequestBody CampaignTimeVisitInfoRequestDto campaignTimeVisitInfoRequestDto) {
        CampaignIdResponseDto campaignIdResponseDto = campaignService.registrationCampaignTimeVisitInfo(campaignTimeVisitInfoRequestDto.getMarketId(), campaignTimeVisitInfoRequestDto);
        SuccessCode successCode = SuccessCode.SUCCESS_CAMPAIGN_TIME_VISIT_INFO_REGISTRATION;
        return ResponseEntity.status(successCode.getHttpStatus())
                .body(ApiResponse.of(successCode.getCode(), successCode.getMessage(), campaignIdResponseDto));
    }

    @PostMapping("/{campaignId}/mission")
    public ResponseEntity<ApiResponse> registrationCampaignMission(@PathVariable("campaignId") Long campaignId, @Valid @RequestBody CampaignMissionRequestDto campaignMissionRequestDto) {
        CampaignIdResponseDto campaignIdResponseDto = campaignService.registrationCampaignMission(campaignMissionRequestDto.getMarketId(), campaignId, campaignMissionRequestDto);
        SuccessCode successCode = SuccessCode.SUCCESS_CAMPAIGN_MISSION_REGISTRATION;
        return ResponseEntity.status(successCode.getHttpStatus())
                .body(ApiResponse.of(successCode.getCode(), successCode.getMessage(), campaignIdResponseDto));
    }

    @PostMapping("/{campaignId}/type")
    public ResponseEntity<ApiResponse> registrationCampaignType(@PathVariable("campaignId") Long campaignId, @Valid @RequestBody CampaignTypeRequestDto campaignTypeRequestDto) {
        CampaignIdResponseDto campaignIdResponseDto = campaignService.registrationCampaignType(campaignTypeRequestDto.getMarketId(), campaignId, campaignTypeRequestDto);
        SuccessCode successCode = SuccessCode.SUCCESS_CAMPAIGN_CREATE;
        return ResponseEntity.status(successCode.getHttpStatus())
                .body(ApiResponse.of(successCode.getCode(),successCode.getMessage(), campaignIdResponseDto));
    }

    @GetMapping("/list")
    public ResponseEntity<ApiResponse> getCampaignList() {
        List<CampaignListResponseDto> campaignListResponseDtos = campaignService.getCampaignList();
        SuccessCode successCode = SuccessCode.SUCCESS_GET_ALL_CAMPAIGN;
        return ResponseEntity.status(successCode.getHttpStatus())
                .body(ApiResponse.of(successCode.getCode(), successCode.getMessage(), campaignListResponseDtos));
    }

    @GetMapping("/{campaignId}")
    public ResponseEntity<ApiResponse> getCampaignById(@PathVariable("campaignId") Long campaignId) {
        CampaignListResponseDto campaignListResponseDto = campaignService.getCampaignById(campaignId);
        SuccessCode successCode = SuccessCode.SUCCESS_GET_CAMPAIGN;
        return ResponseEntity.status(successCode.getHttpStatus())
                .body(ApiResponse.of(successCode.getCode(), successCode.getMessage(), campaignListResponseDto));
    }

    @GetMapping("/list/name")
    public ResponseEntity<ApiResponse> getCampaignNameList() {
        List<CampaignNameListResponseDto> campaignNameListResponseDtos = campaignService.getCampaignNameList();
        SuccessCode successCode = SuccessCode.SUCCESS_GET_CAMPAIGN_NAMES;
        return ResponseEntity.status(successCode.getHttpStatus())
                .body(ApiResponse.of(successCode.getCode(), successCode.getMessage(), campaignNameListResponseDtos));
    }

}

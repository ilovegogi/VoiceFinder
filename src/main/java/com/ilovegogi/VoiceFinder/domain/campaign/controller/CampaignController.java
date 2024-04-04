package com.ilovegogi.VoiceFinder.domain.campaign.controller;

import com.ilovegogi.VoiceFinder.domain.campaign.dto.CampaignTimeVisitInfoRequestDto;
import com.ilovegogi.VoiceFinder.domain.campaign.dto.CampaignTimeVisitInfoResponseDto;
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

    @PostMapping("/{id}/campaigns")
    public ResponseEntity<ApiResponse> registrationCampaignTimeVisitInfo(@PathVariable("id") Long id, @Valid @RequestBody CampaignTimeVisitInfoRequestDto campaignTimeVisitInfoRequestDto) {
        CampaignTimeVisitInfoResponseDto campaignTimeVisitInfoResponseDto = campaignService.registrationCampaignTimeVisitInfo(id, campaignTimeVisitInfoRequestDto);
        SuccessCode successCode = SuccessCode.SUCCESS_CAMPAIGN_TIME_VISIT_INFO_CREATE;
        return ResponseEntity.status(successCode.getHttpStatus())
                .body(ApiResponse.of(successCode.getCode(), successCode.getMessage(), campaignTimeVisitInfoResponseDto));
    }

}

package com.ilovegogi.VoiceFinder.domain.campaign.controller;

import com.ilovegogi.VoiceFinder.domain.campaign.dto.*;
import com.ilovegogi.VoiceFinder.domain.campaign.service.CampaignService;
import com.ilovegogi.VoiceFinder.domain.market.dto.MarketRegistrationRequestDto;
import com.ilovegogi.VoiceFinder.domain.market.dto.MarketResponseDto;
import com.ilovegogi.VoiceFinder.domain.user.entity.User;
import com.ilovegogi.VoiceFinder.global.response.ApiResponse;
import com.ilovegogi.VoiceFinder.global.response.SuccessCode;
import com.ilovegogi.VoiceFinder.global.s3.FileUploadService;
import com.ilovegogi.VoiceFinder.global.security.UserDetailsImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/campaigns")
public class CampaignController {

    private final CampaignService campaignService;
    private final FileUploadService fileUploadService;


//    @PostMapping("/new")
//    public ResponseEntity<ApiResponse> registrationCampaignTimeVisitInfo(@Valid @RequestBody CampaignTimeVisitInfoRequestDto campaignTimeVisitInfoRequestDto) {
//        CampaignIdResponseDto campaignIdResponseDto = campaignService.registrationCampaignTimeVisitInfo(campaignTimeVisitInfoRequestDto.getMarketId(), campaignTimeVisitInfoRequestDto);
//        SuccessCode successCode = SuccessCode.SUCCESS_CAMPAIGN_TIME_VISIT_INFO_REGISTRATION;
//        return ResponseEntity.status(successCode.getHttpStatus())
//                .body(ApiResponse.of(successCode.getCode(), successCode.getMessage(), campaignIdResponseDto));
//    }
//
//    @PostMapping("/{campaignId}/mission")
//    public ResponseEntity<ApiResponse> registrationCampaignMission(@PathVariable("campaignId") Long campaignId, @Valid @RequestBody CampaignMissionRequestDto campaignMissionRequestDto) {
//        CampaignIdResponseDto campaignIdResponseDto = campaignService.registrationCampaignMission(campaignMissionRequestDto.getMarketId(), campaignId, campaignMissionRequestDto);
//        SuccessCode successCode = SuccessCode.SUCCESS_CAMPAIGN_MISSION_REGISTRATION;
//        return ResponseEntity.status(successCode.getHttpStatus())
//                .body(ApiResponse.of(successCode.getCode(), successCode.getMessage(), campaignIdResponseDto));
//    }
//
//    @PostMapping("/{campaignId}/type")
//    public ResponseEntity<ApiResponse> registrationCampaignType(@PathVariable Long campaignId, @Valid @RequestBody CampaignTypeRequestDto campaignTypeRequestDto) {
//        CampaignIdResponseDto campaignIdResponseDto = campaignService.registrationCampaignType(campaignTypeRequestDto.getMarketId(), campaignId, campaignTypeRequestDto);
//        SuccessCode successCode = SuccessCode.SUCCESS_CAMPAIGN_CREATE;
//        return ResponseEntity.status(successCode.getHttpStatus())
//                .body(ApiResponse.of(successCode.getCode(),successCode.getMessage(), campaignIdResponseDto));
//    }

    @PostMapping(consumes = "multipart/form-data")
    public CampaignResponseDto createCampaign(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                          @RequestPart("post") CampaignRequestDto requestDto,
                                          @RequestPart(value = "imageUrls", required = false) List<MultipartFile> files) throws IOException {
        User user = userDetails.getUser();
        List<String> fileUrls = null;
        if (files != null && !files.isEmpty()) {
            fileUrls = fileUploadService.uploadFiles(files); // 파일 업로드 서비스 호출
            requestDto.setImageUrls(fileUrls);
        }
        return campaignService.createCampaign(user, requestDto);
    }

    @GetMapping
    public Page<CampaignResponseDto> getCampaigns(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "sortBy", defaultValue = "createdAt") String sortBy,
            @RequestParam(name = "isAsc", defaultValue = "true") boolean isAsc) {
        System.out.println("keyword = " + keyword);

        // 서비스 메소드 호출시 페이지 번호 조정
        return campaignService.getCampaigns(page - 1, size, sortBy, isAsc, keyword);
    }

    @GetMapping("/recommend")
    public Page<CampaignResponseDto> getRecommendedCampaigns() {
        return campaignService.getRecommendedCampaigns();
    }

    @GetMapping("/{id}")
    public CampaignResponseDto getCampaignDto(@PathVariable Long id) {
        return campaignService.getCampaignDto(id);
    }

}

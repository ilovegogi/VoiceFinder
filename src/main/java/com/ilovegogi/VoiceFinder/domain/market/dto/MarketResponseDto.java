package com.ilovegogi.VoiceFinder.domain.market.dto;

import com.ilovegogi.VoiceFinder.domain.market.entity.Market;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MarketResponseDto {

    private Long id;
    private String name;
    private String address;
    private String category;
    private String phoneNumber;
    private String description;
    private String wayDescription;
    private Boolean isParking;
    private String parkingDescription;
    private String sns;
    private String detailDescription;
    private List<String> imageUrls;
    private List<String> times;
    private List<String> services;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public MarketResponseDto(Market market) {
        this.id = market.getId();
        this.category = market.getCategory();
        this.address = market.getAddress();
        this.name = market.getName();
        this.phoneNumber = market.getPhoneNumber();
        this.description = market.getDescription();
        this.wayDescription = market.getWayDescription();
        this.isParking = market.getIsParking();
        this.parkingDescription = market.getParkingDescription();
        this.sns = market.getSns();
        this.detailDescription = market.getDetailDescription();
        this.createdAt = market.getCreatedAt();
        this.modifiedAt = market.getModifiedAt();
        this.imageUrls = market.getImageUrls();
        this.times = market.getTimes();
        this.services = market.getServices();
    }

}

package com.ilovegogi.VoiceFinder.domain.market.dto;

import com.ilovegogi.VoiceFinder.domain.campaign.entity.Job;
import com.ilovegogi.VoiceFinder.domain.user.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MarketRegistrationRequestDto {

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


}

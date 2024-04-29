package com.ilovegogi.VoiceFinder.domain.market.dto;

import com.ilovegogi.VoiceFinder.domain.market.entity.Category;
import com.ilovegogi.VoiceFinder.domain.user.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MarketListResponseDto {

    private Long marketId;
    private Category category;
    private Long ownerId;
    private Address address;
    private String name;
    private String description;
    private String wayDescription;

}

package com.ilovegogi.VoiceFinder.domain.market.dto;

import com.ilovegogi.VoiceFinder.domain.user.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MarketListResponseDto {

    private Long marketId;
    private String category;
    private Address address;
    private String name;
    private String description;
    private String wayDescription;
    private String topics;
    private String atmosphere;
    private String purpose;

}

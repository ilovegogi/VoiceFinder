package com.ilovegogi.VoiceFinder.domain.market.dto;

import com.ilovegogi.VoiceFinder.domain.market.entity.Category;
import com.ilovegogi.VoiceFinder.domain.user.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MarketRegistrationRequestDto {

    private Category category;
    private Long ownerId;
    private String name;
    private Address address;
    private String description;
    private String wayDescription;

}

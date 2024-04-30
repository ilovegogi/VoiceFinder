package com.ilovegogi.VoiceFinder.domain.market.dto;

import com.ilovegogi.VoiceFinder.domain.market.entity.Category;
import com.ilovegogi.VoiceFinder.domain.user.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MarketRegistrationRequestDto {

    private Category category;
    private String address;
    private String name;
    private String description;
    private String wayDescription;

    private List<String> imageUrls; // 이미지 URL 리스트 추가

}

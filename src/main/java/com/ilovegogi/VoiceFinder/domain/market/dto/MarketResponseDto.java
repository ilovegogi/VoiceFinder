package com.ilovegogi.VoiceFinder.domain.market.dto;

import com.ilovegogi.VoiceFinder.domain.market.entity.Category;
import com.ilovegogi.VoiceFinder.domain.market.entity.Market;
import com.ilovegogi.VoiceFinder.domain.user.entity.Address;
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
    private Category category;
    private String address;
    private String name;
    private String description;
    private String wayDescription;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private List<String> imageUrls; // 파일 URL 리스트 추가

    public MarketResponseDto(Market market) {
        this.id = market.getId();
        this.category = market.getCategory();
        this.address = market.getAddress();
        this.name = market.getName();
        this.description = market.getDescription();
        this.wayDescription = market.getWayDescription();
        this.createdAt = market.getCreatedAt();
        this.modifiedAt = market.getModifiedAt();
        this.imageUrls = market.getImageUrls();
    }
}

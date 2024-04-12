package com.ilovegogi.VoiceFinder.domain.market.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MarketResponseDto<T> {

    private T data;

}

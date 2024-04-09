package com.ilovegogi.VoiceFinder.domain.market.entity;

import com.ilovegogi.VoiceFinder.domain.user.entity.Address;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "market")
public class Market {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "market_id")
    private Long id;

    private Category category;

    @Embedded
    private Address location;

    private String name;

    private String description;
}

package com.ilovegogi.VoiceFinder.domain.market.entity;

import com.ilovegogi.VoiceFinder.domain.user.entity.Address;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
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

    private Long ownerId;

    @Embedded
    private Address location;

    private String name;

    private String description;

    @Builder
    public Market(Category category, Long ownerId, Address location, String name, String description) {
        this.category = category;
        this.ownerId = ownerId;
        this.location = location;
        this.name = name;
        this.description = description;
    }
}

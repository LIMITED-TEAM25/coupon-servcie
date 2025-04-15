package com.sparta.limited.coupon_service.coupon.domain.model;

import com.sparta.limited.common_module.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Entity
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "p_coupon")
public class Coupon extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "discount_rate", nullable = false)
    private Integer discountRate;

    @Column(name = "status", nullable = false)
    private CouponStatus status;

    private Coupon(
        String name,
        Integer discountRate
    ) {
        this.name = name;
        this.discountRate = discountRate;
        this.status = CouponStatus.ACTIVE;
    }

    public static Coupon of(
        String name,
        Integer discountRate
    ) {
        return new Coupon(
            name,
            discountRate
        );
    }

}

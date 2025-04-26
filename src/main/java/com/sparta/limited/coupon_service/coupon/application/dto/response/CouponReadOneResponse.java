package com.sparta.limited.coupon_service.coupon.application.dto.response;

import com.sparta.limited.coupon_service.coupon.domain.model.CouponStatus;
import java.util.UUID;
import lombok.Getter;

@Getter
public class CouponReadOneResponse {

    private final UUID couponId;
    private final String name;
    private final Integer discountRate;
    private final CouponStatus status;
    private final Long quantity;

    private CouponReadOneResponse(
        UUID couponId,
        String name,
        Integer discountRate,
        CouponStatus status,
        Long quantity
    ) {
        this.couponId = couponId;
        this.name = name;
        this.discountRate = discountRate;
        this.status = status;
        this.quantity = quantity;
    }

    public static CouponReadOneResponse of(
        UUID couponId,
        String name,
        Integer discountRate,
        CouponStatus status,
        Long quantity
    ) {
        return new CouponReadOneResponse(
            couponId,
            name,
            discountRate,
            status,
            quantity
        );
    }

}

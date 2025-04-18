package com.sparta.limited.coupon_service.coupon.application.dto.request;

import lombok.Getter;

@Getter
public class CouponCreateRequest {

    private final String name;
    private final Integer discountRate;
    private final Long quantity;

    private CouponCreateRequest(
        String name,
        Integer discountRate,
        Long quantity
    ) {
        this.name = name;
        this.discountRate = discountRate;
        this.quantity = quantity;
    }

    public static CouponCreateRequest of(
        String name,
        Integer discountRate,
        Long quantity
    ) {
        return new CouponCreateRequest(
            name,
            discountRate,
            quantity
        );
    }
}

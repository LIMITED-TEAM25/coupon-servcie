package com.sparta.limited.coupon_service.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CouponStatus {
    ACTIVE("유요한 쿠폰"),
    CLOSED("만료된 쿠폰");

    private final String description;

}

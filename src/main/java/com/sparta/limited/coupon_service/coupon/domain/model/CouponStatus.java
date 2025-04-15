package com.sparta.limited.coupon_service.coupon.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CouponStatus {
    ACTIVE("쿠폰 활성화"),
    CLOSED("쿠폰 종료");

    private final String description;

}

package com.sparta.limited.coupon_service.user_coupon.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserCouponStatus {
    NOT_USED("미사용 쿠폰"),
    USED("사용 쿠폰");

    private final String description;

}

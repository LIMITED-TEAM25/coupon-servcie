package com.sparta.limited.coupon_service.user_coupon.application.mapper;

import com.sparta.limited.coupon_service.user_coupon.domain.model.UserCoupon;
import java.util.UUID;

public class UserCouponMapper {

    public static UserCoupon toEntity(
        UUID couponId,
        Long userId
    ) {
        return UserCoupon.of(
            couponId,
            userId
        );
    }

}

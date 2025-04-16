package com.sparta.limited.coupon_service.user_coupon.domain.repository;

import com.sparta.limited.coupon_service.user_coupon.domain.model.UserCoupon;
import java.util.UUID;

public interface UserCouponRepository {

    void save(UserCoupon userCoupon);

    boolean existsByUserIdAndCouponId(Long userId, UUID couponId);
}

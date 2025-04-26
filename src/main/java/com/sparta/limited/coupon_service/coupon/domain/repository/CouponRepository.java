package com.sparta.limited.coupon_service.coupon.domain.repository;

import com.sparta.limited.coupon_service.coupon.domain.model.Coupon;
import java.util.UUID;

public interface CouponRepository {

    void save(Coupon coupon);

    Coupon findById(UUID couponId);

    Coupon findByIdWithLock(UUID couponId);

}

package com.sparta.limited.coupon_service.domain.repository;

import com.sparta.limited.coupon_service.domain.model.Coupon;
import java.util.UUID;

public interface CouponRepository {

    void save(Coupon coupon);

    Coupon findById(UUID couponId);
}

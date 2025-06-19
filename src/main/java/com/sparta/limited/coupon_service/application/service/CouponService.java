package com.sparta.limited.coupon_service.application.service;

import com.sparta.limited.coupon_service.application.dto.request.CouponCreateRequest;
import com.sparta.limited.coupon_service.application.dto.response.CouponCreateResponse;
import com.sparta.limited.coupon_service.application.dto.response.CouponReadOneResponse;
import java.util.UUID;

public interface CouponService {

    CouponCreateResponse createCoupon(CouponCreateRequest request);

    CouponReadOneResponse getCoupon(UUID couponId);

    void decreaseQuantity(UUID couponId);

}

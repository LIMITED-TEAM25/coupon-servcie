package com.sparta.limited.coupon_service.coupon.application.mapper;

import com.sparta.limited.coupon_service.coupon.application.dto.request.CouponCreateRequest;
import com.sparta.limited.coupon_service.coupon.application.dto.response.CouponCreateResponse;
import com.sparta.limited.coupon_service.coupon.application.dto.response.CouponReadOneResponse;
import com.sparta.limited.coupon_service.coupon.domain.model.Coupon;

public class CouponMapper {

    public static Coupon toEntity(
        CouponCreateRequest request
    ) {
        return Coupon.of(
            request.getName(),
            request.getDiscountRate(),
            request.getQuantity()
        );
    }

    public static CouponCreateResponse toCreateResponse(
        Coupon coupon
    ) {
        return CouponCreateResponse.of(
            coupon.getId(),
            coupon.getName(),
            coupon.getDiscountRate(),
            coupon.getQuantity(),
            coupon.getStatus()
        );
    }

    public static CouponReadOneResponse toReadOneResponse(
        Coupon coupon
    ) {
        return CouponReadOneResponse.of(
            coupon.getId(),
            coupon.getName(),
            coupon.getDiscountRate(),
            coupon.getStatus(),
            coupon.getQuantity()
        );
    }
}

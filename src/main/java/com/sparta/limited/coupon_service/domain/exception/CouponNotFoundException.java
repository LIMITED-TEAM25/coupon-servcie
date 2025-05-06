package com.sparta.limited.coupon_service.domain.exception;

import com.sparta.limited.common_module.exception.BusinessException;
import com.sparta.limited.common_module.exception.ErrorCode;
import java.util.UUID;

public class CouponNotFoundException extends BusinessException {

    public CouponNotFoundException(UUID couponId) {
        super(ErrorCode.RESOURCES_NOT_FOUND, "해당 쿠폰을 찾을 수 없습니다. couponId : " + couponId);
    }
}

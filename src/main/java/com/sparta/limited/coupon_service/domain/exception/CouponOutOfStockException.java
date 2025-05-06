package com.sparta.limited.coupon_service.domain.exception;

import com.sparta.limited.common_module.exception.BusinessException;
import com.sparta.limited.common_module.exception.ErrorCode;
import java.util.UUID;

public class CouponOutOfStockException extends BusinessException {

    public CouponOutOfStockException(UUID couponId) {
        super(ErrorCode.OPERATION_NOT_ALLOWED, "쿠폰의 남은 수량이 없습니다. couponId : " + couponId);
    }
}

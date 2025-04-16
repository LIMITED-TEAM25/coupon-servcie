package com.sparta.limited.coupon_service.user_coupon.domain.exception;

import com.sparta.limited.common_module.exception.BusinessException;
import com.sparta.limited.common_module.exception.ErrorCode;
import java.util.UUID;

public class UserCouponDuplicatedException extends BusinessException {


    public UserCouponDuplicatedException(
        Long userId,
        UUID couponId
    ) {
        super(ErrorCode.DUPLICATE_RESOURCE,
            userId + " 번 사용자는 이미 해당 쿠폰을 가지고 있습니다 couponId : " + couponId);
    }

}

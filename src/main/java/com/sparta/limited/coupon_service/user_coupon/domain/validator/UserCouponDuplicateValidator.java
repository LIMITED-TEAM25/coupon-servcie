package com.sparta.limited.coupon_service.user_coupon.domain.validator;

import com.sparta.limited.coupon_service.user_coupon.domain.exception.UserCouponDuplicatedException;
import java.util.UUID;

public class UserCouponDuplicateValidator {

    public static void duplicateValidate(
        boolean isDuplicated,
        Long userId,
        UUID couponId
    ) {
        if (isDuplicated) {
            throw new UserCouponDuplicatedException(userId, couponId);
        }
    }
}

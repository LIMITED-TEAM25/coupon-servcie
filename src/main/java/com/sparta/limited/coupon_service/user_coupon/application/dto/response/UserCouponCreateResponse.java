package com.sparta.limited.coupon_service.user_coupon.application.dto.response;

import com.sparta.limited.coupon_service.user_coupon.domain.model.UserCouponStatus;
import java.util.UUID;
import lombok.Getter;

@Getter
public class UserCouponCreateResponse {

    private final UUID userCouponId;
    private final UUID couponId;
    private final Long userId;
    private final UserCouponStatus status;

    private UserCouponCreateResponse(
        UUID userCouponId,
        UUID couponId,
        Long userId,
        UserCouponStatus status
    ) {
        this.userCouponId = userCouponId;
        this.couponId = couponId;
        this.userId = userId;
        this.status = status;
    }

    public static UserCouponCreateResponse of(
        UUID userCouponId,
        UUID couponId,
        Long userId,
        UserCouponStatus status
    ) {
        return new UserCouponCreateResponse(
            userCouponId,
            couponId,
            userId,
            status
        );
    }
}

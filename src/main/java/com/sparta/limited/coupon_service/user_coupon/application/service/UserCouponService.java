package com.sparta.limited.coupon_service.user_coupon.application.service;

import com.sparta.limited.coupon_service.user_coupon.domain.model.UserCoupon;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserCouponService {

    private final RedisCouponIssueService redisCouponIssueService;

    @Transactional
    public UUID creatUserCoupon(
        UUID couponId,
        Long userId
    ) {
        UserCoupon userCoupon = UserCoupon.of(couponId, userId);

        redisCouponIssueService.quantityAndDuplicate(userCoupon.getCouponId(),
            userCoupon.getUserId());

        redisCouponIssueService.userCouponCreateEvent(userCoupon.getCouponId(),
            userCoupon.getUserId());

        redisCouponIssueService.decreaseCouponQuantityEvent(userCoupon.getCouponId());

        return userCoupon.getId();
    }
}

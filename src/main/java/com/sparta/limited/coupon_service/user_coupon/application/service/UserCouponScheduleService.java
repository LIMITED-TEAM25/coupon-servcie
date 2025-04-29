package com.sparta.limited.coupon_service.user_coupon.application.service;

import com.sparta.limited.coupon_service.user_coupon.domain.model.UserCoupon;
import com.sparta.limited.coupon_service.user_coupon.domain.repository.UserCouponRepository;
import jakarta.transaction.Transactional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserCouponScheduleService {

    private final UserCouponRepository userCouponRepository;

    @Transactional
    public void saveUserCoupon(
        UUID couponId,
        Long userId
    ) {
        UserCoupon userCoupon = UserCoupon.of(
            couponId,
            userId
        );
        userCouponRepository.save(userCoupon);
    }
}

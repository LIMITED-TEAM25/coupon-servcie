package com.sparta.limited.coupon_service.user_coupon.infrastructure.repository;

import com.sparta.limited.coupon_service.user_coupon.domain.model.UserCoupon;
import com.sparta.limited.coupon_service.user_coupon.domain.repository.UserCouponRepository;
import com.sparta.limited.coupon_service.user_coupon.infrastructure.persistence.JpaUserCouponRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserCouponRepositoryImpl implements UserCouponRepository {

    private final JpaUserCouponRepository jpaUserCouponRepository;

    @Override
    public void save(UserCoupon userCoupon) {
        jpaUserCouponRepository.save(userCoupon);
    }

    @Override
    public boolean existsByUserIdAndCouponId(Long userId, UUID couponId) {
        return jpaUserCouponRepository.existsByUserIdAndCouponId(userId, couponId);
    }
}

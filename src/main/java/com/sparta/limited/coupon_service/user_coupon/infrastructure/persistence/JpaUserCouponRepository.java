package com.sparta.limited.coupon_service.user_coupon.infrastructure.persistence;

import com.sparta.limited.coupon_service.user_coupon.domain.model.UserCoupon;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserCouponRepository extends JpaRepository<UserCoupon, UUID> {

    boolean existsByUserIdAndCouponId(Long userId, UUID couponId);

}

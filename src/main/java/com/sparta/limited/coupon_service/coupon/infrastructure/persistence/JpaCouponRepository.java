package com.sparta.limited.coupon_service.coupon.infrastructure.persistence;

import com.sparta.limited.coupon_service.coupon.domain.model.Coupon;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface JpaCouponRepository extends JpaRepository<Coupon, UUID> {

    @Modifying
    @Query("UPDATE Coupon c SET c.quantity = c.quantity - 1 WHERE c.id = :couponId AND c.quantity > 0")
    int decrementQuantity(UUID couponId);
}

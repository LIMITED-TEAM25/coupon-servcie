package com.sparta.limited.coupon_service.coupon.infrastructure.persistence;

import com.sparta.limited.coupon_service.coupon.domain.model.Coupon;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCouponRepository extends JpaRepository<Coupon, UUID> {

}

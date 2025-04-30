package com.sparta.limited.coupon_service.infrastructure.persistence;

import com.sparta.limited.coupon_service.domain.model.Coupon;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCouponRepository extends JpaRepository<Coupon, UUID> {

}

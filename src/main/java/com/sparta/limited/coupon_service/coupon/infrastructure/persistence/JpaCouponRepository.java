package com.sparta.limited.coupon_service.coupon.infrastructure.persistence;

import com.sparta.limited.coupon_service.coupon.domain.model.Coupon;
import jakarta.persistence.LockModeType;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

public interface JpaCouponRepository extends JpaRepository<Coupon, UUID> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select c from Coupon c where c.id = :id")
    Optional<Coupon> findByIdWithLock(UUID id);

}

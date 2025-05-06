package com.sparta.limited.coupon_service.infrastructure.repository;

import com.sparta.limited.coupon_service.domain.exception.CouponNotFoundException;
import com.sparta.limited.coupon_service.domain.model.Coupon;
import com.sparta.limited.coupon_service.domain.repository.CouponRepository;
import com.sparta.limited.coupon_service.infrastructure.persistence.JpaCouponRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CouponRepositoryImpl implements CouponRepository {

    private final JpaCouponRepository jpaCouponRepository;

    @Override
    public void save(Coupon coupon) {
        jpaCouponRepository.save(coupon);
    }

    @Override
    public Coupon findById(UUID couponId) {
        return jpaCouponRepository.findById(couponId)
            .orElseThrow(() -> new CouponNotFoundException(couponId));
    }

}

package com.sparta.limited.coupon_service.coupon.infrastructure.repository;

import com.sparta.limited.coupon_service.coupon.domain.exception.CouponNotFoundException;
import com.sparta.limited.coupon_service.coupon.domain.exception.CouponOutOfStockException;
import com.sparta.limited.coupon_service.coupon.domain.model.Coupon;
import com.sparta.limited.coupon_service.coupon.domain.repository.CouponRepository;
import com.sparta.limited.coupon_service.coupon.infrastructure.persistence.JpaCouponRepository;
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

    @Override
    public void decrementQuantity(UUID couponId) {
        int updateQuantity = jpaCouponRepository.decrementQuantity(couponId);
        if (updateQuantity == 0) {
            throw new CouponOutOfStockException(couponId);
        }
    }

}

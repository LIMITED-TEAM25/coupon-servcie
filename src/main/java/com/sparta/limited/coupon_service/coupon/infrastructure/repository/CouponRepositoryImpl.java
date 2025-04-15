package com.sparta.limited.coupon_service.coupon.infrastructure.repository;

import com.sparta.limited.coupon_service.coupon.domain.repository.CouponRepository;
import com.sparta.limited.coupon_service.coupon.infrastructure.persistence.JpaCouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CouponRepositoryImpl implements CouponRepository {

    private final JpaCouponRepository jpaCouponRepository;

}

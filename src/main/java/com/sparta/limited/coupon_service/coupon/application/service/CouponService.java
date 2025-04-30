package com.sparta.limited.coupon_service.coupon.application.service;

import com.sparta.limited.coupon_service.coupon.application.dto.request.CouponCreateRequest;
import com.sparta.limited.coupon_service.coupon.application.dto.response.CouponCreateResponse;
import com.sparta.limited.coupon_service.coupon.application.dto.response.CouponReadOneResponse;
import com.sparta.limited.coupon_service.coupon.application.mapper.CouponMapper;
import com.sparta.limited.coupon_service.coupon.domain.model.Coupon;
import com.sparta.limited.coupon_service.coupon.domain.repository.CouponRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CouponService {

    private final CouponRepository couponRepository;
    private final RedisQuantityService redisQuantityService;

    @Transactional
    public CouponCreateResponse createCoupon(
        CouponCreateRequest request
    ) {
        Coupon coupon = CouponMapper.toEntity(request);
        couponRepository.save(coupon);
        redisQuantityService.cachingQuantity(coupon.getId(), coupon.getQuantity());
        redisQuantityService.warmupUserCouponCreate(coupon.getId());
        return CouponMapper.toCreateResponse(coupon);
    }

    @Transactional
    public void decreaseQuantity(
        UUID couponId
    ) {
        Coupon coupon = couponRepository.findById(couponId);
        coupon.decreaseQuantity();
    }

    @Transactional(readOnly = true)
    public CouponReadOneResponse getCoupon(
        UUID couponId
    ) {
        Coupon coupon = couponRepository.findById(couponId);
        return CouponMapper.toReadOneResponse(coupon);
    }
}

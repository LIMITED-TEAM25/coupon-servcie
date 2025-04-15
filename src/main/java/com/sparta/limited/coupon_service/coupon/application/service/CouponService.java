package com.sparta.limited.coupon_service.coupon.application.service;

import com.sparta.limited.coupon_service.coupon.application.dto.request.CouponCreateRequest;
import com.sparta.limited.coupon_service.coupon.application.dto.response.CouponCreateResponse;
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

    @Transactional
    public CouponCreateResponse createCoupon(
        CouponCreateRequest request
    ) {
        Coupon coupon = CouponMapper.toEntity(request);
        couponRepository.save(coupon);
        return CouponMapper.toCreateResponse(coupon);
    }

    @Transactional
    public void decreaseQuantity(
        UUID couponId
    ) {
        Coupon coupon = couponRepository.findById(couponId);
        coupon.decreaseQuantity();
    }
}

package com.sparta.limited.coupon_service.user_coupon.application.service.coupon;

import com.sparta.limited.coupon_service.coupon.application.service.CouponService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CouponFacade {

    private final CouponService couponService;

    public void decreaseQuantity(
        UUID couponId
    ) {
        couponService.decreaseQuantity(couponId);
    }

}

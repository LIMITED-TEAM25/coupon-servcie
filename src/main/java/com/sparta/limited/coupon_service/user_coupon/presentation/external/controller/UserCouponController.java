package com.sparta.limited.coupon_service.user_coupon.presentation.external.controller;

import com.sparta.limited.common_module.common.annotation.CurrentUserId;
import com.sparta.limited.coupon_service.user_coupon.application.service.UserCouponService;
import java.net.URI;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user-coupons")
public class UserCouponController {

    private final UserCouponService userCouponService;

    @PostMapping("/{couponId}")
    public ResponseEntity<String> createUserCoupon(
        @CurrentUserId Long userId,
        @PathVariable(name = "couponId") UUID couponId
    ) {
        UUID userCouponId = userCouponService.creatUserCoupon(couponId, userId);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
            .path("/api/v1/user-coupons/{userCouponId}")
            .buildAndExpand(userCouponId)
            .toUri();
        return ResponseEntity.created(uri).body("쿠폰 발급 완료");
    }
}

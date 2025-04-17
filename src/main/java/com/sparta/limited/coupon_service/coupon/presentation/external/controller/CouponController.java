package com.sparta.limited.coupon_service.coupon.presentation.external.controller;

import com.sparta.limited.common_module.common.aop.RoleCheck;
import com.sparta.limited.coupon_service.coupon.application.dto.request.CouponCreateRequest;
import com.sparta.limited.coupon_service.coupon.application.dto.response.CouponCreateResponse;
import com.sparta.limited.coupon_service.coupon.application.dto.response.CouponReadOneResponse;
import com.sparta.limited.coupon_service.coupon.application.service.CouponService;
import java.net.URI;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/coupons")
public class CouponController {

    private final CouponService couponService;

    @RoleCheck("ROLE_ADMIN")
    @PostMapping
    public ResponseEntity<CouponCreateResponse> createCoupon(
        @RequestBody CouponCreateRequest request
    ) {
        CouponCreateResponse response = couponService.createCoupon(request);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
            .path("/api/v1/coupons/{couponId}")
            .buildAndExpand(response.getId())
            .toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping("/{couponId}")
    public ResponseEntity<CouponReadOneResponse> getOneCoupon(
        @PathVariable(name = "couponId") UUID couponId
    ) {
        CouponReadOneResponse response = couponService.getCoupon(couponId);
        return ResponseEntity.ok(response);
    }

}

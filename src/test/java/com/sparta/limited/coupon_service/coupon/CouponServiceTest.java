package com.sparta.limited.coupon_service.coupon;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.sparta.limited.coupon_service.coupon.application.dto.request.CouponCreateRequest;
import com.sparta.limited.coupon_service.coupon.application.dto.response.CouponCreateResponse;
import com.sparta.limited.coupon_service.coupon.application.service.CouponService;
import com.sparta.limited.coupon_service.coupon.domain.model.Coupon;
import com.sparta.limited.coupon_service.coupon.domain.repository.CouponRepository;
import com.sparta.limited.coupon_service.coupon.infrastructure.persistence.JpaCouponRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.context.TestConstructor.AutowireMode;

@SpringBootTest
@AutoConfigureMockMvc
@TestConstructor(autowireMode = AutowireMode.ALL)
@DisplayName("쿠폰 : 서비스 테스트")
public class CouponServiceTest {

    @Autowired
    private CouponRepository couponRepository;

    @Autowired
    private CouponService couponService;

    @Autowired
    private JpaCouponRepository jpaCouponRepository;

    @Test
    @DisplayName("쿠폰 생성 서비스 테스트")
    void createCoupon() {
        CouponCreateRequest couponCreateRequest = CouponCreateRequest.of(
            "테스트 쿠폰 생성",
            10,
            10L
        );

        CouponCreateResponse couponCreateResponse = couponService.createCoupon(couponCreateRequest);

        Coupon coupon = couponRepository.findById(couponCreateResponse.getId());

        assertEquals(couponCreateRequest.getName(), coupon.getName());
        assertEquals(couponCreateRequest.getDiscountRate(), coupon.getDiscountRate());
        assertEquals(couponCreateRequest.getQuantity(), coupon.getQuantity());

        jpaCouponRepository.delete(coupon);

    }

}

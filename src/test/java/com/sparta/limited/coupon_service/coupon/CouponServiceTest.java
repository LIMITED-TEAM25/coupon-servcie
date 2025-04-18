package com.sparta.limited.coupon_service.coupon;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.sparta.limited.coupon_service.coupon.application.dto.request.CouponCreateRequest;
import com.sparta.limited.coupon_service.coupon.application.dto.response.CouponCreateResponse;
import com.sparta.limited.coupon_service.coupon.application.dto.response.CouponReadOneResponse;
import com.sparta.limited.coupon_service.coupon.application.service.CouponService;
import com.sparta.limited.coupon_service.coupon.domain.model.Coupon;
import com.sparta.limited.coupon_service.coupon.domain.repository.CouponRepository;
import com.sparta.limited.coupon_service.coupon.infrastructure.persistence.JpaCouponRepository;
import java.util.UUID;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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

    private UUID couponId;
    private String couponName;
    private Integer discountRate;
    private Long quantity;

    @BeforeEach
    void setUp() {
        Coupon coupon = Coupon.of(
            "테스트용 쿠폰",
            10,
            10L
        );
        couponRepository.save(coupon);
        couponId = coupon.getId();
        couponName = coupon.getName();
        discountRate = coupon.getDiscountRate();
        quantity = coupon.getQuantity();
    }

    @AfterEach
    void deleteTestCoupon() {
        jpaCouponRepository.deleteById(couponId);
    }

    @Test
    @DisplayName("쿠폰 생성 서비스 테스트")
    void createCouponTest() {
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

    @Test
    @DisplayName("쿠폰 단건 조회 서비스 테스트")
    void getCouponTest() {
        CouponReadOneResponse couponReadOneResponse = couponService.getCoupon(couponId);

        assertEquals(couponId, couponReadOneResponse.getCouponId());
        assertEquals(couponName, couponReadOneResponse.getName());
        assertEquals(discountRate, couponReadOneResponse.getDiscountRate());
        assertEquals(quantity, couponReadOneResponse.getQuantity());
    }

    @Test
    @DisplayName("쿠폰 재고 감소 서비스 테스트")
    void decreaseQuantityTest() {
        couponService.decreaseQuantity(couponId);
        Coupon coupon = couponRepository.findById(couponId);

        assertEquals(coupon.getQuantity(), quantity - 1);
    }

}

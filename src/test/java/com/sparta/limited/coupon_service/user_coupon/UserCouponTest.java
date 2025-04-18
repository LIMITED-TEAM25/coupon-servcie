package com.sparta.limited.coupon_service.user_coupon;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.sparta.limited.coupon_service.coupon.domain.model.Coupon;
import com.sparta.limited.coupon_service.coupon.domain.repository.CouponRepository;
import com.sparta.limited.coupon_service.user_coupon.application.service.UserCouponService;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserCouponTest {

    private final static Logger log = LoggerFactory.getLogger(UserCouponTest.class);
    private final Long couponQuantity = 100L;
    @Autowired
    private UserCouponService userCouponService;
    @Autowired
    private CouponRepository couponRepository;
    private UUID couponId;

    @BeforeEach
    void setUpCoupon() {
        Coupon coupon = Coupon.of(
            "테스트용 쿠폰",
            1,
            couponQuantity
        );
        couponRepository.save(coupon);
        couponId = coupon.getId();
    }

    @Test
    @DisplayName("사용자 쿠폰 발급 동시성 테스트")
    void createUserCouponTest() throws InterruptedException {
        int numThreads = 1000;
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);
        CountDownLatch latch = new CountDownLatch(numThreads);
        AtomicInteger successCount = new AtomicInteger();
        AtomicInteger failCount = new AtomicInteger();

        long testStartTime = System.currentTimeMillis();

        for (int i = 0; i < numThreads; i++) {
            final Long userId = i + 1L;
            executorService.submit(() -> {
                try {
                    userCouponService.creatUserCoupon(couponId, userId);
                    successCount.incrementAndGet();
                } catch (Exception e) {
                    failCount.incrementAndGet();
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        executorService.shutdown();

        long testFinishTime = System.currentTimeMillis();
        log.info("테스트 소요 시간 : " + (testFinishTime - testStartTime));
        log.info("발급한 총 쿠폰 개수 : " + successCount.get());
        log.info("발급 실패한 총 쿠폰 개수 : " + failCount.get());

        assertEquals(couponQuantity, successCount.get());
        assertEquals(numThreads - couponQuantity, failCount.get());
    }

}

package com.sparta.limited.coupon_service.infrastructure.scheduler;

import com.sparta.limited.coupon_service.application.service.CouponService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class CouponScheduler {

    private static final String QUEUE_KEY = "coupon-quantity-decrease:queue";

    private final StringRedisTemplate redisTemplate;
    private final CouponService couponService;

    @Scheduled(initialDelay = 5000, fixedDelay = 5000)
    public void pollStockDecreaseQueue() {
        List<String> batch = redisTemplate.opsForList()
            .leftPop(QUEUE_KEY, 100);

        if (batch != null && !batch.isEmpty()) {
            batch.forEach(this::processStockDecrease);
        }
    }

    @Transactional
    protected void processStockDecrease(String payload) {
        couponService.decreaseQuantity(UUID.fromString(payload));
    }
}

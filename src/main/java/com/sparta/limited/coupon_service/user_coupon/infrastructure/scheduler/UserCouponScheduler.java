package com.sparta.limited.coupon_service.user_coupon.infrastructure.scheduler;

import com.sparta.limited.coupon_service.user_coupon.application.service.UserCouponScheduleService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class UserCouponScheduler {

    private static final String QUEUE_KEY = "user-coupon:queue";

    private final StringRedisTemplate redisTemplate;
    private final UserCouponScheduleService userCouponScheduleService;

    @Scheduled(initialDelay = 5000, fixedDelay = 5000)
    public void pollUserCouponQueue() {
        List<String> batch = redisTemplate.opsForList()
            .leftPop(QUEUE_KEY, 100);

        if (batch != null && !batch.isEmpty()) {
            batch.forEach(this::processUserCoupon);
        }
    }

    @Transactional
    protected void processUserCoupon(String payload) {
        String[] parts = payload.split("\\|");
        userCouponScheduleService.saveUserCoupon(
            UUID.fromString(parts[0]),
            Long.parseLong(parts[1])
        );
    }
}

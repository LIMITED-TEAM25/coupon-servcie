package com.sparta.limited.coupon_service.coupon.application.service;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisQuantityService {

    private static final String KEY_QUANTITY = "coupon:%s:quantity";
    private final StringRedisTemplate redisTemplate;

    public void cachingQuantity(
        UUID couponId,
        Long quantity
    ) {
        String key = String.format(KEY_QUANTITY, couponId);
        redisTemplate.opsForValue()
            .setIfAbsent(key, String.valueOf(quantity));
    }
}

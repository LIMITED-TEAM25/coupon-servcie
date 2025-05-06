package com.sparta.limited.coupon_service.application.service;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisQuantityService {

    private static final String OUT_OF_STOCK = "OUT_OF_STOCK";
    private static final String DUPLICATED = "DUPLICATED";
    private static final String KEY_QUANTITY = "coupon:%s:quantity";
    private static final String KEY_DUPLICATE = "coupon:%s:users";
    private final DefaultRedisScript<String> stockAndDupScript;
    private final StringRedisTemplate redisTemplate;

    public void cachingQuantity(
        UUID couponId,
        Long quantity
    ) {
        String key = String.format(KEY_QUANTITY, couponId);
        redisTemplate.opsForValue()
            .setIfAbsent(key, String.valueOf(quantity));
    }

    public void warmupUserCouponCreate(
        UUID couponId
    ) {
        long userId = 0L;
        String quantityKey = String.format(KEY_QUANTITY, couponId);
        String duplicateKey = String.format(KEY_DUPLICATE, couponId);

        String result = redisTemplate.execute(
            stockAndDupScript,
            List.of(quantityKey, duplicateKey),
            Long.toString(userId)
        );

        if (!OUT_OF_STOCK.equals(result) && !DUPLICATED.equals(result)) {
            redisTemplate.opsForValue().increment(quantityKey);
            redisTemplate.opsForSet().remove(duplicateKey, Long.toString(userId));
        }
    }
}

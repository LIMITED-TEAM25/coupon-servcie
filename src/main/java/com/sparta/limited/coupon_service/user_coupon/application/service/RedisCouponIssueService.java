package com.sparta.limited.coupon_service.user_coupon.application.service;

import com.sparta.limited.coupon_service.coupon.domain.exception.CouponOutOfStockException;
import com.sparta.limited.coupon_service.user_coupon.domain.exception.UserCouponDuplicatedException;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisCouponIssueService {

    private static final String OUT_OF_STOCK = "OUT_OF_STOCK";
    private static final String DUPLICATED = "DUPLICATED";
    private static final String KEY_QUANTITY = "coupon:%s:quantity";
    private static final String KEY_DUPLICATE = "coupon:%s:users";
    private final DefaultRedisScript<String> stockAndDupScript;
    private final StringRedisTemplate redisTemplate;

    public void quantityAndDuplicate(UUID couponId, Long userId) {
        String quantityKey = String.format(KEY_QUANTITY, couponId);
        String duplicateKey = String.format(KEY_DUPLICATE, couponId);
        String result = redisTemplate.execute(
            stockAndDupScript,
            List.of(quantityKey, duplicateKey),
            userId.toString()
        );

        if (result.equals(OUT_OF_STOCK)) {
            throw new CouponOutOfStockException(couponId);
        }
        if (result.equals(DUPLICATED)) {
            throw new UserCouponDuplicatedException(userId, couponId);
        }
    }

}

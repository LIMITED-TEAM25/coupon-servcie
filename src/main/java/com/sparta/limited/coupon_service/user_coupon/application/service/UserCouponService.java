package com.sparta.limited.coupon_service.user_coupon.application.service;

import com.sparta.limited.coupon_service.user_coupon.application.dto.response.UserCouponCreateResponse;
import com.sparta.limited.coupon_service.user_coupon.application.mapper.UserCouponMapper;
import com.sparta.limited.coupon_service.user_coupon.application.service.coupon.CouponFacade;
import com.sparta.limited.coupon_service.user_coupon.domain.model.UserCoupon;
import com.sparta.limited.coupon_service.user_coupon.domain.repository.UserCouponRepository;
import com.sparta.limited.coupon_service.user_coupon.domain.validator.UserCouponDuplicateValidator;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserCouponService {

    private final UserCouponRepository userCouponRepository;
    private final CouponFacade couponFacade;

    @Transactional
    public UserCouponCreateResponse creatUserCoupon(
        UUID couponId,
        Long userId
    ) {
        boolean isDuplicated = userCouponRepository.existsByUserIdAndCouponId(userId, couponId);
        UserCouponDuplicateValidator.duplicateValidate(isDuplicated, userId, couponId);

        couponFacade.decreaseQuantity(couponId);

        UserCoupon userCoupon = UserCouponMapper.toEntity(couponId, userId);
        userCouponRepository.save(userCoupon);

        return UserCouponMapper.toCreateResponse(userCoupon);
    }
}

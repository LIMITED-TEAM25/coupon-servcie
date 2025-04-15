package com.sparta.limited.coupon_service.coupon.application.dto.response;

import com.sparta.limited.coupon_service.coupon.domain.model.CouponStatus;
import java.util.UUID;
import lombok.Getter;

@Getter
public class CouponCreateResponse {

    private final UUID id;
    private final String name;
    private final Integer discountRate;
    private final CouponStatus status;

    private CouponCreateResponse(
        UUID id,
        String name,
        Integer discountRate,
        CouponStatus status
    ) {
        this.id = id;
        this.name = name;
        this.discountRate = discountRate;
        this.status = status;
    }

    public static CouponCreateResponse of(
        UUID id,
        String name,
        Integer discountRate,
        CouponStatus status
    ) {
        return new CouponCreateResponse(
            id,
            name,
            discountRate,
            status
        );
    }

}

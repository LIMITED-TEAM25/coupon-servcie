package com.sparta.limited.coupon_service.application.dto.response;

import com.sparta.limited.coupon_service.domain.model.CouponStatus;
import java.util.UUID;
import lombok.Getter;

@Getter
public class CouponCreateResponse {

    private final UUID id;
    private final String name;
    private final Integer discountRate;
    private final Long quantity;
    private final CouponStatus status;

    private CouponCreateResponse(
        UUID id,
        String name,
        Integer discountRate,
        Long quantity,
        CouponStatus status
    ) {
        this.id = id;
        this.name = name;
        this.discountRate = discountRate;
        this.quantity = quantity;
        this.status = status;
    }

    public static CouponCreateResponse of(
        UUID id,
        String name,
        Integer discountRate,
        Long quantity,
        CouponStatus status
    ) {
        return new CouponCreateResponse(
            id,
            name,
            discountRate,
            quantity,
            status
        );
    }

}

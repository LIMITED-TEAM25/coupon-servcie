package com.sparta.limited.coupon_service.coupon.application.dto.request;

import lombok.Getter;

@Getter
public class CouponCreateRequest {

    private String name;
    private Integer discountRate;
    private Long quantity;

}

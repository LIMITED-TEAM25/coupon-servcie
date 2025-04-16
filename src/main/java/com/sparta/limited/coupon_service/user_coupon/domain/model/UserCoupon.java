package com.sparta.limited.coupon_service.user_coupon.domain.model;

import com.sparta.limited.common_module.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "p_user_coupon")
public class UserCoupon extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "coupon_id", nullable = false)
    private UUID couponId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "status", nullable = false)
    private UserCouponStatus status;

    private UserCoupon(
        UUID couponId,
        Long userId
    ) {
        this.couponId = couponId;
        this.userId = userId;
        this.status = UserCouponStatus.NOT_USED;
    }

    public static UserCoupon of(
        UUID couponId,
        Long userId
    ) {
        return new UserCoupon(
            couponId,
            userId
        );
    }

}

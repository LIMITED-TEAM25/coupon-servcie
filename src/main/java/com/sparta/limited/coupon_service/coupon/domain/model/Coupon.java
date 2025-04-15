package com.sparta.limited.coupon_service.coupon.domain.model;

import com.sparta.limited.common_module.common.BaseEntity;
import com.sparta.limited.coupon_service.coupon.domain.exception.CouponOutOfStockException;
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

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "p_coupon")
public class Coupon extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "discount_rate", nullable = false)
    private Integer discountRate;

    @Column(name = "status", nullable = false)
    private CouponStatus status;

    @Column(name = "quantity", nullable = false)
    private Long quantity;

    private Coupon(
        String name,
        Integer discountRate,
        Long quantity
    ) {
        this.name = name;
        this.discountRate = discountRate;
        this.quantity = quantity;
        this.status = CouponStatus.ACTIVE;
    }

    public static Coupon of(
        String name,
        Integer discountRate,
        Long quantity
    ) {
        return new Coupon(
            name,
            discountRate,
            quantity
        );
    }

    public void decreaseQuantity() {
        if (quantity <= 0) {
            throw new CouponOutOfStockException(id);
        }
        this.quantity--;
    }
}

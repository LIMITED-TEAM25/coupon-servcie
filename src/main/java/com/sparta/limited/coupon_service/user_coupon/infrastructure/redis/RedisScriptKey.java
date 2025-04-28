package com.sparta.limited.coupon_service.user_coupon.infrastructure.redis;

import lombok.Getter;

@Getter
public enum RedisScriptKey {
    QUANTITY_AND_DUPLICATE("lua/user_coupon/quantity-duplicate.lua"),
    ;

    private final String luaScripName;

    RedisScriptKey(
        String luaScripName
    ) {
        this.luaScripName = luaScripName;
    }

}

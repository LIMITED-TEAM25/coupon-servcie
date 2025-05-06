package com.sparta.limited.coupon_service.infrastructure.redis;

import lombok.Getter;

@Getter
public enum RedisScriptKey {
    QUANTITY_AND_DUPLICATE("lua/quantity-duplicate.lua"),
    ;

    private final String luaScripName;

    RedisScriptKey(
        String luaScripName
    ) {
        this.luaScripName = luaScripName;
    }

}

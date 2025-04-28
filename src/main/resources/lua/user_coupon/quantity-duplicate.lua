-- KEYS[1]=coupon:{id}:stock, KEYS[2]=coupon:{id}:users, ARGV[1]=userId
local remain = redis.call("DECR", KEYS[1])
if not remain or remain < 0 then
  redis.call("INCR", KEYS[1])
  return "OUT_OF_STOCK"
end
if redis.call("SADD", KEYS[2], ARGV[1]) == 0 then
  redis.call("INCR", KEYS[1])
  return "DUPLICATED"
end
return {ok=tostring(remain)}
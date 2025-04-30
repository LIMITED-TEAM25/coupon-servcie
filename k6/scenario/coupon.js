import http from 'k6/http';
import { check } from 'k6';
import { Counter } from 'k6/metrics';

const totalRequests = new Counter('total_requests');
const success201 = new Counter('success_201');
const duplicated409 = new Counter('duplicated_409')
const failedRequests = new Counter('failed_requests');
const outOfStock400 = new Counter('outOfStock_400');
const error5xx = new Counter('errorCode_5xx');
const timeoutErrors = new Counter('timeout_errors');
const connResetErrors = new Counter('connection_reset_errors');

export const options = {
  scenarios: {
    load_test: {
      executor: 'constant-arrival-rate',
      rate: 3000,
      duration: '30s',
      preAllocatedVUs: 6000,
      maxVUs: 30000,
      gracefulStop: '5m',
    },
  },
};

const couponId = "50fd0ed6-1285-4d97-9039-c5669d53a752";

export default function () {
  const userId = (__VU * 10000) + __ITER;
  // const userId = __VU;
  const url = `http://user-coupon-service:19099/api/v1/user-coupons/${couponId}`
  const headers = {'X-User-Id' : userId.toString()};

  let res = http.post(url, null, {headers});
  if (res.error_code === 'ETIMEDOUT' || (res.error && res.error.includes('timeout'))) {
    timeoutErrors.add(1);
    return;
  }
  if (res.error_code === 'ECONNRESET' || (res.error && res.error.includes('connection reset'))) {
    connResetErrors.add(1);
    return;
  }
  if (res.error) {
    failedRequests.add(1);  // 그 외 네트워크 에러
    return;
  }

  totalRequests.add(1);

  if (res.status === 201) {
    success201.add(1);
  } else if (res.status === 409) {
    duplicated409.add(1);
  } else if (res.status === 400) {
    outOfStock400.add(1);
  } else if (res.status >= 500) {
    error5xx.add(1)
  }

  check(res, {
    'status is 201' : (r) => r.status === 201
  });
}
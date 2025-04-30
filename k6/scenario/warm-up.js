
import http from 'k6/http';

export const options = {
  stages: [
    { duration: '30s', target: 50 },
    { duration: '30s', target: 500 },
    { duration: '30s', target: 1000 },
    { duration: '30s', target: 1500 },
    { duration: '1m', target: 2000 }, // 여기서 부하를 주면서 웜업 진행
    { duration: '1m', target: 2000 }, // 1분간 2000req/s를 지속하며 steady state 상태로 올라왔는지 체크
  ],
  thresholds: {
    'http_req_duration': ['p(95)<2000'],
  },
};

const couponId = "f58bfbb0-368c-4ea7-8f85-ff758605a468";
const baseUrl = "http://user-coupon-service:19099";

export default function () {

  const userId = (__VU * 10000) + __ITER;
  const url = `${baseUrl}/api/v1/user-coupons/${couponId}`
  const headers = {'X-User-Id' : userId.toString()};

  http.post(url, null, {headers});
}

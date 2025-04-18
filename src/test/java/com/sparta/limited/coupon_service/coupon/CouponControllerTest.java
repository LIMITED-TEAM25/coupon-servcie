package com.sparta.limited.coupon_service.coupon;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.limited.coupon_service.coupon.application.dto.request.CouponCreateRequest;
import com.sparta.limited.coupon_service.coupon.presentation.external.controller.CouponController;
import org.apache.http.HttpHeaders;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.context.TestConstructor.AutowireMode;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@SpringBootTest
@WebMvcTest(controllers = CouponController.class)
@AutoConfigureMockMvc
@TestConstructor(autowireMode = AutowireMode.ALL)
@DisplayName("API:Coupon")
public class CouponControllerTest {

    private final MockMvc mockMvc;

    private final ObjectMapper objectMapper;

    public CouponControllerTest(MockMvc mockMvc, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    @Test
    @DisplayName("쿠폰 생성 컨트롤러 테스트")
    void createCoupon() throws Exception {

        CouponCreateRequest couponCreateRequest = CouponCreateRequest.of(
            "테스트 쿠폰명",
            10,
            10L
        );

        ResultActions resultActions = mockMvc.perform(post("/api/v1/coupons")
            .header("X-User-Role", "ROLE_ADMIN")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(couponCreateRequest))
        );

        resultActions.andDo(print())
            .andExpect(status().isCreated())
            .andExpect(header().exists(HttpHeaders.LOCATION));
    }

}

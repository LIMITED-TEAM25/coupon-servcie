package com.sparta.limited.coupon_service.coupon;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.limited.coupon_service.coupon.application.dto.request.CouponCreateRequest;
import com.sparta.limited.coupon_service.coupon.domain.model.Coupon;
import com.sparta.limited.coupon_service.coupon.domain.model.CouponStatus;
import com.sparta.limited.coupon_service.coupon.domain.repository.CouponRepository;
import com.sparta.limited.coupon_service.coupon.infrastructure.persistence.JpaCouponRepository;
import java.util.UUID;
import org.apache.http.HttpHeaders;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.context.TestConstructor.AutowireMode;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@SpringBootTest
@AutoConfigureMockMvc
@TestConstructor(autowireMode = AutowireMode.ALL)
@DisplayName("API:Coupon")
public class CouponControllerTest {

    private final MockMvc mockMvc;

    private final ObjectMapper objectMapper;

    @Autowired
    private CouponRepository couponRepository;

    @Autowired
    private JpaCouponRepository jpaCouponRepository;

    private UUID couponId;
    private String couponName;
    private Integer discountRate;
    private Long quantity;

    public CouponControllerTest(MockMvc mockMvc, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    @BeforeEach
    void setUp() {
        Coupon coupon = Coupon.of(
            "테스트용 쿠폰",
            10,
            10L
        );
        couponRepository.save(coupon);
        couponId = coupon.getId();
        couponName = coupon.getName();
        discountRate = coupon.getDiscountRate();
        quantity = coupon.getQuantity();
    }

    @AfterEach
    void deleteTestCoupon() {
        jpaCouponRepository.deleteById(couponId);
    }

    @Test
    @DisplayName("쿠폰 생성 컨트롤러 테스트")
    void createCoupon() throws Exception {

        CouponCreateRequest couponCreateRequest = CouponCreateRequest.of(
            "테스트 쿠폰 생성",
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
            .andExpect(jsonPath("name").value(couponCreateRequest.getName()))
            .andExpect(jsonPath("discountRate").value(couponCreateRequest.getDiscountRate()))
            .andExpect(jsonPath("quantity").value(couponCreateRequest.getQuantity()))
            .andExpect(header().exists(HttpHeaders.LOCATION));
    }

    @Test
    @DisplayName("쿠폰 단건 조회 컨트롤러 테스트")
    void getOneCoupon() throws Exception {

        ResultActions resultActions = mockMvc.perform(get("/api/v1/coupons/" + couponId));

        resultActions.andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("couponId").value(couponId.toString()))
            .andExpect(jsonPath("name").value(couponName))
            .andExpect(jsonPath("discountRate").value(discountRate))
            .andExpect(jsonPath("status").value(CouponStatus.ACTIVE.toString()))
            .andExpect(jsonPath("quantity").value(quantity));

    }

}

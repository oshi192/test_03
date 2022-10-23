package com.yuriikoss.codingtask03sevenhillsgrouptech.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yuriikoss.codingtask03sevenhillsgrouptech.entity.dto.PurchaseDto;
import com.yuriikoss.codingtask03sevenhillsgrouptech.repository.PurchaseRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Date;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.util.Assert.state;

public class PurchaseControllerTest extends BasicControllerTest {

    private static final long USER_ID = 11;
    @Autowired
    private PurchaseRepository purchaseRepository;

    @Test
    void postPurchase() throws Exception {
        PurchaseDto purchase = createPurchaseDto();
        ObjectMapper objectMapper = new ObjectMapper();
        long purchaseAmountBefore = purchaseRepository.count();
        long userBonusAmountBefore = userRepository.findById(USER_ID).get().getBonusAmount();

        mockMvc.perform(post("/purchase")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.convertValue(purchase, JsonNode.class).toString()))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        long purchaseAmountAfter = purchaseRepository.count();
        long userBonusAmountAfter = userRepository.findById(USER_ID).get().getBonusAmount();

        state(purchaseAmountAfter == purchaseAmountBefore + 1, "Purchases amount after response must be greater than before by 1");
        state(userBonusAmountAfter > userBonusAmountBefore, "Bonus  after response must be greater than before");
    }

    @Test
    void postPurchaseWrongUserId() throws Exception {
        PurchaseDto purchase = createPurchaseDto();
        purchase.setUserId(USER_ID_NOT_EXIST);
        ObjectMapper objectMapper = new ObjectMapper();
        long purchaseAmountBefore = purchaseRepository.count();
        MvcResult result = mockMvc.perform(post("/purchase")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.convertValue(purchase, JsonNode.class).toString()))
                .andExpect(status().is5xxServerError())
                .andReturn();

        long purchaseAmountAfter = purchaseRepository.count();

        state(purchaseAmountAfter == purchaseAmountBefore, "Purchases amount before and after response must be equals");
    }

    private PurchaseDto createPurchaseDto() {
        return PurchaseDto.builder()
                .purchaseDate(new Date())
                .valueCents(12222)
                .userId(USER_ID)
                .build();
    }
}

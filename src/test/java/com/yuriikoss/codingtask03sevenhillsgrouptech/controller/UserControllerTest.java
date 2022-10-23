package com.yuriikoss.codingtask03sevenhillsgrouptech.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yuriikoss.codingtask03sevenhillsgrouptech.entity.User;
import org.junit.jupiter.api.Test;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.util.Assert.notNull;
import static org.springframework.util.Assert.state;

public class UserControllerTest extends BasicControllerTest {

    private static final int USER_ID_TO_DELETE = 11;

    @Test
    void postUser() throws Exception {
        User userNewOne = createUser();
        ObjectMapper objectMapper = new ObjectMapper();
        long amountBefore = userRepository.count();

        String resultJson = mockMvc.perform(post("/user")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.convertValue(userNewOne, JsonNode.class).toString()))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        User resultUser = objectMapper.readValue(resultJson, User.class);
        long amountAfter = userRepository.count();

        notNull(resultUser.getUserId(), "The Id must be not null after insertion.");
        state(resultUser.getEmail().equals(userNewOne.getEmail()), "User email cant be changed after insertion");
        state(resultUser.getFullName().equals(userNewOne.getFullName()), "User email cant be changed after insertion");
        state(resultUser.getPhone().equals(userNewOne.getPhone()), "User phone cant be changed after insertion");
        state(amountAfter == amountBefore + 1, "Users table must be changed by 1 row, no bigger no smaller");
    }

    @Test
    void deleteUser() throws Exception {
        long amountBefore = userRepository.count();

        mockMvc.perform(delete("/user/" + USER_ID_TO_DELETE).contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());

        long amountAfter = userRepository.count();

        state(amountAfter == amountBefore - 1, "Users table must be changed by 1 row, no bigger no smaller");
    }

    @Test
    void deleteUserWrongId() throws Exception {
        long amountBefore = userRepository.count();
        mockMvc.perform(delete("/user/" + USER_ID_NOT_EXIST).contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is5xxServerError());

        long amountAfter = userRepository.count();

        state(amountAfter == amountBefore, "Users table must be changed by 1 row, no bigger no smaller");
    }

    private User createUser() {
        User user = new User();
        user.setBonusAmount(0);
        user.setEmail("spider@gmail.com");
        user.setFullName("Peter Parker");
        user.setPhone("+1239494949");
        return user;
    }
}

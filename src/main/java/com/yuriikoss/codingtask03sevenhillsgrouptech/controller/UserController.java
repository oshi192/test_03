package com.yuriikoss.codingtask03sevenhillsgrouptech.controller;

import com.yuriikoss.codingtask03sevenhillsgrouptech.entity.User;
import com.yuriikoss.codingtask03sevenhillsgrouptech.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@ApiOperation("Reward    API")
public class UserController {

    final UserService userService;

    @GetMapping("/{userId}")
    public User findUser(@PathVariable @ApiParam(name = "id", value = "User id", example = "1") Long userId) {
        return userService.findUserById(userId);
    }

    @GetMapping("/{userId}/reward")
    public Long getReward(@PathVariable @ApiParam(name = "id", value = "User id", example = "1") Long userId) {
        return userService.findBonusAmount(userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody @Valid @ApiParam(name = "user", value = "User params") User user) {
        return userService.saveUser(user);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeUser(@PathVariable @ApiParam(name = "id", value = "User id", example = "1") Long userId) {
        userService.removeUser(userId);
    }
}

package com.yuriikoss.codingtask03sevenhillsgrouptech.controller;

import com.yuriikoss.codingtask03sevenhillsgrouptech.entity.Purchase;
import com.yuriikoss.codingtask03sevenhillsgrouptech.entity.dto.PurchaseDto;
import com.yuriikoss.codingtask03sevenhillsgrouptech.service.PurchaseService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/purchase")
@RequiredArgsConstructor
@ApiOperation("Reward    API")
public class PurchaseController {
    private final PurchaseService purchaseService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Abb new purchase", notes = "Add new user purchase and update reward points if needed.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully added"),
            @ApiResponse(code = 500, message = "User not found.")
    })
    public Purchase addPurchase(@RequestBody @Valid @ApiParam(name = "purchaseDto", value = "Purchase data")
                                PurchaseDto purchaseDto) {
        return purchaseService.addPurchase(purchaseDto);
    }
}

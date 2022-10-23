package com.yuriikoss.codingtask03sevenhillsgrouptech.entity.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Builder
public class PurchaseDto {
    @NotNull
    private Long userId;
    @Min(0)
    private int valueCents;
    private Date purchaseDate;
}

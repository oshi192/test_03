package com.yuriikoss.codingtask03sevenhillsgrouptech.service;

import com.yuriikoss.codingtask03sevenhillsgrouptech.entity.Purchase;
import com.yuriikoss.codingtask03sevenhillsgrouptech.entity.User;
import com.yuriikoss.codingtask03sevenhillsgrouptech.entity.dto.PurchaseDto;
import com.yuriikoss.codingtask03sevenhillsgrouptech.repository.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final UserService userService;
    private final RewardService rewardService;

    public Purchase addPurchase(PurchaseDto purchaseDto) {
        User user = userService.findUserById(purchaseDto.getUserId());
        Purchase purchase = convertToPurchase(purchaseDto);
        int rewardAmount = rewardService.calculateReward(purchaseDto.getValueCents());
        purchase.setUser(user);

        purchase = purchaseRepository.save(purchase);
        userService.updateBonus(user, rewardAmount);
        return purchase;
    }

    private Purchase convertToPurchase(PurchaseDto purchaseDto) {
        return Purchase.builder()
                .purchaseDate(purchaseDto.getPurchaseDate())
                .valueCents(purchaseDto.getValueCents())
                .build();
    }
}

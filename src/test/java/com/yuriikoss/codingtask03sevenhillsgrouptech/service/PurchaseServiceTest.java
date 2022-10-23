package com.yuriikoss.codingtask03sevenhillsgrouptech.service;

import com.yuriikoss.codingtask03sevenhillsgrouptech.entity.Purchase;
import com.yuriikoss.codingtask03sevenhillsgrouptech.entity.User;
import com.yuriikoss.codingtask03sevenhillsgrouptech.entity.dto.PurchaseDto;
import com.yuriikoss.codingtask03sevenhillsgrouptech.repository.PurchaseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class PurchaseServiceTest {
    private static final Long USER_ID = 11L;
    private static final int REWARD_AMOUNT = 10;
    private static final int PURCHASE_VALUE = 10000;

    @Mock
    private PurchaseRepository purchaseRepository;
    @Mock
    private UserService userService;
    @Mock
    private RewardService rewardService;
    @InjectMocks
    private PurchaseService purchaseService;

    @Test
    public void addPurchase() {
        PurchaseDto dto = createPurchaseDto();
        User user = createUser();
        Purchase purchase = createPurchase(user);

        Mockito.when(userService.findUserById(USER_ID)).thenReturn(user);
        Mockito.when(rewardService.calculateReward(dto.getValueCents())).thenReturn(REWARD_AMOUNT);
        Mockito.when(purchaseRepository.save(any())).thenReturn(purchase);
        Mockito.doNothing().when(userService).updateBonus(user, purchase.getValueCents());

        purchaseService.addPurchase(dto);

        verify(userService).findUserById(USER_ID);
        verify(rewardService).calculateReward(dto.getValueCents());
        verify(purchaseRepository).save(any());
        verify(userService).updateBonus(user, REWARD_AMOUNT);
    }

    private Purchase createPurchase(User user) {
        return Purchase.builder()
                .user(user)
                .valueCents(PURCHASE_VALUE)
                .purchaseDate(new Date())
                .build();
    }

    private PurchaseDto createPurchaseDto() {
        return PurchaseDto.builder()
                .purchaseDate(new Date())
                .valueCents(PURCHASE_VALUE)
                .userId(USER_ID)
                .build();
    }

    private User createUser() {
        User user = new User();
        user.setBonusAmount(0);
        user.setEmail("spider@gmail.com");
        user.setFullName("Peter Parker");
        user.setPhone("+1239494949");
        user.setUserId(USER_ID);
        return user;
    }
}

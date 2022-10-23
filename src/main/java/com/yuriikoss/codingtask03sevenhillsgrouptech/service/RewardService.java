package com.yuriikoss.codingtask03sevenhillsgrouptech.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RewardService {
    private static final int REGULAR_BONUS_START_CENTS = 5000;
    private static final int DOUBLE_BONUS_START_CENTS = 10000;
    private static final int REGULAR_BONUS_MULTIPLIER = 1;
    private static final int DOUBLE_BONUS_MULTIPLIER = 2;

    public int calculateReward(int valueCents) {
        int bonus = 0;
        if ((valueCents - REGULAR_BONUS_START_CENTS) > 0) {
            if ((valueCents - DOUBLE_BONUS_START_CENTS) > 0) {
                bonus = (DOUBLE_BONUS_START_CENTS - REGULAR_BONUS_START_CENTS) * REGULAR_BONUS_MULTIPLIER +
                        (valueCents - DOUBLE_BONUS_START_CENTS) * DOUBLE_BONUS_MULTIPLIER;
            } else {
                bonus = (valueCents - REGULAR_BONUS_START_CENTS) * REGULAR_BONUS_MULTIPLIER;
            }
        }
        return bonus;
    }
}

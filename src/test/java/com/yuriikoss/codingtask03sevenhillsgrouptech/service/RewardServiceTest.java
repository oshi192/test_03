package com.yuriikoss.codingtask03sevenhillsgrouptech.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import static org.springframework.util.Assert.isTrue;

@ExtendWith(MockitoExtension.class)
public class RewardServiceTest {

    private static final int DOUBLE_MULTIPLIER = 2;
    private static final int INVALID_MONEY_AMOUNT = -1;
    private static final int LOVER_VALUE_CENTS = 4900;
    private static final int MIDDLE_VALUE_CENTS = 7500;
    private static final int HIGH_VALUE_CENTS = 17500;
    private static final int REGULAR_BONUS_START_CENTS = 5000;
    private static final int DOUBLE_BONUS_START_CENTS = 10000;
    private RewardService rewardService = new RewardService();

    @Test
    public void calculateRewardZeroReward() {
        int bonus = rewardService.calculateReward(LOVER_VALUE_CENTS);
        isTrue(bonus == 0, "There are no bonus if purchase lower than 50$");
    }

    @Test
    public void calculateRewardOneToOneWalue() {
        int bonus = rewardService.calculateReward(MIDDLE_VALUE_CENTS);
        isTrue(bonus == MIDDLE_VALUE_CENTS - REGULAR_BONUS_START_CENTS, "The bonus value must be 50$ lower than income value");
    }

    @Test
    public void calculateRewardDouble() {
        int bonus = rewardService.calculateReward(HIGH_VALUE_CENTS);
        isTrue(bonus == (HIGH_VALUE_CENTS - DOUBLE_BONUS_START_CENTS) * DOUBLE_MULTIPLIER + REGULAR_BONUS_START_CENTS, "The bonus value must 2 times bigger for than value of income $ over 100 plus 50");
    }

    @Test
    public void calculateRewardInvalidValue() {
        int bonus = rewardService.calculateReward(INVALID_MONEY_AMOUNT);
        isTrue(bonus == 0, "There are no bonus if purchase lower than 50$");
    }

}

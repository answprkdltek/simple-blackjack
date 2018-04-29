package com.cnu.blackjack;

import lombok.Data;

@Data
public class UserPlayerController extends PlayerController{

    public int placeBet(int balance, int minBet) {
        return AppIO.in_betAmount(minBet,balance);
    }

    public boolean wannaHit(Hand hand) {
        return AppIO.in_wannaHit();
    }
}

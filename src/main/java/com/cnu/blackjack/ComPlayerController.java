package com.cnu.blackjack;

import lombok.Data;

@Data
public class ComPlayerController extends PlayerController{

    public int placeBet(int balance, int minBet) {
        return 0;
    }

    public boolean wannaHit(Hand hand) {
        return false;
    }
}

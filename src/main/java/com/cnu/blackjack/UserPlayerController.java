package com.cnu.blackjack;

import lombok.Data;

@Data
public class UserPlayerController extends PlayerController{

    public int placeBet(int balance, int minBet) {
        return AppIO.AppIO_in_bet(balance, minBet);
    }

    public boolean wannaHit(Hand hand) {
        return AppIO.AppIO_in_wannaHit();
    }
}

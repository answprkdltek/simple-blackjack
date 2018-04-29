package com.cnu.blackjack;

import lombok.Data;

@Data
public class UserPlayerController extends PlayerController{

    public int placeBet(int balance) {
        return AppIO.AppIO_in_bet;
    }

    public boolean wannaHit() {
        return false;
    }
}

package com.cnu.blackjack;

import lombok.Data;

@Data
public abstract class PlayerController{

    public abstract int placeBet(int balance, int minBet);

    public abstract boolean wannaHit(Hand hand);
}

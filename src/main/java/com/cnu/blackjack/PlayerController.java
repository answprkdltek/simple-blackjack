package com.cnu.blackjack;

import lombok.Data;

@Data
public abstract class PlayerController{
    public abstract int placeBet(int balance);

    public abstract boolean wannaHit();
}

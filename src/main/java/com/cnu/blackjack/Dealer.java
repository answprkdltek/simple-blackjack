package com.cnu.blackjack;

import java.util.concurrent.ThreadLocalRandom;

public class Dealer {

    public int getScore() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return random.nextInt(17, 25);
    }
}

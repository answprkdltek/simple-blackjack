package com.cnu.blackjack;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class DealerTest {

    @Test
    public void 딜러는_핸드를_가지고있다() {
        Dealer dealer = new Dealer();
        assertNotNull(dealer.getHand());
    }
}

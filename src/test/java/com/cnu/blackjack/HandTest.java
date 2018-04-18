package com.cnu.blackjack;

import org.junit.Test;

import static org.junit.Assert.*;

public class HandTest {

    @Test
    public void 핸드는_카드를_한장씩_받을수_있다() {
        Hand hand = new Hand(new Deck(1));
        Card card = hand.drawCard();
        int handSize = hand.getCurrentHandSize();
        assertNotNull(card);
        assertEquals(1, handSize);
    }
}

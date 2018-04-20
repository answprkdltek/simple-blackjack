package com.cnu.blackjack;

import org.junit.Test;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EvaluatorTest {

    @Test
    public void 카드_합이_22이면_버스트다(){
        Hand hand = new Hand();
        hand.receiveCard(new Card(10, Suit.SPADE));
        hand.receiveCard(new Card(10, Suit.CLUB));
        hand.receiveCard(new Card(2, Suit.CLUB));

        Evaluator evaluator = new Evaluator();
        boolean isBusted = evaluator.checkBust(hand);
        assertTrue(isBusted);
    }

    @Test
    public void 카드_합이_21이면_버스트가_아니다(){
        Hand hand = new Hand();
        hand.receiveCard(new Card(13, Suit.SPADE));
        hand.receiveCard(new Card(1, Suit.CLUB));

        Evaluator evaluator = new Evaluator();
        boolean isBusted = evaluator.checkBust(hand);
        assertFalse(isBusted);
    }
}

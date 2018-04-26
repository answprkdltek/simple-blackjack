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
        hand.receiveCard(new Card(2, Suit.CLUB));     //합이 22인 카드를 받는다.

        Evaluator evaluator = new Evaluator();
        boolean isBusted = evaluator.checkBust(hand);
        assertTrue(isBusted);
    }

    @Test
    public void 카드_합이_21이면_버스트가_아니다(){
        Hand hand = new Hand();
        hand.receiveCard(new Card(13, Suit.SPADE));
        hand.receiveCard(new Card(1, Suit.CLUB));    //합이 21인 카드를 받는다(A, K)

        Evaluator evaluator = new Evaluator();
        boolean isBusted = evaluator.checkBust(hand);
        assertFalse(isBusted);
    }

    @Test
    public voud 카드가_K_J_이면_합이_20이다(){
        Hand hand = new Hand();
        hand.receiveCard(new Card(11, Suit.SPADE));   // J
        hand.receiveCard(new Card(12, Suit.CLUB));    // K

        Evaluator evaluator = new Evaluator();
        assertTrue(evaluator.checkScore(hand) == 20);  //checkScore는 손에 든 카드의 합을 반환
    }

    @Test
    public void 카드가_5_6_7_이면_합이_18이다(){

        Hand hand = new Hand();
        hand.receiveCard(new Card(5, Suit.SPADE));   // 5
        hand.receiveCard(new Card(6, Suit.CLUB));    // 6
        hand.receiveCard(new Card(7, Suit.CLUB));    // 7

        Evaluator evaluator = new Evaluator();
        assertTrue(evaluator.checkScore(hand) == 18);
    }

    @Test
    public void 카드가_A_A_이면_합은_2_또는_12이다(){

        Hand hand = new Hand();
        hand.receiveCard(new Card(1, Suit.SPADE));   // A
        hand.receiveCard(new Card(1, Suit.CLUB));    // A

        Evaluator evaluator = new Evaluator();
        assertTrue(evaluator.checkScore(hand) == 2 || evaluator.checkScore(hand) == 12);
    }

}

package com.cnu.blackjack;

import org.junit.Test;

import static org.junit.Assert.*;

public class HandTest {

    @Test
    public void 핸드는_카드를_받을수_있다() {
        Hand hand = new Hand();
        Card card = new Card(1, Suit.CLUB);
        hand.receiveCard(card);

        int nCards = hand.getCurrentHandSize();
        assertEquals(1, nCards);

        Card handCard = hand.getCard(0);
        assertEquals(card, handCard);
    }

    @Test
    public void 게임이_끝나면_카드를_모두버린다(){
        Hand hand = new Hand();
        hand.receiveCard(new Card(1, Suit.CLUB));
        hand.receiveCard(new Card(5, Suit.HEART));

        hand.clearHand();

        int nCards = hand.getCurrentHandSize();
        assertEquals(0, nCards);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void 없는_카드를_가져올_수_없다() {
        Hand hand = new Hand();
        hand.receiveCard(new Card(1, Suit.CLUB));

        Card card = hand.getCard(1);
    }

    @Test
    public void 현재_가지고있는_카드는_몇장인지(){
        Hand hand = new Hand();
        Card card1 = new Card(1, Suit.DIAMOND);
        Card card2 = new Card(2, Suit.DIAMOND);
        hand.receiveCard(card1);
        hand.receiveCard(card2);
        int nCards = hand.getCurrentHandSize();
        assertEquals(2, nCards);
    }

    @Test
    public void 카드를_받을땐_공개된상태이다(){
        Hand hand = new Hand();
        hand.receiveCard(new Card(1, Suit.CLUB));
        hand.receiveCard(new Card(5, Suit.HEART));

        assertTrue(hand.isOpened(0));
        assertTrue(hand.isOpened(1));
    }

    @Test
    public void 카드를_뒤집을_수_있다(){
        Hand hand = new Hand();
        hand.receiveCard(new Card(1, Suit.CLUB));

        hand.closeCard(0);
        boolean isClosed = (!hand.isOpened(0));
        assertTrue(isClosed);
    }

    @Test
    public void 카드를_오픈할_수_있다(){
        Hand hand = new Hand();
        hand.receiveCard(new Card(1, Suit.CLUB));
        hand.closeCard(0);

        hand.openCard(0);

        boolean isOpened = (hand.isOpened(0));
        assertTrue(isOpened);
    }
}

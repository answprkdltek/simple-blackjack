package com.cnu.blackjack;

import org.junit.Test;

import static org.junit.Assert.*;

public class HandTest {

    @Test
    public void 핸드는_카드를_받을수_있다() {
        Hand hand = new Hand(new Deck(1));
        Card card = new Card(1, Suit.CLUB);
        hand.recieveCard(card);
    }

    @Test
    public void 게임이_끝나면_카드를_모두버린다(){
        Hand hand = new Hand(new Deck(1));
        Hand.clearHand();
        int cards = hand.getCurrentHandSize();
        assertTrue(cards==0);
    }

    @Test
    public void 현재_가지고있는_카드는_몇장인지(){
        Hand hand = new Hand(new Deck(1));
        Card card1 = new Card(1, Suit.DIAMOND);
        Card card2 = new Card(2, Suit.DIAMOND);
        hand.recieveCard(card1);
        hand.recieveCard(card2);
        int cards = hand.getCurrentHandSize();
        assertTrue(cards == 2);
    }

    @Test
    public void 카드의_랭크와_모양을_알수있다(){
        Hand hand = new Hand(new Deck(1));
        Card card = new Card(1, Suit.CLUB);
        hand.getCard(card);
    }

    @Test
    public void 카드를_뒤집어_둔다(){
        Hand hand = new Hand(new Deck(1));
        boolean isClosed = hand.closeCard();
        assertTrue(isClosed == true);
    }

    @Test
    public void 카드를_오픈한다(){
        Hand hand = new Hand(new Deck(1));
        boolean isOpen = hand.openCard();
        assertTrue(isOpen == true);
    }
}

package com.cnu.blackjack;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DeckTest {

    @Test
    public void 하나의_덱은_총_52장의_카드를_가진다() {
        Deck deck = new Deck(1);
        int totalCardCount = deck.getTotalCardCount();
        assertEquals(totalCardCount, 52);
    }

    @Test
    public void 세개의_덱은_총_156장의_카드를_가진다() {
        Deck deck = new Deck(3);
        int totalCardCount = deck.getTotalCardCount();
        assertEquals(156, totalCardCount);
    }

    @Test
    public void 하나의_덱에서_하나의_카드를_뽑으면_51장이_남아야_한다() {
        Deck deck = new Deck(1);
        deck.drawCard();
        int totalCardCount = deck.getTotalCardCount();
        assertEquals(51, totalCardCount);
    }

    @Test
    public void 하나의_덱에서_모든_카드를_뽑으면_하나도_남지_않아야_한다() {
        Deck deck = new Deck(1);
        for (int i = deck.getTotalCardCount(); i > 0; i--) {
            deck.drawCard();
        }
        int totalCardCount = deck.getTotalCardCount();
        assertEquals(0, totalCardCount);
    }
}

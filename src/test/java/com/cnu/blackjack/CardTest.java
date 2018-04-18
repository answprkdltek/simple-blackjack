package com.cnu.blackjack;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class CardTest {

    @Test
    public void 스페이드_10_카드를_생성할_수_있다() {
        Card card = new Card(1, Suit.SPADE);
        int rank = card.getRank();
        Suit suit = card.getSuit();
        assertEquals(rank, 1);
        assertSame(suit, Suit.SPADE);
    }

    @Test(expected = NoRankException.class)
    public void 카드의_랭크는_13_이하_여야한다() {
        Card rank = new Card(15, Suit.SPADE);
    }
}

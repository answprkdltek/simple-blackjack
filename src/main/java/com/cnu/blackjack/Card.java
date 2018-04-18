package com.cnu.blackjack;

import lombok.Data;

@Data
public class Card {

    private int rank;
    private Suit suit;

    public Card(int rank, Suit suit) {
        if (rank > 13) {
            throw new NoRankException();
        }

        this.rank = rank;
        this.suit = suit;
    }
}

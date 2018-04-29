package com.cnu.blackjack;

import lombok.Data;

@Data
public class Card {

    private int rank;
    private Suit suit;

    public Card(int rank, Suit suit) {
        if (rank > 13 || rank < 1) {      //수정
            throw new NoRankException();
        }
        if(suit == null){                //추가
            throw new NoSuitExecption();
        }
        this.rank = rank;
        this.suit = suit;
    }
}

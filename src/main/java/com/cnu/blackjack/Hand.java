package com.cnu.blackjack;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Hand {
    private Deck deck;
    private static List<Card> handList = new ArrayList<>();

    public Hand(Deck deck) {
        this.deck = deck;
    }

    public Card drawCard() {
        Card card = deck.drawCard();
        handList.add(card);
        return card;
    }

    public int getScore(){                   //손에 든 카드의 점수 반환
        int score = 0;
        for(int i=0; i<handList.size(); i++){
            score += (handList.get(i)).getRank();
        }
        return score;
    }

}

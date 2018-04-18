package com.cnu.blackjack;

import java.util.ArrayList;
import java.util.List;

public class Deck {
    private int numberOfDeck;
    private List<Card> cardList = new ArrayList<>();

    public Deck(int numberOfDeck) {
        this.numberOfDeck = numberOfDeck;
        createCards(numberOfDeck);
    }

    private void createCards(int numberOfDeck) {
        for (int j = 0; j < numberOfDeck; j++) {
            for (int i = 1; i <= 13; i++) {
                for (Suit suit: Suit.values()) {
                    Card card = new Card(i, suit);
                    cardList.add(card);
                }
            }
        }
    }

    public int getTotalCardCount() {
        return cardList.size();
    }

    public Card drawCard() {
        return cardList.remove(0);
    }
}

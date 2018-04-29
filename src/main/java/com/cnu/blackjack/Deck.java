package com.cnu.blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private int numberOfPacks;
    private List<Card> cardList = new ArrayList<>();

    public Deck(int numberOfPacks) {
        this.numberOfPacks = numberOfPacks;
        createCards(numberOfPacks);
        shuffleCards();
    }

    private void createCards(int numberOfPacks) {
        for (int j = 0; j < numberOfPacks; j++) {
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

    public void reset() {
        cardList.clear();
        createCards(numberOfPacks);
        shuffleCards();
    }

    private void shuffleCards() {
        Collections.shuffle(cardList);
    }
}

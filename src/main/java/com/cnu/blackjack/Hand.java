package com.cnu.blackjack;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Hand {
    private List<Card> cards;
    private Map<Card, Boolean> openStates;

    public Hand() {
        this.cards = new ArrayList<Card>();
        this.openStates = new HashMap<Card, Boolean>();
    }

    public void clearHand() {
        this.cards.clear();
        this.openStates.clear();
    }

    public void recieveCard(Card card) {
        this.cards.add(card);
        this.openStates.put(card, true);
    }

    public int getCurrentHandSize() {
        return this.cards.size();
    }

    public Card getCard(int index) {
        if (!this.hasIndex(index)) {
            throw new IndexOutOfBoundsException();
        }
        return this.cards.get(index);
    }

    private boolean hasIndex(int index) {
        if (index < 0) {
            return false;
        }
        if (index >= this.getCurrentHandSize()) {
            return false;
        }
        return true;
    }

    public boolean isOpened(int index) {
        Card card = this.getCard(index);
        return this.openStates.get(card);
    }

    public void closeCard(int index) {
        Card card = this.getCard(index);
        this.openStates.put(card, false);
    }

    public void openCard(int index) {
        Card card = this.getCard(index);
        this.openStates.put(card, true);
    }
}

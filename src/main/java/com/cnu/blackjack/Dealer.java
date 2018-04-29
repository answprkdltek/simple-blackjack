package com.cnu.blackjack;

public class Dealer {
    private Hand hand;

    public Dealer(){
        this.hand = new Hand();
    }

    public Hand getHand(){
        return this.hand;
    }

    public void addCardToHand(Card card){
        this.hand.receiveCard(card);
    }
}
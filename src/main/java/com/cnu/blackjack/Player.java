package com.cnu.blackjack;

import lombok.Data;

@Data
public class Player {
    private String playerName;
    private ControllerType controllerType;
    private int balance;
    private PlayerController controller;
    private Hand hand;

    public Player(String playerName, int balance, ControllerType controllerType) {
        this.playerName = playerName;
        this.balance = balance;
        this.controllerType = controllerType;
        this.hand = new Hand();
        if (controllerType ==ControllerType.USER){
            controller = new UserPlayerController();
        }
        else{
            controller = new ComPlayerController();
        }
    }

    public boolean ableToParticipateIn(int minBet){
        if(balance < minBet){
            return false;
        }
        else{
            return true;
        }
    }

    public int placeBet(int minBet) {
        int currentBet = controller.placeBet(balance, minBet);
        balance -= currentBet;
        return currentBet;
    }

    public boolean wannaHit(){
        return controller.wannaHit(hand);
    }

    public void receiveReward(int reward) {
        balance += reward;
    }

    public void addCardToHand(Card card) {
        this.hand.receiveCard(card);
    }
}
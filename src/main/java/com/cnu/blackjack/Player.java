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
        if (controllerType ==ControllerType.User){
            controller = new UserPlayerController();
        }
        else{
            controller = new ComPlayerController();
        }
    }

    public boolean placeBaseMoney(int baseMoney){
        if(balance < baseMoney){
            return false;
        }
        else{
            balance -= baseMoney;
            return true;
        }
    }

    public int placeBet() {
        int currentBet = controller.placeBet(balance);
        balance -= currentBet;
        return currentBet;
    }

    public boolean wannaHit(){
        return controller.wannaHit();
    }

    public void receiveReward(int reward) {
        balance += reward;
    }

    public void addCardToHand(Card card) {
        this.hand.receiveCard(card);
    }
}
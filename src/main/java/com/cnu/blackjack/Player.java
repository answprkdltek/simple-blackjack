package com.cnu.blackjack;

import lombok.Data;

@Data
public class Player {
    private String playerName;
    private ControllerType controllerType;
    private int balance;
    private PlayerController controller;
    private Hand hand;
    private boolean didWinAtPreviousGame ;
    private int howMuchEarnPreviousGame;

    public Player(String playerName, int balance, ControllerType controllerType) {
        this.playerName = playerName;
        this.balance = balance;
        this.controllerType = controllerType;
        this.hand = new Hand();
        didWinAtPreviousGame = false;
        howMuchEarnPreviousGame = 0;
        if (controllerType ==ControllerType.User){
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
        int currentBet = controller.placeBet(balance, minBet, didWinAtPreviousGame, howMuchEarnPreviousGame);
        balance -= currentBet;
        return currentBet;
    }

    public boolean wannaHit(){
        return controller.wannaHit(hand);
    }

    public void amIWin(boolean result){
        didWinAtPreviousGame = result;
    }

    public void receiveReward(int reward) {
        howMuchEarnPreviousGame = reward;
        balance += reward;
    }

    public void addCardToHand(Card card) {
        this.hand.receiveCard(card);
    }
}
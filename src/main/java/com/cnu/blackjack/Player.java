package com.cnu.blackjack;

import lombok.Data;

import java.util.Scanner;

@Data
public class Player {
    private int balance;
    private int currentBet;

    public Player(int balance) {
        this.balance = balance;
    }

    public Player(int balance, PlayerController playerController) {
        this.balance = balance;
        if(playerController.equals("null")){
            ComPlayerController comPlayerController  = new ComPlayerController();
            comPlayerController.playGame();
        }
        else{
            new UserPlayerController();
        }
    }

    public void placeBet(int bet) {
        if (bet > this.balance) {
            throw new NotEnoughBalanceException();
        }
        this.balance -= bet;
        this.currentBet = bet;
    }

    public String getPlayerName() {
        Scanner scan = new Scanner(System.in);
        return scan.nextLine();
    }

    public PlayerController getController() {
        return new PlayerController();
    }

    public Hand getHand() {
        return new Hand();
    }

    public void receiveReward() {
        if(winGame()){
            balance += (currentBet*1.5);
        }
        else if(winGamewithBlackjack()){
            balance += (currentBet*2);
        }
        else{
            balance += 0;
        }
    }
}

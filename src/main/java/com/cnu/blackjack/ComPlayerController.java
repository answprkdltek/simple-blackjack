package com.cnu.blackjack;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ComPlayerController extends PlayerController{
    private int previousBet = 0;
    private int previousBalance = 0;

    public int placeBet(int balance, int minBet) {
        int Bet = martinGale(balance);
        return Bet > minBet ? Bet : minBet;
    }

    private int Kelly(int balance){
        Double edge = 0.1;
        Double result = balance*edge;
        return result.intValue();
    }

    private int martinGale(int balance){
        int Bet = 0;
        if(!amIWinAtThePreviousGame(balance)){
            Bet = previousBet *2;
        }else{
            Bet = previousBet;
        }
        previousBet = Bet;
        return Bet;
    }

    private Boolean amIWinAtThePreviousGame(int balance){
        if (previousBalance-previousBet + (previousBet*1.5)== balance ||
                previousBalance-previousBet + (previousBet * 2) ==balance){
            previousBalance = balance;
            return true;
        }else{
            previousBalance = balance;
            return false;
        }
    }

    public boolean wannaHit(Hand hand) {
        int currentScore = Evaluator.checkScore(hand);
        if(currentScore < 17){
            return true;
        }
        //return percentageOfBlackjack(currentScore) > 0.1 ? true : false;
        return false;
    }

    private double percentageOfBlackjack(int currentScore){
        List<Card> leftDeck = new ArrayList<Card>();
        for (int j = 0; j < 3; j++) {
            for (int i = 1; i <= 13; i++) {
                for (Suit suit: Suit.values()) {
                    Card card = new Card(i, suit);
                    if (!isUsedCard(card)){
                        leftDeck.add(card);
                    }
                }
            }
        }

        int neededScore = 21 - currentScore;
        int numberOfHasNeededScoreCards = 0;

        for (int i=0; i<leftDeck.size();i++){
            int getRank = leftDeck.get(i).getRank();
            if (getRank > 11){
                getRank = 10;
            }
            if (getRank == neededScore){
                numberOfHasNeededScoreCards++;
            }
        }

        return numberOfHasNeededScoreCards / leftDeck.size();
    }

    private boolean isUsedCard(Card card){
        List<Card> usedCardList = UsedCards.getUsedCardList();
        return usedCardList.contains(card);
    }
}

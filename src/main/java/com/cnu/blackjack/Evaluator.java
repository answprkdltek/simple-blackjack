package com.cnu.blackjack;

public class Evaluator {

    public static boolean checkBurst(Hand hand) {
        return checkScore(hand) > 21 ? true : false;
    }

    public static int checkScore(Hand hand) {
        int valueOfHand = 0;

        int numberOfAce = 0;

        for(int i=0; i<hand.getCurrentHandSize(); i++){
            int rank = hand.getCard(i).getRank();

            if(rank == 1){
                numberOfAce++;
            }else if(rank > 10){
                valueOfHand += 10;
            }else{
                valueOfHand += rank;
            }
        }

        while(numberOfAce != 0){
            if (valueOfHand < 11 && numberOfAce == 1){
                valueOfHand += 11;
            }else{
                valueOfHand += 1;
            }
            numberOfAce--;
        }

        return valueOfHand;
    }
}

package com.cnu.blackjack;

import java.util.ArrayList;
import java.util.List;

public class UsedCards {
    private static List<Card> usedCardList = new ArrayList<Card>();

    public static void addUsedCardList(Card usedCard){
        usedCardList.add(usedCard);
    }

    public static List<Card> getUsedCardList(){
        return usedCardList;
    }

    public static void vacateUsedCardList(){
        usedCardList.clear();
    }
}

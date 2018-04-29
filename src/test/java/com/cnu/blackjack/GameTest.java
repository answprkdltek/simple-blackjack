package com.cnu.blackjack;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class GameTest {

    @Test
    public void 배팅액을_입력할수있다(){


    }

    @Test
    public void 배팅이_불가능한_플레이어를_제외한_모든_플레이어가_배팅을하지않으면_게임을_시작할수없다(){

    }

    @Test
    public void 합21은_19를_이긴다(){

    }

    @Test
    public void 게임에_승리한_플레이어를_알아낼_수_있다(){
        Player player = new Player(1000);
        Player player2 = new Player(1000);

        List<Player> players;  //플레이어들
        Deck deck;
        Game game;

        Hand playerHand = player.getHand();
        playerHand.receiveCard(new Card(10,Suit.DIAMOND));
        playerHand.receiveCard(new Card(10,Suit.SPADE));
        Hand playerHand2 = player2.getHand();
        playerHand2.receiveCard(new Card(9,Suit.DIAMOND));
        playerHand2.receiveCard(new Card(9,Suit.CLUB));

        List<Player> winners = game.누가이김();   //이긴 플레이어들


        assertEquals(player.getBalance() == 1150);

    }

    @Test
    public void 게임에_승리한_플레이어는_배팅금액의_15배를_보상_받는다(){
        Player player = new Player(1000);
        Player player2 = new Player(1000);

        List<Player> players;  //플레이어들
        Deck deck;
        Game game;

        Hand playerHand = player.getHand();
        playerHand.receiveCard(new Card(10,Suit.DIAMOND));
        playerHand.receiveCard(new Card(10,Suit.SPADE));
        Hand playerHand2 = player2.getHand();
        playerHand2.receiveCard(new Card(9,Suit.DIAMOND));
        playerHand2.receiveCard(new Card(9,Suit.CLUB));

        player.placeBet(100);
        player2.placeBet(100);

        List<Player> winners = new ArrayList<>();   //이긴 플레이어들
        game.누가이김()



        assertEquals(player.getBalance() == 1150);

    }

    @Test
    public void 게임에_패배한_플레이어는_보상이없다(){

    }

    @Test
    public void 게임이종료되면_사용된카드를_정리한다(){

    }

}

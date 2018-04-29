package com.cnu.blackjack;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GameTest {

    @Test
    public void 모든_플레이어가_최소_배팅액을_가지면_플레이가능() {
        List<Player> players = new ArrayList<>();
        Deck deck = new Deck(3);
        players.add(new Player("AAA", 1000, ControllerType.COM));
        players.add(new Player("BBB", 9999, ControllerType.COM));
        players.add(new Player("CCC", 100, ControllerType.COM));
        Game game = new Game(players, deck, 100);

        assertTrue(game.isPlayable());
    }

    @Test
    public void 모든_플레이어가_최소_배팅액을_가지지못하면_플레이불가능() {
        List<Player> players = new ArrayList<>();
        Deck deck = new Deck(3);
        players.add(new Player("AAA", 1000, ControllerType.COM));
        players.add(new Player("BBB", 9999, ControllerType.COM));
        players.add(new Player("CCC", 100, ControllerType.COM));
        Game game = new Game(players, deck, 10000);

        assertFalse(game.isPlayable());
    }

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

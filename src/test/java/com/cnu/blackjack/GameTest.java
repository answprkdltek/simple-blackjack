package com.cnu.blackjack;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
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
    public void 총_4명의_플레이어_중_2명의_플레이어가_최소배팅액을_가지지_못하면_플레이_가능한_플레이어는_2명이다(){
        List<Player> players = new ArrayList<>();
        players.add(new Player("AAA",1000,ControllerType.COM));
        players.add(new Player("BBB",1000,ControllerType.COM));
        players.add(new Player("CCC",100,ControllerType.COM));
        players.add(new Player("DDD",200,ControllerType.COM));

        assertEquals(2, Game.getPlayablePlayers(players,500).size());
    }

    @Test
    public void 플레이어가_1000원을_배팅했다면_betTable의_해당_플레이어의_배팅액은_1000원이다(){
        List<Player> players = new ArrayList<>();
        Player player = new Player("SSS",1000,ControllerType.COM);
        players.add(player);
        players.add(new Player("AAA",1000,ControllerType.COM));
        players.add(new Player("BBB",1000,ControllerType.COM));
        players.add(new Player("CCC",100,ControllerType.COM));
        players.add(new Player("DDD",200,ControllerType.COM));

        Map<Player,Integer> bettings = Game.betTable(players,1000);
        int bet = bettings.get(player);
        assertEquals(1000, bet);
    }

    @Test
    public void 딜러의_점수가_19일_때_점수가_21인_플레이어가_이긴다(){
        List<Player> players = new ArrayList<>();
        Player player = new Player("winner",1000,ControllerType.COM);
        player.getHand().receiveCard(new Card(1,Suit.SPADE));
        player.getHand().receiveCard(new Card(13,Suit.CLUB));
        players.add(player);

        Dealer dealer = new Dealer();
        dealer.getHand().receiveCard(new Card(12,Suit.HEART));
        dealer.getHand().receiveCard(new Card(9,Suit.HEART));

        assertEquals(player,Game.getWinners(dealer,players).get(0));
    }

    @Test
    public void 게임에_승리한_플레이어들의_수를_알아낼_수_있다(){
        List<Player> players = new ArrayList<>();
        Player player1 = new Player("winner",1000,ControllerType.COM);
        player1.getHand().receiveCard(new Card(1,Suit.SPADE));
        player1.getHand().receiveCard(new Card(13,Suit.CLUB));
        players.add(player1);

        Player player2 = new Player("winner",1000,ControllerType.COM);
        player2.getHand().receiveCard(new Card(1,Suit.SPADE));
        player2.getHand().receiveCard(new Card(13,Suit.CLUB));
        players.add(player2);

        Player player3 = new Player("loser",1000,ControllerType.COM);
        player3.getHand().receiveCard(new Card(2,Suit.SPADE));
        player3.getHand().receiveCard(new Card(13,Suit.CLUB));
        players.add(player3);

        Dealer dealer = new Dealer();
        dealer.getHand().receiveCard(new Card(12,Suit.HEART));
        dealer.getHand().receiveCard(new Card(9,Suit.HEART));

        assertEquals(2,Game.getWinners(dealer,players).size());
    }

    @Test
    public void 게임에_승리한_플레이어는_배팅금액의_150퍼센트를_보상_받는다(){
        List<Player> players = new ArrayList<>();
        Player player = new Player("winner",1000,ControllerType.COM);
        player.getHand().receiveCard(new Card(10,Suit.SPADE));
        player.getHand().receiveCard(new Card(13,Suit.CLUB));
        players.add(player);

        Dealer dealer = new Dealer();
        dealer.getHand().receiveCard(new Card(9,Suit.HEART));
        dealer.getHand().receiveCard(new Card(9,Suit.HEART));

        Map<Player,Integer> bettings = Game.betTable(players,1000);
        List<Player> Winners = Game.getWinners(dealer,players);

        Game.giveRewards(Winners,bettings);

        assertEquals(1500,player.getBalance());
    }

    @Test
    public void 게임에_패배한_플레이어는_보상이없다(){
        List<Player> players = new ArrayList<>();
        Player player = new Player("loser",1000,ControllerType.COM);
        player.getHand().receiveCard(new Card(2,Suit.SPADE));
        player.getHand().receiveCard(new Card(13,Suit.CLUB));
        players.add(player);

        Dealer dealer = new Dealer();
        dealer.getHand().receiveCard(new Card(12,Suit.HEART));
        dealer.getHand().receiveCard(new Card(9,Suit.HEART));

        Map<Player,Integer> bettings = Game.betTable(players,1000);
        List<Player> Winners = Game.getWinners(dealer,players);

        Game.giveRewards(Winners,bettings);

        assertEquals(0,player.getBalance());
    }

    @Test
    public void 게임이종료되면_사용된카드를_정리한다(){
        List<Player> players = new ArrayList<>();
        Player player = new Player("loser",1000,ControllerType.COM);
        player.getHand().receiveCard(new Card(2,Suit.SPADE));
        player.getHand().receiveCard(new Card(13,Suit.CLUB));
        players.add(player);

        Dealer dealer = new Dealer();
        dealer.getHand().receiveCard(new Card(12,Suit.HEART));
        dealer.getHand().receiveCard(new Card(9,Suit.HEART));

        Map<Player,Integer> bettings = Game.betTable(players,1000);
        List<Player> Winners = Game.getWinners(dealer,players);

        Game.giveRewards(Winners,bettings);

        Game.clearTable(dealer,players);

        assertEquals(0,player.getHand().getCurrentHandSize());
    }
}
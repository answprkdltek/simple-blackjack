package com.cnu.blackjack;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlayerTest {

    @Test
    public void 플레이어는_이름과_시드머니를_가지고_생성할수있다() {
        Player player = new Player("BlackJackKing", 5000);

        String playerName = player.getPlayerName();
        assertEquals(playerName, "BlackJackKing");

        int balance = player.getBalance();
        assertEquals(balance, 5000);
    }

    @Test
    public void 플레이어는_배팅을_할수있다() {
        Player player = new Player("BlackJackKing", 5000);
        player.placeBet(3000);
        int balance = player.getBalance();
        assertEquals(balance, 2000);
    }

    @Test(expected = NotEnoughBalanceException.class)
    public void 플레이어는_가진돈보다_많이_배팅할수없다() {
        Player player = new Player("BlackJackKing", 5000);
        player.placeBet(10000);
    }

    public void 플레이어는_상금을_받을_수_있다() {
        Player player = new Player("BlackJackKing", 5000);
        player.receiveReward(3000);
        int balance = player.getBalance();
        assertEquals(balance, 8000);
    }
}

package com.cnu.blackjack;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PlayerTest {

    @Test
    public void 플레이어는_이름과_시드머니를_가지고_생성할수있다() {
        Player player = new Player(5000);

        String playerName = player.getPlayerName();
        assertEquals(playerName, "BlackJackKing");

        int balance = player.getBalance();
        assertEquals(balance, 5000);
    }

    @Test
    public void 플레이어는_기본적으로_Com컨트롤러를_가진다() {
        Player player = new Player(5000);

        PlayerController controller = player.getController();
        assertEquals(controller.getClass(), ComPlayerController.class);
    }

    @Test
    public void 플레이어는_컨트롤러를_지정해서_생성할_수_있다() {
        Player player = new Player(5000, new UserPlayerController());

        PlayerController controller = player.getController();
        assertEquals(controller.getClass(), UserPlayerController.class);
    }

    @Test
    public void 플레이어의_컨트롤러가_null이라면_Com컨트롤러를_가진다() {
        Player player = new Player(5000, null);

        PlayerController controller = player.getController();
        assertEquals(controller.getClass(), ComPlayerController.class);
    }

    @Test
    public void 플레이어는_배팅을_할수있다() {
        Player player = new Player(5000);
        player.placeBet(3000);
        int balance = player.getBalance();
        assertEquals(balance, 2000);
    }

    @Test(expected = NotEnoughBalanceException.class)
    public void 플레이어는_가진돈보다_많이_배팅할수없다() {
        Player player = new Player(5000);
        player.placeBet(10000);
    }

    @Test
    public void 플레이어는_상금을_받을_수_있다() {
        Player player = new Player(5000);
        player.receiveReward();
        int balance = player.getBalance();
        assertEquals(balance, 8000);
    }

    @Test
    public void 플레이어는_핸드를_가진다() {
        Player player = new Player(5000);
        Hand playerHand = player.getHand();
        assertNotNull(playerHand);
    }
}

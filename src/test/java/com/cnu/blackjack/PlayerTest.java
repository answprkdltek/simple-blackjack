package com.cnu.blackjack;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class PlayerTest {

    @Test
    public void 플레이어는_이름과_시드머니_컨트롤러타입을_가지고_생성할수있다() {
        Player player = new Player("blackjack", 4000, ControllerType.USER);

        String playerName = player.getPlayerName();
        assertEquals(playerName, "blackjack");

        int balance = player.getBalance();
        assertEquals(4000, balance);
    }

    @Test
    public void 플레이어는_컨트롤러를_지정해서_생성할_수_있다() {
        Player player = new Player("blackjack", 4000, ControllerType.USER);
    }

    @Test
    public void 플레이어는_상금을_받을_수_있다() {
        Player player = new Player("blackjack", 4000, ControllerType.USER);
        player.receiveReward(10000);
        int balance = player.getBalance();
        assertEquals(14000, balance);
    }

    @Test
    public void 플레이어는_핸드를_가진다() {
        Player player = new Player("blackjack", 4000, ControllerType.USER);
        Hand playerHand = player.getHand();
        assertNotNull(playerHand);
    }

    @Test
    public void 플레이어의_핸드에_카드를_추가할수있다(){
        Player player = new Player("blackjack", 4000, ControllerType.USER);

        Card card1 = new Card(1, Suit.DIAMOND);
        Card card2 = new Card(13, Suit.DIAMOND);

        player.addCardToHand(card1);
        player.addCardToHand(card2);

        assertTrue(player.getHand().getCurrentHandSize() == 2);
    }

    @Test
    public void 플레이어의_소지금이_판돈보다_적으면_게임에_참여할수없다(){
        Player player = new Player("blackjack", 4000, ControllerType.USER);

        assertFalse(player.ableToParticipateIn(10000));
    }
}

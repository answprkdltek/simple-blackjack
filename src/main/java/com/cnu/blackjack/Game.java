package com.cnu.blackjack;

import java.util.List;

public class Game {
    private List<Player> players;
    private Deck deck;
    private int minBetAmount;

    public Game(List<Player> players, Deck deck, int minBetAmount) {
        this.players = players;
        this.deck = deck;
        this.minBetAmount = minBetAmount;
    }

    public boolean isPlayable() {
        for (Player player : this.players) {
            if (player.ableToParticipateIn(this.minBetAmount)) {
                return true;
            }
        }
        return false;
    }

    public void run() {

    }
}

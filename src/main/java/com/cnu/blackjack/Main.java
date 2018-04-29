package com.cnu.blackjack;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final int MIN_NUMBER_OF_PLAYERS = 1;
    private static final int MAX_NUMBER_OF_PLAYERS = 4;

    public static void main(String[] args) {
        AppIO.out_message("< < < BlackJack을 시작합니다 > > >");

        AppIO.out_message("플레이어들의 소지금과 기본 배팅액을 입력하시오.");
        int minBalance = AppIO.in_playerBalance();
        int minBetAmount = AppIO.in_minBetAmount();

        int nPlayers = AppIO.in_numberOfPlayers(MIN_NUMBER_OF_PLAYERS, MAX_NUMBER_OF_PLAYERS);
        List<Player> players = createPlayers(nPlayers, minBalance);

        int nPacks = 3;
        Deck deck = new Deck(nPacks);

        printPlayerState(players);

        Game game = new Game(players, deck, minBetAmount);
        while (game.isPlayable()) {
            game.run();
            printPlayerState(players);
        }
    }

    private static List<Player> createPlayers(int nPlayers, int initBalance) {
        List<Player> players = new ArrayList<>();
        for (int i = 1; i <= nPlayers; i++) {
            Player player = createPlayer(initBalance);
            players.add(player);
        }
        return players;
    }

    private static Player createPlayer(int initBalance) {
        String name = AppIO.in_playerName();
        boolean isUser = AppIO.in_isUser();

        if (isUser) {
            return new Player(name, initBalance, ControllerType.USER);
        }
        else {
            return new Player(name, initBalance, ControllerType.COM);
        }
    }

    private static void printPlayerState(List<Player> players) {
        AppIO.out_message("[플레이어 현황]");
        for (Player player : players) {
            String playerName = player.getPlayerName();
            int playerBalance = player.getBalance();
            AppIO.out_message(String.format("%10s: %10d원", playerName, playerBalance));
        }
    }
}

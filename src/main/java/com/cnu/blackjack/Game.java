package com.cnu.blackjack;

import sun.java2d.pipe.AlphaPaintPipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        List<Player> playablePlayers = getPlayablePlayers(this.players, this.minBetAmount);
        Map<Player, Integer> bettings = betTable(playablePlayers, this.minBetAmount);

        Dealer dealer = new Dealer();
        startGame(dealer, playablePlayers, this.deck);

        List<Player> winners = getWinners(dealer, playablePlayers);
        giveRewards(winners, bettings);

        clearTable(dealer, playablePlayers);
    }

    public static void startGame(Dealer dealer, List<Player> players, Deck deck) {
        giveCardToEveryPlayers(dealer, players, deck);
        giveCardToEveryPlayers(dealer, players, deck);
        dealer.getHand().closeCard(0);

        AppIO.out_message("플레이어들은 Hit 여부를 결정합니다.");
        hitPlayers(dealer, players, deck);

        AppIO.out_message("딜러는 Hit 여부를 결정합니다.");
        hitDealer(dealer, players, deck);
        AppIO.out_message("딜러의 Hit이 끝났습니다.");

        AppIO.out_message("딜러는 카드를 공개합니다.");
        dealer.getHand().openCard(0);
        printTable(dealer, players);
    }

    private static void giveCardToEveryPlayers(Dealer dealer, List<Player> players, Deck deck) {
        giveCard(dealer.getHand(), deck);
        for (Player player : players) {
            giveCard(player.getHand(), deck);
        }
    }

    private static void giveCard(Hand hand, Deck deck) {
        if (deck.getTotalCardCount() == 0) {
            deck.reset();

            UsedCards.vacateUsedCardList();
        }

        Card card = deck.drawCard();
        hand.receiveCard(card);

        UsedCards.addUsedCardList(card);

        if (deck.getTotalCardCount() == 0) {
            deck.reset();

            UsedCards.vacateUsedCardList();
        }
    }

    private static void hitPlayers(Dealer dealer, List<Player> players, Deck deck) {
        List<Player> hittingPlayer = new ArrayList<>(players);
        while (hittingPlayer.size() > 0) {
            List<Player> nextHittingPlayer = new ArrayList<>(hittingPlayer);
            printTable(dealer, players);

            for (Player player : hittingPlayer) {
                AppIO.out_message(player.getPlayerName() + "의 차례:");
                if (!player.wannaHit()) {
                    nextHittingPlayer.remove(player);
                    continue;
                }

                Hand playerHand = player.getHand();
                giveCard(playerHand, deck);
                if (Evaluator.checkBurst(playerHand)) {
                    AppIO.out_message(player.getPlayerName() + "버스트!");
                    nextHittingPlayer.remove(player);
                }
            }

            hittingPlayer = nextHittingPlayer;
        }
    }

    private static void hitDealer(Dealer dealer, List<Player> players, Deck deck) {
        Hand dealerHand = dealer.getHand();
        int dealerScore = Evaluator.checkScore(dealerHand);
        while (dealerScore < 17) {
            giveCard(dealerHand, deck);
            dealerScore = Evaluator.checkScore(dealerHand);
            printTable(dealer, players);
        }
    }

    public static List<Player> getWinners(Dealer dealer, List<Player> players) {
        List<Player> winners = new ArrayList<>();
        for (Player player : players) {
            if (isPlayerWin(player, dealer)) {
                winners.add(player);
            }
        }

        return winners;
    }

    private static boolean isPlayerWin(Player player, Dealer dealer) {
        Hand playerHand = player.getHand();
        Hand dealerHand = dealer.getHand();
        int playerScore = Evaluator.checkScore(playerHand);
        int dealerScore = Evaluator.checkScore(dealerHand);

        if(Evaluator.checkBurst(playerHand)){
            return false;
        }
        if(Evaluator.checkBurst(dealerHand)){
            return true;
        }

        return (playerScore > dealerScore);
    }

    public static void giveRewards(List<Player> winners, Map<Player,Integer> bettings) {
        for(Player player : winners){
            boolean isBlackjack = isBlackjackPlayer(player);
            double weight = (isBlackjack ? 2.0 : 1.5);
            int betting = bettings.get(player);
            int reward = (int)(betting * weight);
            player.receiveReward(reward);
        }
    }

    private static boolean isBlackjackPlayer(Player player) {
        Hand playerHand = player.getHand();
        return (Evaluator.checkScore(playerHand) == 21 && playerHand.getCurrentHandSize() == 2);
    }

    public static List<Player> getPlayablePlayers(List<Player> players, int minBetAmount) {
        List<Player> playablePlayers = new ArrayList<>();
        for (Player player : players) {
            if (!player.ableToParticipateIn(minBetAmount)) {
                continue;
            }

            playablePlayers.add(player);
        }
        return playablePlayers;
    }

    public static Map<Player, Integer> betTable(List<Player> players, int minBetAmount) {
        Map<Player, Integer> bettings = new HashMap<>();
        for(Player player : players){
            AppIO.out_message(player.getPlayerName() + "의 차례:");

            int betAmount = player.placeBet(minBetAmount);
            bettings.put(player, betAmount);
        }
        return bettings;
    }

    public static void clearTable(Dealer dealer, List<Player> playablePlayers) {
        dealer.getHand().clearHand();
        for(Player player : playablePlayers){
            player.getHand().clearHand();
        }
    }

    public static void printTable(Dealer dealer, List<Player> players) {
        AppIO.out_message("");
        AppIO.out_message("=== 테이블 현황 ===");
        printHand("딜러", dealer.getHand());
        for (Player player : players) {
            printHand(player.getPlayerName(), player.getHand());
        }
        AppIO.out_message("");
    }

    private static void printHand(String name, Hand hand) {
        String str = String.format("%10s: ", name);
        for (int i = 0; i < hand.getCurrentHandSize(); i++) {
            str += getCardString(hand.getCard(i), hand.isOpened(i)) + " ";
        }

        AppIO.out_message(str);
    }

    private static String getCardString(Card card, boolean opened) {
        if (!opened) {
            return "??";
        }

        String suit = "?";
        switch (card.getSuit()) {
            case SPADE:
                suit = "♠";
                break;

            case CLUB:
                suit = "♣";
                break;

            case DIAMOND:
                suit = "◇";
                break;

            case HEART:
                suit = "♡";
                break;
        }

        String rank = "?";
        switch (card.getRank()) {
            case 1:
                rank = "A";
                break;

            case 11:
                rank = "J";
                break;

            case 12:
                rank = "Q";
                break;

            case 13:
                rank = "K";
                break;

            default:
                rank = String.valueOf(card.getRank());
                break;
        }

        return suit + rank;
    }
}

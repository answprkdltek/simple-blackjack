package com.cnu.blackjack;

import java.io.*;
import java.text.ParseException;
import java.util.Scanner;

public class AppIO {

    private static Scanner scan = new Scanner(System.in);
    private static PrintStream out = System.out;

    public static void setIn(InputStream inputStream) {
        scan.close();
        scan = new Scanner(inputStream);
    }

    public static void setOut(PrintStream printStream) {
        out = printStream;
    }

    public static void out_message(String message) {
        out.println(message);
    }

    public static void out_errorMessage(String message) {
        out.println("[오류] " + message);
    }

    private static String in_line(String message) {
        out.print(message);

        String answer = scan.nextLine();
        return answer;
    }

    private static int in_int(String message, int min, int max) throws NumberFormatException, NumberOutOfBoundsException {
        String answer = AppIO.in_line(message);
        int value = Integer.parseInt(answer);

        if (value < min) {
            throw new NumberOutOfBoundsException();
        }
        if (value > max) {
            throw new NumberOutOfBoundsException();
        }
        return value;
    }

    private static boolean in_confirm(String message) throws YesOrNoException {
        String answer = AppIO.in_line(message);

        if (answer.equals("y")) {
            return true;
        }
        else if (answer.equals("n")) {
            return false;
        }
        else {
            throw new YesOrNoException();
        }
    }

    public static boolean in_wannaHit() {
        while (true) {
            try {
                return AppIO.in_confirm("Hit 하시겠습니까? [y/n]: ");
            }
            catch (YesOrNoException e) {
                AppIO.out_errorMessage("잘못된 입력입니다.");
            }
        }
    }

    public static boolean in_isUser() {
        while (true) {
            try {
                return AppIO.in_confirm("사용자 입니까? [y:사용자 / n:컴퓨터]: ");
            }
            catch (YesOrNoException e) {
                AppIO.out_errorMessage("잘못된 입력입니다.");
            }
        }
    }

    public static int in_playerBalance() {
        while (true) {
            try {
                int minBalance = 0;
                int maxBalance = Integer.MAX_VALUE;
                int balance = AppIO.in_int("플레이어의 소지금을 입력하십시오: ", minBalance, maxBalance);
                return balance;
            }
            catch (NumberFormatException e) {
                AppIO.out_errorMessage("잘못된 입력입니다.");
            }
            catch (NumberOutOfBoundsException e) {
                AppIO.out_errorMessage("소지금은 0보다 작을 수 없습니다.");
            }
        }
    }

    public static int in_minBetAmount() {
        while (true) {
            try {
                int minValue = 0;
                int maxValue = Integer.MAX_VALUE;
                int minBetAmount = AppIO.in_int("최소 배팅액을 입력하십시오: ", minValue, maxValue);
                return minBetAmount;
            }
            catch (NumberFormatException e) {
                AppIO.out_errorMessage("잘못된 입력입니다.");
            }
            catch (NumberOutOfBoundsException e) {
                AppIO.out_errorMessage("최소배팅액은 0보다 작을 수 없습니다.");
            }
        }
    }

    public static int in_betAmount(int minBetAmount, int maxBetAmount) {
        while (true) {
            try {
                int betAmount = AppIO.in_int("배팅액을 입력하십시오: ", minBetAmount, maxBetAmount);
                return betAmount;
            }
            catch (NumberFormatException e) {
                AppIO.out_errorMessage("잘못된 입력입니다.");
            }
            catch (NumberOutOfBoundsException e) {
                AppIO.out_errorMessage(String.format("배팅액은 %d~%d여야 합니다.", minBetAmount, maxBetAmount));
            }
        }
    }

    public static int in_numberOfPlayers(int minNumberOfPlayers, int maxNumberOfPlayers) {
        while (true) {
            try {
                int betAmount = AppIO.in_int("플레이어 수를 입력하십시오: ", minNumberOfPlayers, maxNumberOfPlayers);
                return betAmount;
            }
            catch (NumberFormatException e) {
                AppIO.out_errorMessage("잘못된 입력입니다.");
            }
            catch (NumberOutOfBoundsException e) {
                AppIO.out_errorMessage(String.format("플레이어 수는 %d~%d여야 합니다.", minNumberOfPlayers, maxNumberOfPlayers));
            }
        }
    }
}

package com.cnu.blackjack;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public class AppIO {

    public static void out_message(String message) {
        System.out.println(message);
    }

    public static void out_errorMessage(String message) {
        System.out.println("[오류] " + message);
    }

    private static String in_line(String message) {
        System.out.print(message);

        Scanner scan = new Scanner(System.in);
        String answer = scan.nextLine();
        scan.close();

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
}

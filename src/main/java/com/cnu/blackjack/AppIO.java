package com.cnu.blackjack;

import java.util.Scanner;

public class AppIO {
    public static boolean AppIO_in_yesOrNo(String answer) {
        System.out.println("yes or no : ");
        if(answer.equals("yes")) {
            return true;
        }else if (answer.equals("no")){
            return false;
        }else{
            throw new YesOrNoException();
        }
    }

    public static String AppIO_in_String(){
        return new Scanner(System.in).next();
    }

    public static int AppIO_in_numberOfPlayers(int numberOfPlayers) {
        System.out.println("플레이어 수를 입력하시오 : ");
        if(numberOfPlayers < 7 && numberOfPlayers >0){
            return numberOfPlayers;
        }else{
            throw new NumberOfPlayersException();
        }
    }

    public static int AppIO_in_Integer(){
        return new Scanner(System.in).nextInt();
    }
}

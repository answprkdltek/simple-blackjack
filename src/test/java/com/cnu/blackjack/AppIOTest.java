package com.cnu.blackjack;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AppIOTest {

    private static final String NEW_LINE = System.getProperty("line.separator");

    private void setTestInput(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    private ByteArrayOutputStream getTestOutputStream() {
        ByteArrayOutputStream testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
        return testOut;
    }

    @Test
    public void 안내메시지를_보여줄_수_있다() {
        String OUTPUT = "Hello Blackjack" + NEW_LINE;
        ByteArrayOutputStream out = getTestOutputStream();

        AppIO.out_message("Hello Blackjack");
        String output = out.toString();

        assertEquals(OUTPUT, output);
    }

    @Test
    public void wannaHit을_y로_답하면_true를_반환한다() {
        String INPUT = "y";
        setTestInput(INPUT);

        boolean wannaHit = AppIO.in_wannaHit();

        assertTrue(wannaHit);
    }

    @Test
    public void wannaHit을_n으로_답하면_false를_반환한다() {
        String INPUT = "n";
        setTestInput(INPUT);

        boolean wannaHit = AppIO.in_wannaHit();

        assertFalse(wannaHit);
    }

    @Test
    public void wannaHit은_y또는n으로_응답_받을때_까지_물어본다() {
        String INPUT
                = "I" + NEW_LINE
                + "don't" + NEW_LINE
                + "know" + NEW_LINE
                + "y" + NEW_LINE;
        setTestInput(INPUT);

        boolean wannaHit = AppIO.in_wannaHit();
        assertTrue(wannaHit);
    }

    @Test
    public void isUser을_y로_답하면_true를_반환한다() {
        String INPUT = "y";
        setTestInput(INPUT);

        boolean isUser = AppIO.in_isUser();

        assertTrue(isUser);
    }

    @Test
    public void isUser을_n으로_답하면_false를_반환한다() {
        String INPUT = "n";
        setTestInput(INPUT);

        boolean isUser = AppIO.in_isUser();

        assertFalse(isUser);
    }

    @Test
    public void isUser은_y또는n으로_응답_받을때_까지_물어본다() {
        String INPUT
                = "I" + NEW_LINE
                + "don't" + NEW_LINE
                + "know" + NEW_LINE
                + "n" + NEW_LINE;
        setTestInput(INPUT);

        boolean isUser = AppIO.in_isUser();
        assertFalse(isUser);
    }

    @Test
    public void 플레이어들의_소지금은_정수로_입력받는다() {
        String INPUT
                = "백만원" + NEW_LINE
                + "1000000" + NEW_LINE;
        setTestInput(INPUT);

        int balance = AppIO.in_playerBalance();
        assertEquals(1000000, balance);
    }

    @Test
    public void 기본배팅액은_정수로_입력받는다() {
        String INPUT ="1000";
        setTestInput(INPUT);

        int amount = AppIO.in_minBetAmount();
        assertEquals(1000, amount);
    }

    @Test
    public void 배팅액은_정수로_입력받는다() {
        String INPUT ="1000";
        setTestInput(INPUT);

        int amount = AppIO.in_betAmount(1000, 3000);
        assertEquals(1000, amount);
    }

    @Test
    public void 배팅액은_최소배팅액과_최대배팅액_사이로_입력받는다() {
        String INPUT
                = "999" + NEW_LINE
                + "3001" + NEW_LINE
                + "3000" + NEW_LINE;
        setTestInput(INPUT);

        int amount = AppIO.in_betAmount(1000, 3000);
        assertEquals(3000, amount);
    }

    @Test
    public void 플레이어수는_1명이상_6명이하로_입력받는다() {
        String INPUT
                = "0" + NEW_LINE
                + "7" + NEW_LINE
                + "6" + NEW_LINE;
        setTestInput(INPUT);

        int nPlayers = AppIO.in_numberOfPlayers(1, 6);
        assertEquals(6, nPlayers);
    }
}
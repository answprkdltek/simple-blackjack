package com.cnu.blackjack;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

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

        AppIO.printMessage("Hello Blackjack");
        String output = out.toString();

        Assert.assertEquals(OUTPUT, output);
    }

    @Test
    public void Confirm은_y로_응답하면_true를_반환한다() {
        String INPUT = "y";
        setTestInput(INPUT);

        boolean wannaHit = AppIO.confirm("Hit 하시겠습니까?");

        Assert.assertTrue(wannaHit);
    }

    @Test
    public void Confirm은_n으로_응답하면_true를_반환한다() {
        String INPUT = "n";
        setTestInput(INPUT);

        boolean wannaHit = AppIO.confirm("Hit 하시겠습니까?");

        Assert.assertFalse(wannaHit);
    }

    @Test(expected = YesOrNoException.class)
    public void Confirm은_y또는n으로_응답해야한다() {
        String INPUT = "I_don't_know";
        setTestInput(INPUT);

        boolean wannaHit = AppIO.confirm("Hit 하시겠습니까?");
    }

    @Test(expected = NumberOfPlayersException.class)
    public void 플레이어_수를_입력받을_때_최대_6명까지만_입력가능하다(){
        AppIO.AppIO_in_numberOfPlayers(111);
    }
}
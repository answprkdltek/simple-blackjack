package com.cnu.blackjack;

import org.junit.Test;

import static org.junit.Assert.*;

public class AppIOTest {

    @Test(expected = yesOrNoException.class)
    public void 물음에_대한_입력은_yes아니면_no다(){
        AppIO.AppIO_in_yesOrNo("yesa");
    }

    @Test(expected = NumberOfPlayersException.class)
    public void 플레이어_수를_입력받을_때_최대_6명까지만_입력가능하다(){
        AppIO.AppIO_in_numberOfPlayers(111);
    }
}
package com.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class Junit5Test {

    @Test
    @DisplayName("My First Junit5 Test method")
    public void firstTest(){
        Assertions.assertTrue(Boolean.TRUE);

    }

    @Test
    @Tag("development1")
    @DisplayName("I expect this method to not run")
    public void SecondTest(){
        Assertions.assertTrue(Boolean.FALSE);

    }

    @Test
    @Tag("development2")
    @DisplayName("I expect this method to not run")
    public void ThirdTest(){
        Assertions.assertTrue(Boolean.FALSE);

    }
}

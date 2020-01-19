package ru.netology.pageobjects.data;

import lombok.Value;

public class DataHelper {
    private DataHelper() {}

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getCorrectAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getWrongAuthInfoNotValidLogin () {
        return new AuthInfo("petya", "qwerty123");
    }
    public static AuthInfo getWrongAuthInfoNotValidPassword () {
        return new AuthInfo("vasya", "123qwerty");
    }

    //для получения кода из смс
    @Value
    public static class VerificationCode {
        private String code;
    }

    //метод класса DataHelper, в котором мы возвращаем объект с кодом
    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }

    public static VerificationCode getWrongVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("23456");
    }

    @Value
    public static class AmountOfMoney {
        String amountOfMoney;
    }
    public static AmountOfMoney oneHundredRubles() {
        return new AmountOfMoney("100");
    }
    public static AmountOfMoney twentyThousandsRubles() {
        return new AmountOfMoney("20000");
    }
    public static AmountOfMoney emptyAmountOfMoney() {
        return new AmountOfMoney(" ");
    }

    @Value
    public static class NumberOfCard {
        String numberOfCard;
    }
    public static NumberOfCard cardWithOne() { return new NumberOfCard("5559 0000 0000 0001"); }
    public static NumberOfCard cardWithTwo() {
        return new NumberOfCard("5559 0000 0000 0002");
    }
    public static NumberOfCard cardWithNotFullNumber() { return new NumberOfCard("5559 000"); }
    public static NumberOfCard cardDontExist() { return new NumberOfCard("5559 0000 0000 0003"); }
    public static NumberOfCard emptyNumberOfCard() { return new NumberOfCard(" "); }
}



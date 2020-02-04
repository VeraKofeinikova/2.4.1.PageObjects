package ru.netology.pageobjects.data;

import lombok.Value;

public class DataHelper {
    private DataHelper() {}

    public static String extractingBalance(String string) {
        return string.substring(string.indexOf(":") + 1, string.indexOf("р")).trim();
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    @Value
    public static class AmountOfMoney {
        String amountOfMoney;
    }

    @Value
    public static class NumberOfCard {
        String numberOfCard;
    }

    public static AuthInfo getCorrectAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static VerificationCode getVerificationCodeFor() {
        return new VerificationCode("12345");
    }

    public static AmountOfMoney oneHundredRubles() {
        return new AmountOfMoney("100");
    }

    public static AmountOfMoney twentyThousandsRubles() {
        return new AmountOfMoney("20000");
    }

    public static NumberOfCard cardWithOne() { return new NumberOfCard("5559 0000 0000 0001"); }

    public static NumberOfCard cardWithTwo() {
        return new NumberOfCard("5559 0000 0000 0002");
    }
}





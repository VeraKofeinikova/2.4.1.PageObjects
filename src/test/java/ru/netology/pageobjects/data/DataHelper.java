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
}

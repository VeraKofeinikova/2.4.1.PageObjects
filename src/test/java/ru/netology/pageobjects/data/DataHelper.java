package ru.netology.pageobjects.data;

import lombok.Value;

public class DataHelper {
    private DataHelper() {}

//если данных немного – можно сделать несколько классов внутри одного.
// @Value – это аннотация класса Lombok, которая создает конструктор,
// геттеры и сеттеры – ее используют для компактности кода

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    //вот этот метод создает объект, который описывается
    // в классе выше. Объект будет передаваться в тесты
    public static AuthInfo getAuthInfo() {

        return new AuthInfo("vasya", "qwerty123");
    }


    //это метод для получения других данных
    public static AuthInfo getOtherAuthInfo(AuthInfo original) {

        return new AuthInfo("petya", "123qwerty");
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
}

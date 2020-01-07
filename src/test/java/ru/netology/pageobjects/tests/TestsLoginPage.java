package ru.netology.pageobjects.tests;

import static com.codeborne.selenide.Selenide.open;

import lombok.val;
import org.junit.jupiter.api.Test;
import ru.netology.pageobjects.data.DataHelper;
import ru.netology.pageobjects.page.LoginPageV1;
import ru.netology.pageobjects.page.LoginPageV2;
import ru.netology.pageobjects.page.LoginPageV3;

public class TestsLoginPage {
    @Test
    void shouldTransferMoneyBetweenOwnCardsV1() {
        //селенидовский метод, который нам открывает страницу
        open("http://localhost:9999");

        // браузер открылся и мы попали на первую страницу -
        // и мы создаем объект этой новой первой страницы
        val loginPage = new LoginPageV1();
        // строки 16+19 можно заменить на
        // val loginPage = open("http://localhost:9999", LoginPageV1.class);


        //создаем объект, где хранятся данные об авторизации
        // и присваиваем его в переменную
        val authInfo = DataHelper.getAuthInfo();


        //вызываем метод validLogin из класса LoginPageV1 (
        // ввод значений в текстовые поля и нажатие кнопки)
        // и записываем в новую переменную вторую страницу,
        // которая получается после вызова метода validLogin

        //мы проверяем наличие поля на 2й странице
        val verificationPage = loginPage.validLogin(authInfo);

        //тут же проверим доступность кнопки и тогда продолжаем действия
        verificationPage.assertVerifyBtnAvailable();

        // получаем мастер-пароль и записываем в переменную
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);

        //проверка пароля из смс
        // dashboardPage - это уже третья страница
        val dashboardPage = verificationPage.validVerify(verificationCode);

        //дожидаемся чтобы страница личного кабинета прогрузилась
        //метод  waitUntilPageisLoaded из класса DashboardPage

        //закрыта эта проверка и метод в классе, потому что
        //dashboardPage.waitUntilPageisLoaded();
    }
    @Test
    void shouldTransferMoneyBetweenOwnCardsV2() {
        open("http://localhost:9999");
        val loginPage = new LoginPageV2();
        // можно заменить на
        // val loginPage = open("http://localhost:9999", LoginPageV2.class);
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
    }
    @Test
    void shouldTransferMoneyBetweenOwnCardsV3() {
        val loginPage = open("http://localhost:9999", LoginPageV3.class);
        // но здесь обратное не сработает — FindBy только с PageFactory
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
    }
}

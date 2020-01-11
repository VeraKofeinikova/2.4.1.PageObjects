package ru.netology.pageobjects.tests;

import static com.codeborne.selenide.Selenide.open;

import com.codeborne.selenide.Selenide;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.pageobjects.data.DataHelper;
import ru.netology.pageobjects.page.LoginPage;

public class TestsLoginPage {

    @BeforeEach
    public void clearCookies() {
        open("http://localhost:9999");
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
    }

    @Test
    @DisplayName("Успешный вход. Правильные логин-пароль-верификейшн код")
    void LoginSuccessful() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getCorrectAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        verificationPage.assertVerifyBtnAvailable();
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
    }

    @Test
    @DisplayName("Невозможно войти. Неправильный логин")
    void LoginFailedWrongLogin() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val notValidLogin = DataHelper.getWrongAuthInfoNotValidLogin();
        val loginPageWithError = loginPage.notValidLogin(notValidLogin);
    }

    @Test
    @DisplayName("Невозможно войти. Неправильный пароль")
    void LoginFailedWrongPassword() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val notValidPassword = DataHelper.getWrongAuthInfoNotValidPassword();
        val loginPageWithError = loginPage.notValidLogin(notValidPassword);
    }

    @Test
    @DisplayName("Невозможно войти. Правильные логин-пароль. Неправильный смс-код")
    void LoginFailedWrongVerificationCode() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getCorrectAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        verificationPage.assertVerifyBtnAvailable();
        val verificationCode = DataHelper.getWrongVerificationCodeFor(authInfo);
        verificationPage.canNotVerifyWrongCode(verificationCode);
    }

    @Test
    @DisplayName("Невозможно войти. Правильные логин-пароль. Превышение количества попыток введения неправильного кода")
    void LoginFailedWrongVerificationCodeTooMuchAttemptsToPutCode() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getCorrectAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        verificationPage.assertVerifyBtnAvailable();
        val verificationCode1 = DataHelper.getWrongVerificationCodeFor(authInfo);
        verificationPage.canNotVerifyWrongCode(verificationCode1);
        val verificationCode2 = DataHelper.getWrongVerificationCodeFor(authInfo);
        verificationPage.canNotVerifyWrongCode(verificationCode2);
        val verificationCode3 = DataHelper.getWrongVerificationCodeFor(authInfo);
        verificationPage.canNotVerifyWrongCode(verificationCode3);
        val verificationCode4 = DataHelper.getWrongVerificationCodeFor(authInfo);
        val loginPage2 = new LoginPage();
    }
}


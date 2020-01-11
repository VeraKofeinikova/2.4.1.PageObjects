package ru.netology.pageobjects.tests;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.pageobjects.data.DataHelper;
import ru.netology.pageobjects.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;

public class TestLoginPageTooMuchAttemptsForPutCode {
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

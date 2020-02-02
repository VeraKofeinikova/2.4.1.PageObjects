package ru.netology.pageobjects.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.pageobjects.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private SelenideElement loginField = $("[data-test-id=login] input");
    private SelenideElement passwordField = $("[data-test-id=password] input");
    private SelenideElement loginButton = $("[data-test-id=action-login]");

    public LoginPage() {
        loginField.shouldBe(visible);
    }

    public VerificationPage happyPath(DataHelper.AuthInfo authInfo) {
        loginField.setValue(DataHelper.getCorrectAuthInfo().getLogin());
        passwordField.setValue(DataHelper.getCorrectAuthInfo().getPassword());
        loginButton.click();
        return new VerificationPage();
    }
}


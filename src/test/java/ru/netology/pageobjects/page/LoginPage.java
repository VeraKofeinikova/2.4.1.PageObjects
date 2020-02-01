package ru.netology.pageobjects.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ru.netology.pageobjects.data.DataHelper;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class LoginPage {
    private SelenideElement loginField = $("[data-test-id=login] input");
    private SelenideElement passwordField = $("[data-test-id=password] input");
    private SelenideElement loginButton = $("[data-test-id=action-login]");
    private SelenideElement errorNotification = $(By.className("notification__content"));
    private SelenideElement emptyLoginField = $$(".input__inner").get(0);
    private SelenideElement emptyPasswordField = $$(".input__inner").get(1);

    public LoginPage() {
        loginField.shouldBe(visible);
    }

    public VerificationPage happyPath(DataHelper.AuthInfo authInfo) {
        loginField.setValue(DataHelper.getCorrectAuthInfo().getLogin());
        passwordField.setValue(DataHelper.getCorrectAuthInfo().getPassword());
        loginButton.click();
        return new VerificationPage();
    }

    public LoginPage notValidLoginValidPassword(DataHelper.AuthInfo notValidLogin) {
        loginField.setValue(DataHelper.getNotValidLoginValidPassword().getLogin());
        passwordField.setValue(DataHelper.getNotValidLoginValidPassword().getPassword());
        loginButton.click();
        errorNotification.shouldHave(text("Неверно указан логин или пароль"));
        return new LoginPage();
    }

    public LoginPage validLoginNotValidPassword(DataHelper.AuthInfo notValidPassword) {
        loginField.setValue(DataHelper.getValidLoginNotValidPassword().getLogin());
        passwordField.setValue(DataHelper.getValidLoginNotValidPassword().getPassword());
        loginButton.click();
        errorNotification.shouldHave(text("Неверно указан логин или пароль"));
        return new LoginPage();
    }

    public LoginPage emptyLoginValidPassword(DataHelper.AuthInfo emptyPassword) {
        loginField.setValue(DataHelper.getEmptyLoginValidPassword().getLogin());
        passwordField.setValue(DataHelper.getEmptyLoginValidPassword().getPassword());
        loginButton.click();
        emptyLoginField.shouldHave(Condition.text("Поле обязательно для заполнения"));
        return new LoginPage();
    }

    public LoginPage validLoginEmptyPassword(DataHelper.AuthInfo emptyPassword) {
        loginField.setValue(DataHelper.getValidLoginEmptyPassword().getLogin());
        passwordField.setValue(DataHelper.getValidLoginEmptyPassword().getPassword());
        loginButton.click();
        emptyPasswordField.shouldHave(Condition.text("Поле обязательно для заполнения"));
        return new LoginPage();
    }
}


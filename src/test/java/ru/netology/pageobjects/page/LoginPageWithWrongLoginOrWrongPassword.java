package ru.netology.pageobjects.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class LoginPageWithWrongLoginOrWrongPassword {
    private SelenideElement errorNotification = $(By.className("notification__content")).shouldHave(text("Неверно указан логин или пароль"));

    public LoginPageWithWrongLoginOrWrongPassword() {
        errorNotification.shouldBe(Condition.visible);
    }

}

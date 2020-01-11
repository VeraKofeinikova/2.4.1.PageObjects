package ru.netology.pageobjects.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class LoginPageWithWrongLoginOrWrongPassword {
    // выскакивает поп-ап с предупреждением о неправильном логине или пароле
    private SelenideElement errorNotification = $(By.className("notification__content")).shouldBe(visible).shouldHave(text("Неверно указан логин или пароль"));

}

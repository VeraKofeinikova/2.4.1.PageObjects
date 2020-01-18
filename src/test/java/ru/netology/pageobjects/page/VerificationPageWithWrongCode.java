package ru.netology.pageobjects.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class VerificationPageWithWrongCode {
    // выскакивает поп-ап с предупреждением о неверном коде
    private SelenideElement errorNotification = $(By.className("notification__content")).shouldHave(text("Неверно указан код"));

    public VerificationPageWithWrongCode() {
        errorNotification.shouldBe(Condition.visible);
    }
}

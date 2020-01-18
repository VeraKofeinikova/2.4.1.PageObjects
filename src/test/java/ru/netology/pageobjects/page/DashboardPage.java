package ru.netology.pageobjects.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private SelenideElement ul = $(By.className("list_theme_alfa-on-white"));

    public DashboardPage() {
        ul.shouldBe(Condition.visible);
    }

    private static SelenideElement firstButtonActionDeposite = $$(By.className("button_view_extra")).first();
    private static SelenideElement secondButtonActionDeposite = $$(By.className("button_view_extra")).last();

    public static DashboardPagePaymentForm FromCard2ToCard1Payment() {
        firstButtonActionDeposite.click();
        return new DashboardPagePaymentForm();
    }

    public static DashboardPagePaymentForm FromCard1ToCard2Payment() {
        secondButtonActionDeposite.click();
        return new DashboardPagePaymentForm();
    }
}


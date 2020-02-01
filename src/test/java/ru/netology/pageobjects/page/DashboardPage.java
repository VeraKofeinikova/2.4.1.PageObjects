package ru.netology.pageobjects.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private SelenideElement ul = $(By.className("list_theme_alfa-on-white"));
    private static SelenideElement firstButtonActionDeposite = $$(By.className("button_view_extra")).first();
    private static SelenideElement secondButtonActionDeposite = $$(By.className("button_view_extra")).last();
    private SelenideElement balanceOfFirstCard = $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0']");
    private SelenideElement balanceOfSecondCard = $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d']");

    public DashboardPage() {
        ul.shouldBe(Condition.visible);
    }

    public SelenideElement getBalanceOfFirstCard() {
        return balanceOfFirstCard;
    }

    public SelenideElement getBalanceOfSecondCard() {
        return balanceOfSecondCard;
    }

    public static TransferPage fromCardTwoToCardOne() {
        firstButtonActionDeposite.click();
        return new TransferPage();
    }

    public static TransferPage fromCardOneToCardTwo() {
        secondButtonActionDeposite.click();
        return new TransferPage();
    }
}


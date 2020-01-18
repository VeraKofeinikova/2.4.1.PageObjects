package ru.netology.pageobjects.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

public class DashBoardNewBalance100RublesFromCard1ToCard2 {
    private static SelenideElement balanceOfCardOne = $(withText("**** **** **** 0001"));
    private static SelenideElement balanceOfCardTwo = $(withText("**** **** **** 0002"));

    public DashBoardNewBalance100RublesFromCard1ToCard2() {
        balanceOfCardOne.shouldHave(text("10000"));
        balanceOfCardTwo.shouldHave(text("10000"));
    }
}

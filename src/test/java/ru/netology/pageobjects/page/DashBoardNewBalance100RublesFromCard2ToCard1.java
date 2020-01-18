package ru.netology.pageobjects.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

public class DashBoardNewBalance100RublesFromCard2ToCard1 {
    private static SelenideElement balanceOfCardOne = $(withText("**** **** **** 0001"));
    private static SelenideElement balanceOfCardTwo = $(withText("**** **** **** 0002"));

    public DashBoardNewBalance100RublesFromCard2ToCard1() {
        balanceOfCardOne.shouldHave(text("10100"));
        balanceOfCardTwo.shouldHave(text("9900"));
    }
}

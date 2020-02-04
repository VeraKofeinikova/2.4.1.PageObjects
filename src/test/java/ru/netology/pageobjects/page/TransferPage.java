package ru.netology.pageobjects.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ru.netology.pageobjects.data.DataHelper;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    private SelenideElement form = $(By.className("form_theme_alfa-on-white"));
    private SelenideElement amount = $(By.className("input_type_text")).find(By.className("input__control"));
    private SelenideElement from = $(By.className("input_type_tel")).find(By.className("input__control"));
    private SelenideElement transferButton = $(withText("Пополнить"));
    private SelenideElement cancelButton = $(withText("Отмена"));

    public TransferPage() {
        form.shouldBe(Condition.visible);
    }

    public DashboardPage clickCancelAndReturn() {
        cancelButton.click();
        return new DashboardPage();
    }

    public DashboardPage fromCardToCard(DataHelper.AmountOfMoney amountOfMoney, DataHelper.NumberOfCard numberOfCard) {
        amount.setValue(amountOfMoney.getAmountOfMoney());
        from.setValue(numberOfCard.getNumberOfCard());
        transferButton.click();
        return new DashboardPage();
    }

    public TransferPage stayOnTransferPage(DataHelper.AmountOfMoney amountOfMoney, DataHelper.NumberOfCard numberOfCard) {
        amount.setValue(amountOfMoney.getAmountOfMoney());
        from.setValue(numberOfCard.getNumberOfCard());
        transferButton.click();
        return new TransferPage();
    }
}

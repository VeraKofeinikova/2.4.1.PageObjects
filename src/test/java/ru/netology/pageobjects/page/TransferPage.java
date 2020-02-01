package ru.netology.pageobjects.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ru.netology.pageobjects.data.DataHelper;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    private static SelenideElement form = $(By.className("form_theme_alfa-on-white"));
    private static SelenideElement amount = $(By.className("input_type_text")).find(By.className("input__control"));
    private static SelenideElement from = $(By.className("input_type_tel")).find(By.className("input__control"));
    private static SelenideElement transferButton = $(withText("Пополнить"));
    private static SelenideElement cancelButton = $(withText("Отмена"));
    //вот это я описываю частично несуществующие элементы: у меня есть поп-ап с ошибкой в 3х случаях ниже, но
    //текст на поп-апе неправильный. Предполагаю что в разных кейсах программисты делали бы
    //поп-ап с определенным текстом видимым - поэтому я выбрала такой селектор
    private static SelenideElement notificationNotFullNumberCard = $(By.className("notification_visible"));
    private static SelenideElement notificationYouDontHaveSuchCard = $(By.className("notification_visible"));
    private static SelenideElement notificationNoNumberOfCardAtInputFrom = $(By.className("notification_visible"));
    // 3х ошибок под этим комментарием вообще не выскакивает. Про выбор селектора см коммент выше
    private static SelenideElement notificationMoreThenBalanceOfCard = $(By.className("notification_visible"));
    private static SelenideElement notificationSameNumberOfCard = $(By.className("notification_visible"));
    private static SelenideElement notificationEmptyAmountOfMoney = $(By.className("notification_visible"));

    public TransferPage() {
        form.shouldBe(Condition.visible);
    }

    public static DashboardPage clickCancelandReturn() {
        cancelButton.click();
        return new DashboardPage();
    }

    public static DashboardPage fromCardToCard(DataHelper.AmountOfMoney amountOfMoney, DataHelper.NumberOfCard numberOfCard) {
        amount.setValue(amountOfMoney.getAmountOfMoney());
        from.setValue(numberOfCard.getNumberOfCard());
        transferButton.click();
        return new DashboardPage();
    }

    public static TransferPage notificationAboutNotFullNumberCard(DataHelper.AmountOfMoney amountOfMoney, DataHelper.NumberOfCard numberOfCard) {
        amount.setValue(amountOfMoney.getAmountOfMoney());
        from.setValue(numberOfCard.getNumberOfCard());
        transferButton.click();
        notificationNotFullNumberCard.waitUntil(Condition.visible, 5000).shouldHave(Condition.text("Введите номер карты полностью в поле ввода Откуда"));
        return new TransferPage();
    }

    public static TransferPage notificationYouDontHaveSuchCard(DataHelper.AmountOfMoney amountOfMoney, DataHelper.NumberOfCard numberOfCard) {
        amount.setValue(amountOfMoney.getAmountOfMoney());
        from.setValue(numberOfCard.getNumberOfCard());
        transferButton.click();
        notificationYouDontHaveSuchCard.waitUntil(Condition.visible, 5000).shouldHave(Condition.text("У вас нет карты с таким номером"));
        return new TransferPage();
    }

    public static TransferPage notificationSameNumberOfCard(DataHelper.AmountOfMoney amountOfMoney, DataHelper.NumberOfCard numberOfCard) {
        amount.setValue(amountOfMoney.getAmountOfMoney());
        from.setValue(numberOfCard.getNumberOfCard());
        transferButton.click();
        notificationSameNumberOfCard.shouldHave(Condition.text("Невозможно перевести деньги на эту же карту. Введите другой номер карты"));
        return new TransferPage();
    }

    public static TransferPage notificationOfEmptyAmountOfMoney(DataHelper.AmountOfMoney amountOfMoney, DataHelper.NumberOfCard numberOfCard) {
        amount.setValue(amountOfMoney.getAmountOfMoney());
        from.setValue(numberOfCard.getNumberOfCard());
        transferButton.click();
        notificationEmptyAmountOfMoney.shouldHave(Condition.text("Введите сумму, которую хотите перевести"));
        return new TransferPage();
    }

    public static TransferPage notificationOfNoNumberOfCardAtInputFrom(DataHelper.AmountOfMoney amountOfMoney, DataHelper.NumberOfCard numberOfCard) {
        amount.setValue(amountOfMoney.getAmountOfMoney());
        from.setValue(numberOfCard.getNumberOfCard());
        transferButton.click();
        notificationNoNumberOfCardAtInputFrom.shouldHave(Condition.text("Введите номер карты в поле Откуда"));
        return new TransferPage();
    }

    public static TransferPage notificationMoreThenBalanceOfCard(DataHelper.AmountOfMoney amountOfMoney, DataHelper.NumberOfCard numberOfCard) {
        amount.setValue(amountOfMoney.getAmountOfMoney());
        from.setValue(numberOfCard.getNumberOfCard());
        transferButton.click();
        notificationMoreThenBalanceOfCard.waitUntil(Condition.visible, 5000).shouldHave(Condition.text("Невозможно перевести сумму денег, превышающую баланс карты"));
        return new TransferPage();
    }
}

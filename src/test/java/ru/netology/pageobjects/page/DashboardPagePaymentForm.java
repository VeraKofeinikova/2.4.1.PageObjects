package ru.netology.pageobjects.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ru.netology.pageobjects.data.DataHelper;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

public class DashboardPagePaymentForm {
    private static SelenideElement form = $(By.className("form_theme_alfa-on-white"));
    private static SelenideElement amount = $(By.className("input_type_text")).find(By.className("input__control"));
    private static SelenideElement from = $(By.className("input_type_tel")).find(By.className("input__control"));
    private static SelenideElement transferButton = $(withText("Пополнить"));
    private static SelenideElement cancelButton = $(withText("Отмена"));
    //вот это я описываю частично несуществующие элементы
    // notificationNotFullNumberCard и notificationYouDontHaveSuchCard - должны содержать другой текст
    //см методы где они используются - и поиск по селектору различался бы
    private static SelenideElement notificationNotFullNumberCard = $(By.className("notification_visible"));
    private static SelenideElement notificationYouDontHaveSuchCard = $(By.className("notification_visible"));
    // такой ошибки вообще не выскакивает - вероятно ее селектор тоже был бы отличен от первых двух
    private static SelenideElement notificationMoreThenBalanceOfCard = $(By.className("notification_visible"));


    public DashboardPagePaymentForm() {
        form.shouldBe(Condition.visible);
    }

    public static DashboardPageNoChangeOfBalance notificationMoreThenBalanceOfCard(DataHelper.AmountOfMoney amountOfMoney, DataHelper.NumberOfCard numberOfCard) {
        amount.setValue(amountOfMoney.getAmountOfMoney());
        from.setValue(numberOfCard.getNumberOfCard());
        transferButton.click();
        notificationMoreThenBalanceOfCard.waitUntil(Condition.visible, 5000).shouldHave(Condition.text("Невозможно перевести число, превышающее баланс карты"));
        cancelButton.click();
        return new DashboardPageNoChangeOfBalance();
    }

    public static DashboardPage notificationYouDontHaveSuchCard(DataHelper.AmountOfMoney amountOfMoney, DataHelper.NumberOfCard numberOfCard) {
        amount.setValue(amountOfMoney.getAmountOfMoney());
        from.setValue(numberOfCard.getNumberOfCard());
        transferButton.click();
        // если здесь убрать //.shouldHave(Condition.text("У вас нет карты с таким номером"));
        //и вставить .shouldBe(Condition.visible);
        //то тест падать не будет - но поскольку текст в ошибке неправильный - я специально обрушиваю тест
        notificationYouDontHaveSuchCard.shouldHave(Condition.text("У вас нет карты с таким номером"));
        cancelButton.click();
        return new DashboardPage();
    }

    public static DashboardPage notificationAboutNotFullNumberCard(DataHelper.AmountOfMoney amountOfMoney, DataHelper.NumberOfCard numberOfCard) {
        amount.setValue(amountOfMoney.getAmountOfMoney());
        from.setValue(numberOfCard.getNumberOfCard());
        transferButton.click();
        // если здесь убрать .shouldHave(Condition.text("Введите номер карты полностью в поле ввода Откуда"));
        //и вставить .shouldBe(Condition.visible);
        //то тест падать не будет - но поскольку текст в ошибке неправильный - я специально обрушиваю тест невыполнимым условием
        notificationNotFullNumberCard.shouldHave(Condition.text("Введите номер карты полностью в поле ввода Откуда"));
        cancelButton.click();
        return new DashboardPage();
    }

    public static DashboardPage clickCancelandReturn() {
        cancelButton.click();
        return new DashboardPage();
    }

    public static DashBoardNewBalance100RublesFromCard2ToCard1 fromCard2ToCard1OneHundredRubles(DataHelper.AmountOfMoney amountOfMoney, DataHelper.NumberOfCard numberOfCard) {
        amount.setValue(amountOfMoney.getAmountOfMoney());
        from.setValue(numberOfCard.getNumberOfCard());
        transferButton.click();
        return new DashBoardNewBalance100RublesFromCard2ToCard1();
    }

    public static DashBoardNewBalance100RublesFromCard1ToCard2 fromCard1ToCard2OneHundredRubles(DataHelper.AmountOfMoney amountOfMoney, DataHelper.NumberOfCard numberOfCard) {
        amount.setValue(amountOfMoney.getAmountOfMoney());
        from.setValue(numberOfCard.getNumberOfCard());
        transferButton.click();
        return new DashBoardNewBalance100RublesFromCard1ToCard2();
    }
}

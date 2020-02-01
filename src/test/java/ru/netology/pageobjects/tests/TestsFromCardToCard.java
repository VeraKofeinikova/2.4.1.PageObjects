package ru.netology.pageobjects.tests;

import lombok.val;
import org.junit.jupiter.api.*;
import ru.netology.pageobjects.data.DataHelper;
import ru.netology.pageobjects.page.DashboardPage;
import ru.netology.pageobjects.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestsFromCardToCard {
    @BeforeAll
    public static void login() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getCorrectAuthInfo();
        val verificationPage = loginPage.happyPath(authInfo);
        verificationPage.assertVerifyBtnAvailable();
        val verificationCode = DataHelper.getVerificationCodeFor();
        verificationPage.validVerify(verificationCode);
    }

    @Test
    @DisplayName("Нажать любую Пополнить, а на странице Пополнения нажать Отменить, вернулись на страницу баланса")
    void cancelOfPayment() {
        val transferPage = DashboardPage.fromCardTwoToCardOne();
        transferPage.clickCancelandReturn();
    }

    //пока должен падать - сейчас текст у сообщения об ошибке неправильный
    @Test
    @DisplayName("Ввести номер карты неполностью типа 5559 000")
    void notWriteFullNumberOfCard() {
        val transferPage = DashboardPage.fromCardTwoToCardOne();
        val amountOfMoney = DataHelper.oneHundredRubles();
        val cardWithNotFullNumber = DataHelper.cardWithNotFullNumber();
        transferPage.notificationAboutNotFullNumberCard(amountOfMoney, cardWithNotFullNumber);
    }

    //пока должен падать - сейчас текст у сообщения об ошибке неправильный
    @Test
    @DisplayName("Ввести несуществующую карту 5559 0000 0000 0003")
    void wrongNumberOfCardFromWhichWeTakeMoney() {
        val transferPage = DashboardPage.fromCardTwoToCardOne();
        val amountOfMoney = DataHelper.oneHundredRubles();
        val cardDontExist = DataHelper.cardDontExist();
        transferPage.notificationYouDontHaveSuchCard(amountOfMoney, cardDontExist);
    }

    //пока должен падать: должно выскакивать сообщение об ошибке, а нас просто выкидывает
    //на DashboardPage
    @Test
    @DisplayName("Ввести тот же номер карты, на которую переводим")
    void tryToMakePaymentFromSameCard() {
        val transferPage = DashboardPage.fromCardTwoToCardOne();
        val amountOfMoney = DataHelper.oneHundredRubles();
        val numberOfCardOne = DataHelper.cardWithOne();
        transferPage.notificationSameNumberOfCard(amountOfMoney, numberOfCardOne);
    }

    //пока должен падать - сейчас текст у сообщения об ошибке неправильный
    @Test
    @DisplayName("Нажать любую Пополнить, а на странице Пополнения не ввести номер карты, с которой переводим")
    void tryToMakePaymentWithEmptyNumberOfCard() {
        val transferPage = DashboardPage.fromCardTwoToCardOne();
        val amountOfMoney = DataHelper.oneHundredRubles();
        val emptyNumberOfCard = DataHelper.emptyNumberOfCard();
        transferPage.notificationOfNoNumberOfCardAtInputFrom(amountOfMoney, emptyNumberOfCard);
    }

    //пока должен падать: должно выскакивать сообщение об ошибке, а нас просто выкидывает
    //на DashboardPage
    @Test
    @DisplayName("Нажать любую Пополнить, а на странице Пополнения не ввести количество денег")
    void tryToMakePaymentWithEmptyAmountOfMoney() {
        val transferPage = DashboardPage.fromCardTwoToCardOne();
        val amountOfMoney = DataHelper.emptyAmountOfMoney();
        val numberOfCardTwo = DataHelper.cardWithTwo();
        transferPage.notificationOfEmptyAmountOfMoney(amountOfMoney, numberOfCardTwo);
    }

    @Test
    @DisplayName("Успешный перевод 100 рублей с карты2 на карту1")
    void sendFromCardTwoToCardOne() {
        val dashboardPage = new DashboardPage();
        int startBalanceOfCardOne = Integer.parseInt(DataHelper.extractingBalance(dashboardPage.getBalanceOfFirstCard().getText()));
        int startBalanceOfCardTwo = Integer.parseInt(DataHelper.extractingBalance(dashboardPage.getBalanceOfSecondCard().getText()));
        val transferPage = DashboardPage.fromCardTwoToCardOne();
        val amountOfMoney = DataHelper.oneHundredRubles();
        int amountOfMoneyForCalculate = Integer.parseInt(amountOfMoney.getAmountOfMoney());
        val numberOfCardTwo = DataHelper.cardWithTwo();
        int expectedBalanceOfCardOne = startBalanceOfCardOne + amountOfMoneyForCalculate;
        int expectedBalanceOfCardTwo = startBalanceOfCardTwo - amountOfMoneyForCalculate;
        transferPage.fromCardToCard(amountOfMoney, numberOfCardTwo);
        int actualBalanceOfFirstCard = Integer.parseInt(DataHelper.extractingBalance(dashboardPage.getBalanceOfFirstCard().getText()));
        int actualBalanceOfSecondCard = Integer.parseInt(DataHelper.extractingBalance(dashboardPage.getBalanceOfSecondCard().getText()));
        assertEquals(expectedBalanceOfCardOne, actualBalanceOfFirstCard);
        assertEquals(expectedBalanceOfCardTwo, actualBalanceOfSecondCard);
    }

    @Test
    @DisplayName("Успешный перевод 100 рублей с карты1 на карту2")
    void sendFromCardOneToCardTwo() {
        val dashboardPage = new DashboardPage();
        int startBalanceOfCardOne = Integer.parseInt(DataHelper.extractingBalance(dashboardPage.getBalanceOfFirstCard().getText()));
        int startBalanceOfCardTwo = Integer.parseInt(DataHelper.extractingBalance(dashboardPage.getBalanceOfSecondCard().getText()));
        val transferPage = DashboardPage.fromCardOneToCardTwo();
        val amountOfMoney = DataHelper.oneHundredRubles();
        int amountOfMoneyForCalculate = Integer.parseInt(amountOfMoney.getAmountOfMoney());
        val numberOfCardOne = DataHelper.cardWithOne();
        int expectedBalanceOfCardOne = startBalanceOfCardOne - amountOfMoneyForCalculate;
        int expectedBalanceOfCardTwo = startBalanceOfCardTwo + amountOfMoneyForCalculate;
        transferPage.fromCardToCard(amountOfMoney, numberOfCardOne);
        int actualBalanceOfFirstCard = Integer.parseInt(DataHelper.extractingBalance(dashboardPage.getBalanceOfFirstCard().getText()));
        int actualBalanceOfSecondCard = Integer.parseInt(DataHelper.extractingBalance(dashboardPage.getBalanceOfSecondCard().getText()));
        assertEquals(expectedBalanceOfCardOne, actualBalanceOfFirstCard);
        assertEquals(expectedBalanceOfCardTwo, actualBalanceOfSecondCard);
    }

    //должен упасть - сейчас возможно перевести на другую карту сумму, которая
    //больше баланса карты, откуда переводят
    @Test
    @DisplayName("Невозможно перевести на другую карту сумму, большую чем баланс карты")
    void shouldNotMakePaymentIfAmountOfSendingBiggerThenBalanceOfCard() {
        val dashboardPage = new DashboardPage();
        int startBalanceOfCardOne = Integer.parseInt(DataHelper.extractingBalance(dashboardPage.getBalanceOfFirstCard().getText()));
        int startBalanceOfCardTwo = Integer.parseInt(DataHelper.extractingBalance(dashboardPage.getBalanceOfSecondCard().getText()));
        val transferPage = DashboardPage.fromCardTwoToCardOne();
        val amountOfMoney = DataHelper.twentyThousandsRubles();
        int amountOfMoneyForCalculate = Integer.parseInt(amountOfMoney.getAmountOfMoney());
        val numberOfCardTwo = DataHelper.cardWithTwo();
        if (amountOfMoneyForCalculate > startBalanceOfCardTwo) {
            transferPage.notificationMoreThenBalanceOfCard(amountOfMoney, numberOfCardTwo);
        }
    }
}

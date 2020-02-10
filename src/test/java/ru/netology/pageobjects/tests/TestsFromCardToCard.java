package ru.netology.pageobjects.tests;

import com.codeborne.selenide.Selenide;
import lombok.val;
import org.junit.jupiter.api.*;
import ru.netology.pageobjects.data.DataHelper;
import ru.netology.pageobjects.page.DashboardPage;
import ru.netology.pageobjects.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestsFromCardToCard {
    @BeforeEach
    public void clearCookiesAndStorageAndLogin() {
        open("http://localhost:9999");
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
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
        val dashboardPage = new DashboardPage();
        val transferPage = dashboardPage.fromCardTwoToCardOne();
        transferPage.clickCancelAndReturn();
    }

    @Test
    @DisplayName("Успешный перевод 100 рублей с карты2 на карту1")
    void sendFromCardTwoToCardOne() {
        val dashboardPage = new DashboardPage();
        int startBalanceOfCardOne = Integer.parseInt(DataHelper.extractingBalance(dashboardPage.getBalanceOfFirstCard().getText()));
        int startBalanceOfCardTwo = Integer.parseInt(DataHelper.extractingBalance(dashboardPage.getBalanceOfSecondCard().getText()));
        val transferPage = dashboardPage.fromCardTwoToCardOne();
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
        val transferPage = dashboardPage.fromCardOneToCardTwo();
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

    @Test
    @DisplayName("Невозможно перевести на другую карту сумму, большую чем баланс карты")
    void shouldNotMakePaymentIfAmountOfSendingBiggerThenBalanceOfCard() {
        val dashboardPage = new DashboardPage();
        int startBalanceOfCardOne = Integer.parseInt(DataHelper.extractingBalance(dashboardPage.getBalanceOfFirstCard().getText()));
        int startBalanceOfCardTwo = Integer.parseInt(DataHelper.extractingBalance(dashboardPage.getBalanceOfSecondCard().getText()));
        val transferPage = dashboardPage.fromCardTwoToCardOne();
        val amountOfMoney = DataHelper.twentyThousandsRubles();
        int amountOfMoneyForCalculate = Integer.parseInt(amountOfMoney.getAmountOfMoney());
        val numberOfCardTwo = DataHelper.cardWithTwo();
        if (amountOfMoneyForCalculate > startBalanceOfCardTwo) {
            transferPage.stayOnTransferPage(amountOfMoney, numberOfCardTwo);
        }
    }
}

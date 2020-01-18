package ru.netology.pageobjects.tests;

import lombok.val;
import org.junit.jupiter.api.*;
import ru.netology.pageobjects.data.DataHelper;
import ru.netology.pageobjects.page.DashboardPage;
import ru.netology.pageobjects.page.DashboardPagePaymentForm;
import ru.netology.pageobjects.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;

public class TestsFromCardToCard {
    @BeforeAll
    public static void Login() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getCorrectAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        verificationPage.assertVerifyBtnAvailable();
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
    }

    @Test
    @DisplayName("Успешный перевод 100 рублей с карты2 на карту1 и обратно")
    void SendFromCard1ToCard2AndBack() {
        val dashboardPaymentFormFromCard2ToCard1 = DashboardPage.FromCard2ToCard1Payment();
        val amountOfMoney = DataHelper.oneHundredRubles();
        val numberOfCard2 = DataHelper.CardWithTwo();
        val DashBoardNewBalance100RublesFromCard2ToCard1 = DashboardPagePaymentForm.fromCard2ToCard1OneHundredRubles(amountOfMoney, numberOfCard2);

        val dashboardPaymentFormFromCard1ToCard2 = DashboardPage.FromCard1ToCard2Payment();
        val numberOfCard1 = DataHelper.CardWithOne();
        val DashBoardNewBalance100RublesFromCard1ToCard2 = DashboardPagePaymentForm.fromCard1ToCard2OneHundredRubles(amountOfMoney, numberOfCard1);
    }

    @Test
    @DisplayName("Нажать любую Пополнить, а на странице Пополнения нажать Отменить, вернулись на страницу баланса")
    void cancelOfPayment() {
        val dashboardPaymentFormFromCard2ToCard1 = DashboardPage.FromCard2ToCard1Payment();
        val dashBoardPage = DashboardPagePaymentForm.clickCancelandReturn();
    }

    @Test
    @DisplayName("Нажать любую Пополнить, а на странице Пополнения ввести карту неполностью типа 5559 000")
    void NotWriteFullNumberOfCard() {
        val dashboardPaymentFormFromCard2ToCard1 = DashboardPage.FromCard2ToCard1Payment();
        val amountOfMoney = DataHelper.oneHundredRubles();
        val cardWithNotFullNumber = DataHelper.CardWithNotFullNumber();
        val dashboardPage = DashboardPagePaymentForm.notificationAboutNotFullNumberCard(amountOfMoney, cardWithNotFullNumber);
        //выскакивает ошибка с неправильным текстом и тест падает
    }

    @Test
    @DisplayName("Нажать Пополнить, а на странице Пополнения ввести несуществующую карту 5559 0000 0000 0003")
    void WrongNumberOfCardFromWhichWeTakeMoney() {
        val dashboardPaymentFormFromCard2ToCard1 = DashboardPage.FromCard2ToCard1Payment();
        val amountOfMoney = DataHelper.oneHundredRubles();
        val cardDontExist = DataHelper.CardDontExist();
        val dashboardPage = DashboardPagePaymentForm.notificationYouDontHaveSuchCard(amountOfMoney, cardDontExist);
        //выскакивает ошибка с неправильным текстом и тест падает
    }

    @Test
    @DisplayName("Невозможно перевести на другую карту сумму, большую чем баланс карты")
    void shouldNotMakePaymentIfAmountOfSendingBiggerThenBalanceOfCard() {
        val dashboardPaymentFormFromCard2ToCard1 = DashboardPage.FromCard2ToCard1Payment();
        val amountOfMoney = DataHelper.twentyThousandsRubles();
        val numberOfCard2 = DataHelper.CardWithTwo();
        val dashboardPage = DashboardPagePaymentForm.notificationMoreThenBalanceOfCard(amountOfMoney, numberOfCard2);

        //должна будет возвращаться ошибка, но списание будет происходить и тест упадет
    }
}
package ru.netology.pageobjects.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.pageobjects.data.DataHelper;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private SelenideElement codeField = $("[data-test-id=code] input");
    private SelenideElement verifyButton = $("[data-test-id=action-verify]");

    public VerificationPage() {
//первым делом проверяем, что поле для ввода кода – ВИДИМОЕ
//если невидимое – значит мы НЕ перешли на нужную страницу
// и тест упадет и дальше никакие методы вызываться не будут
        codeField.shouldBe(visible);
    }

    //метод validVerify, где мы вводим код и нажимаем кнопку
    // и получаем DashboardPage (личный кабинет или что-то еще,
    // что происходит после авторизации)
    public DashboardPage validVerify(DataHelper.VerificationCode verificationCode) {
        codeField.setValue(verificationCode.getCode());
        verifyButton.click();
        return new DashboardPage();
    }

    public VerificationPageWithWrongCode canNotVerifyWrongCode(DataHelper.VerificationCode verificationCode) {
        codeField.setValue(verificationCode.getCode());
        verifyButton.click();
        return new VerificationPageWithWrongCode();
    }

    //проверяем что кнопка для ввода пароля доступна.
    // берем кнопку и вызываем метод shouldBeVisible
    public void assertVerifyBtnAvailable() {
        verifyButton.shouldBe(visible);
    }
}
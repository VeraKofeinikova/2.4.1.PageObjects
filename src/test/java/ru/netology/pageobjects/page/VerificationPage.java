package ru.netology.pageobjects.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ru.netology.pageobjects.data.DataHelper;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private SelenideElement codeField = $("[data-test-id=code] input");
    private SelenideElement verifyButton = $("[data-test-id=action-verify]");
    private SelenideElement errorNotification = $(By.className("notification__content"));

    public VerificationPage() {
        codeField.shouldBe(visible);
    }

    public DashboardPage validVerify(DataHelper.VerificationCode verificationCode) {
        codeField.setValue(DataHelper.getVerificationCodeFor().getCode());
        verifyButton.click();
        return new DashboardPage();
    }

    public VerificationPage notValidVerify(DataHelper.VerificationCode verificationCode) {
        codeField.setValue(DataHelper.getWrongVerificationCodeFor().getCode());
        verifyButton.click();
        errorNotification.waitUntil(Condition.visible,5000).shouldHave(text("Неверно указан код"));
        return new VerificationPage();
    }

    public void assertVerifyBtnAvailable() {
        verifyButton.shouldBe(visible);
    }

    public VerificationPage tooMuchAttemptsOfVerificationCode(DataHelper.VerificationCode verificationCode4) {
        codeField.setValue(DataHelper.getWrongVerificationCodeFor().getCode());
        verifyButton.click();
        errorNotification.waitUntil(Condition.visible,15000).shouldHave(text("Превышено количество попыток ввода кода"));
        return new VerificationPage();
    }
}

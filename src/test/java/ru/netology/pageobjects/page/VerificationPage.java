package ru.netology.pageobjects.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.pageobjects.data.DataHelper;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private SelenideElement codeField = $("[data-test-id=code] input");
    private SelenideElement verifyButton = $("[data-test-id=action-verify]");

    public VerificationPage() {
        codeField.shouldBe(visible);
    }

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

    public void assertVerifyBtnAvailable() {
        verifyButton.shouldBe(visible);
    }
}
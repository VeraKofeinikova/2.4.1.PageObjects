package ru.netology.pageobjects.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {
    private SelenideElement ul = $(By.className("list_theme_alfa-on-white"));

    public DashboardPage() {
        ul.shouldBe(Condition.visible);
    }
}

    // закрыт, потому что у спиннера нет css в нашем приложении
    //метод, который будет ожидать какого-то действия
//    public void waitUntilPageisLoaded() {
//        $ ("loader css").waitUntil(disappear, 10);
//    }

    // а можно написать чтобы у нас отобразился какой-то элемент
    // и это вероятно будет логичнее, потому что после входа в личный кабинет
    //мы должны увидеть баланс, например и делаем ожидание секунд в 5, чтобы долго не ждать
//    public void waitUntilPageisLoaded() {
//        $ ("balance css").waitUntil(visible, 5);
//    }


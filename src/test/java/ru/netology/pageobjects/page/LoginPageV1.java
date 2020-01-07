package ru.netology.pageobjects.page;

import ru.netology.pageobjects.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class LoginPageV1 {
    //вызываем метод validLogin для авторизации, у нас отрабатывают 3 строчки кода
    public VerificationPage validLogin(DataHelper.AuthInfo info) {
        $("[data-test-id=login] input").setValue(info.getLogin());
        $("[data-test-id=password] input").setValue(info.getPassword());
        $("[data-test-id=action-login]").click();
//после успешного введения логина-пароля мы получаем
// новый объект – переход на новую страницу
// (личный кабинет или другое) – это как бы знак
// успешной отработки трех строчек кода выше
        return new VerificationPage();
    }
}


package pages;

import com.codeborne.selenide.SelenideElement;
import data.DataHelper;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class LoginPage { //авторизация
    //2й вариант
    private SelenideElement loginField = $("[data-test-id='login'] input");
    private SelenideElement passwordField = $("[data-test-id='password'] input");
    private SelenideElement loginButton = $("[data-test-id='action-login'] span");

    public VerificationPage validLogin(DataHelper.AuthInfo info) {
        loginField.val(info.getLogin());
        passwordField.val(info.getPassword());
        loginButton.click();
        return new VerificationPage();
    }
}

package test;

import Pages.DashboardPage;
import Pages.LoginPage;
import Pages.TransferPage;
import com.codeborne.selenide.Configuration;
import data.DataHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PageObjectTest {
    // тесты:
    //перевод с карты на карту
    //проверка баланса
    //возврат на страницу с картами после успешного пополнения

    @BeforeEach
    void setUp() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
    }

    @Test
    void shouldRefill1Card() { //пополнение 1й карты со 2й
        var loginPage = new LoginPage(); //создаем страницу
        var authInfo = DataHelper.getAuthInfo();//передаем данные о пользователе
        var verificationPage = loginPage.validLogin(authInfo);//проверяем правильность данных
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);//передаем код
        var dashboardPage = verificationPage.validVerify(verificationCode);//проверяем правильность кода
        //перешли на страницу с картами
        int balance1BeforeTransfer = dashboardPage.getCardBalance(0);//получаем баланс 1 карты до пополнения
        int balance2BeforeTransfer = dashboardPage.getCardBalance(1);//получаем баланс 2 карты до пополнения
        var moneyTransferPage = dashboardPage.refillSum(0); //Выбираем карту для пополнения
        int amount = 500; // сумма перевода
        moneyTransferPage.moneyTransfer(amount, DataHelper.get2CardInfo().getCardNumber()); //передаем сумму, данные по карте
        //проверка
        Assertions.assertEquals(dashboardPage.getCardBalance(0), balance1BeforeTransfer + amount);
        Assertions.assertEquals(dashboardPage.getCardBalance(1), balance2BeforeTransfer - amount);


    }

    @Test
    void shouldRefill2Card() {//пополнение 2й карты с 1й
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        int balance1BeforeTransfer = dashboardPage.getCardBalance(0);
        int balance2BeforeTransfer = dashboardPage.getCardBalance(1);
        var moneyTransferPage = dashboardPage.refillSum(1);
        int amount = 3000;
        moneyTransferPage.moneyTransfer(amount, DataHelper.get1CardInfo().getCardNumber());
        Assertions.assertEquals(dashboardPage.getCardBalance(0), balance1BeforeTransfer - amount);
        Assertions.assertEquals(dashboardPage.getCardBalance(1), balance2BeforeTransfer + amount);
    }

    @Test
    void shouldNotRefill1To1(){// пополнение 1й карты с 1й карты пройти не должно
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        int balance1BeforeTransfer = dashboardPage.getCardBalance(0);
        int balance2BeforeTransfer = dashboardPage.getCardBalance(1);
        var moneyTransferPage = dashboardPage.refillSum(0);
        int amount = 2300;
        moneyTransferPage.moneyTransfer(amount, DataHelper.get1CardInfo().getCardNumber());
        Assertions.assertEquals(dashboardPage.getCardBalance(0), balance1BeforeTransfer);

    }



}

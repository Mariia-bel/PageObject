package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class TransferPage {
    private SelenideElement heading1 = $("[data-test-id='dashboard']");
    private SelenideElement heading2 = $("div#root h1");
    private SelenideElement amountField = $("[data-test-id='amount'] input");
    private SelenideElement cardNumberField = $("[data-test-id='from'] input");
    private SelenideElement transferButton = $("[data-test-id='action-transfer'] span");
    public SelenideElement errorNotification = $(".notification__content");
    private SelenideElement cancelButton = $("[data-test-id='action-cancel']");

    public TransferPage() {//видимость заголовков
        heading1.shouldBe(visible);
        heading2.shouldBe(visible);
    }

    public TransferPage moneyTransfer(int sum, String cardNumber) {//перевод денег
        amountField.val(String.valueOf(sum));
        cardNumberField.val(cardNumber);
        transferButton.click();

        return new TransferPage();
    }

    public void errorNotification(){
        errorNotification.shouldBe(visible);

    }

}
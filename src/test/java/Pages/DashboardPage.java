package Pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private SelenideElement heading1 = $("[data-test-id='dashboard']");
    private SelenideElement heading2 = $(byText("Ваши карты"));
    private ElementsCollection cards = $$(".list__item div");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    public DashboardPage() { //видимость заголовков
        heading1.shouldBe(visible);
        heading2.shouldBe(visible);
    }

    //метод получения баланса
    public int getCardBalance(int id) { //баланс по ID карты
        val text = cards.get(id).text();
        return extractBalance(text);
    }

    int extractBalance(String text) { //общий баланс
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public TransferPage refillSum(int id) {//пополнение
        cards.get(id).$(byText("Пополнить")).click();
        return new TransferPage();
    }
}


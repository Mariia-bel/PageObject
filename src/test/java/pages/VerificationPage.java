package pages;

import com.codeborne.selenide.SelenideElement;
import data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

//верификация
public class VerificationPage {
    private SelenideElement codeField = $("[data-test-id=code] input");
    private SelenideElement verifyButton = $("[data-test-id=action-verify]");

    public VerificationPage(){
        codeField.shouldBe(visible);
    }
    public DashboardPage validVerify(DataHelper.VerificationCode verificationCode) {
        codeField.val(verificationCode.getCode());
        verifyButton.click();
        return new DashboardPage();
    }

}

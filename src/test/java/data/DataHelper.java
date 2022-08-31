package data;

import lombok.Value;

public class DataHelper {
    private DataHelper() {
    }

    //Авторизация
    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getOtherAuthInfo(AuthInfo original) {
        return new AuthInfo("vasya", "qwerty123");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }


    //Карты
    @Value
    public static class CardInfo {
        private String cardNumber;
    }

    public static CardInfo get1CardInfo() {
        return new CardInfo("5559 0000 0000 0001");
    }

    public static CardInfo get2CardInfo() {
        return new CardInfo("5559 0000 0000 0002");
    }


}

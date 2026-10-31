import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class LoginTests extends TestBase {

    @Test
    void successfulLoginTest() {
        open("/login");

        $("[data-testid=login-input]").setValue("user1");
        $("[data-testid=password-input]").setValue("password1").pressEnter();

        $("[data-testid=welcome-message]").shouldHave(text("Welcome, user1!"));
    }

    @Test
    void wrongPasswordLoginTest() {
        open("/login");

        $("[data-testid=login-input]").setValue("user1");
        $("[data-testid=password-input]").setValue("WRONGPASSWORD").pressEnter();

        $("[data-testid=error-message]").shouldHave(text("Wrong login or password"));
    }

    @Test
    void missingPasswordLoginTest() {
        open("/login");

        $("[data-testid=login-input]").setValue("user1").pressEnter();
        $("[data-testid=error-message]").shouldBe(text("Password is required (minimum 6 characters)"));
    }

    @Test
    void toLongNameLoginTest() {
        open("/login");

        $("[data-testid=login-input]").setValue("user1111111111111111111111111111111111111111111111111111111111111");
        $("[data-testid=password-input]").setValue("password1").pressEnter();

        $("[data-testid=error-message]").shouldHave(text("username размер должен находиться в диапазоне от 3 до 64"));
    }

    @Test
    void wrongSimbolsLoginTest() {
        open("/login");

        $("[data-testid=login-input]").setValue("пользователь");
        $("[data-testid=password-input]").setValue("пароль!@#").pressEnter();

        $("[data-testid=error-message]").shouldHave(text("Wrong login or password"));
    }

}

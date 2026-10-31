import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class LoginTests {

    @Test
    void successfulLoginTest() {
        open("https://autotests.ai/stack/backend-java-spring/frontend-typescript-react/login");

        $("[data-testid=login-input]").setValue("user1");
        $("[data-testid=password-input]").setValue("password1").pressEnter();

        $("[data-testid=welcome-message]").shouldHave(text("Welcome, user1!"));

        closeWebDriver();
    }

    @Test
    void wrongPasswordLoginTest() {
        open("https://autotests.ai/stack/backend-java-spring/frontend-typescript-react/login");

        $("[data-testid=login-input]").setValue("user1");
        $("[data-testid=password-input]").setValue("WRONGPASSWORD").pressEnter();

        $("[data-testid=error-message]").shouldHave(text("Wrong login or password"));

        closeWebDriver();
    }

    @Test
    void missingPasswordLoginTest() {
        open("https://autotests.ai/stack/backend-java-spring/frontend-typescript-react/login");

        $("[data-testid=login-input]").setValue("user1").pressEnter();
        $("[data-testid=error-message]").shouldBe(text("Password is required (minimum 6 characters)"));

        closeWebDriver();
    }

    @Test
    void toLongNameAndPasswordLoginTest() {
        open("https://autotests.ai/stack/backend-java-spring/frontend-typescript-react/login");

        $("[data-testid=login-input]").setValue("user1111111111111111111111111111111111111111111111111111111111111");
        $("[data-testid=password-input]").setValue("password1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111").pressEnter();

        $("[data-testid=error-message]").shouldHave(text("password размер должен находиться в диапазоне от 6 до 128; username размер должен находиться в диапазоне от 3 до 64"));

        closeWebDriver();
    }

    @Test
    void wrongSimbolsLoginTest() {
        open("https://autotests.ai/stack/backend-java-spring/frontend-typescript-react/login");

        $("[data-testid=login-input]").setValue("пользователь");
        $("[data-testid=password-input]").setValue("пароль!@#").pressEnter();

        $("[data-testid=error-message]").shouldHave(text("Wrong login or password"));

        closeWebDriver();
    }

}

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginTests {

    @Test
    void successfulLoginTest() {
        open("https://autotests.ai/stack/backend-java-spring/frontend-typescript-react/login");
        $("[data-testid=login-input]").setValue("user1");
        $("[data-testid=password-input]").setValue("password1").pressEnter();
        $("[data-testid=welcome-message]").shouldBe(text("Welcome, user1!"));
    }

    @Test
    void wrongPasswordTest() {
        open("https://autotests.ai/stack/backend-java-spring/frontend-typescript-react/login");
        $("[data-testid=login-input]").setValue("user1");
        $("[data-testid=password-input]").setValue("WRONGPASSWORD").pressEnter();
        $("[data-testid=error-message]").shouldBe(text("Wrong login or password"));
    }

    @Test
    void missingPasswordTest() {
        open("https://autotests.ai/stack/backend-java-spring/frontend-typescript-react/login");
        $("[data-testid=password-input]").setValue("WRONGPASSWORD").pressEnter();
        $("[data-testid=error-message]").shouldBe(text("Login is required (minimum 3 characters)"));
    }

}

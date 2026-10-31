import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationTests extends TestBase {

    @Test
    @Disabled("todo: ...")
    void successfulRegistrationTests() {
        open("/register");

        $("[data-testid=register-login-input]").setValue("user1");
        $("[data-testid=register-password-input]").setValue("password1");
        $("[data-testid=confirm-password-input]").setValue("password1").pressEnter();

        $("[data-testid=welcome-message]").shouldHave(text("Welcome, user1!"));
    }

    @Test
    void existingUserWrongRegistrationTests() {
        open("/register");

        $("[data-testid=register-login-input]").setValue("user1");
        $("[data-testid=register-password-input]").setValue("password1");
        $("[data-testid=confirm-password-input]").setValue("password1").pressEnter();

        $("[data-testid=register-error-message]").shouldHave(text("Username already taken"));
    }

    @Test
    void missingAllInputsRegistrationTests() {
        open("/register");

        $("[data-testid=register-login-input]").setValue("");
        $("[data-testid=register-password-input]").setValue("");
        $("[data-testid=confirm-password-input]").setValue("").pressEnter();

        $("[data-testid=register-error-message]").shouldHave(text("Login and password are required (minimum 3 and 6 characters)"));
    }

    @Test
    void notMatchPasswordRegistrationTests() {
        open("/register");

        $("[data-testid=register-login-input]").setValue("user1");
        $("[data-testid=register-password-input]").setValue("password1");
        $("[data-testid=confirm-password-input]").setValue("password2").pressEnter();

        $("[data-testid=register-error-message]").shouldHave(text("Passwords do not match"));
    }

    @Test
    void toLongPasswordRegistrationTests() {
        open("/register");

        $("[data-testid=register-login-input]").setValue("user1");
        $("[data-testid=register-password-input]").setValue("password1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111");
        $("[data-testid=confirm-password-input]").setValue("password1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111").pressEnter();

        $("[data-testid=register-error-message]").shouldHave(text("password размер должен находиться в диапазоне от 6 до 128"));
    }

}


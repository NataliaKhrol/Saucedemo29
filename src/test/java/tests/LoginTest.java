package tests;

import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import user.User;

import static enums.TitleNaming.PRODUCTS;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static user.UserFactory.withAdminPermission;
import static user.UserFactory.withLockedPermission;

@Epic("Блок онлайн оплаты")
@Feature("Оплата банк картой")
@Owner("Khr Na Vl nhk@sbe.ru")
public class LoginTest extends BaseTest {

    @Story("Ввод перс данных")
    @Severity(SeverityLevel.BLOCKER)
    @TmsLink("Saucedemo29")
    @Issue("Saus")

    @Test(description = "Проверка корректной авторизации", priority = 1)
    public void checkLogin() {
        System.out.println("LoginTest.checkLogin is running in Thread: "
                + Thread.currentThread().getId());

        loginPage
                .open()
                .login(withAdminPermission());

        assertEquals(productsPage.getTitle(), PRODUCTS.getDisplayName(),
                "Заголовок страницы не соответствует");
    }

    @DataProvider(name = "incorrectLoginData")
    public Object[][] loginData() {
        return new Object[][]{
                {new User("", "secret_sauce"), "Epic sadface: Username is required"},
                {new User("standard_user", ""), "Epic sadface: Password is required"},
                // {"Standard_user", "secret_sauce", "Epic sadface: Username and password do not match any user in this service"},
                {withLockedPermission(), "Epic sadface: Sorry, this user has been locked out."}
        };
    }

    @Story("Ввод перс данных")
    @Severity(SeverityLevel.BLOCKER)
    @TmsLink("Saucedemo29")
    @Issue("Saus")
    @Test(dataProvider = "incorrectLoginData", priority = 2,
            enabled = true)
    public void checkIncorrectLogin(User user, String errorMessage) {
        System.out.println("LoginTest.checkIncorrectLogin is running in Thread: "
                + Thread.currentThread().getId());
        loginPage.open();
        loginPage.login(user);

        assertTrue(loginPage.isErrorDisplayed());
        assertEquals(loginPage.getErrorText(), errorMessage);
    }
}

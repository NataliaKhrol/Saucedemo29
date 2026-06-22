package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTest extends BaseTest {

    @Test
    public void checkLogin() throws InterruptedException {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        Thread.sleep(5000);

        assertEquals(productsPage.getTitle(), "Products", "Заголовок страницы не соответствует");
    }

    @Test
    public void checkIncorrectLogin() {
        loginPage.open();
        loginPage.login("", "secret_sauce");

        assertTrue(loginPage.isErrorDisplayed());
        assertEquals(loginPage.getErrorText(), "Epic sadface: Username is required");
    }
}

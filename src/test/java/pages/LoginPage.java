package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import user.User;

public class LoginPage extends BasePage {
    private final By loginInput = By.xpath(DATA_TEST_PATTERN.formatted("username"));
    private final By passwordInput = By.xpath("//input[@placeholder='Password']");
    private final By submitButton = By.cssSelector("#login-button");
    private final By error = By.xpath(DATA_TEST_PATTERN.formatted("error"));

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открываем страницу авторизации")
    public LoginPage open() {
        driver.get(BASE_URL);

        return this;
    }

    public LoginPage open(String url) {
        driver.get(BASE_URL + url);

        return this;
    }

    @Step("Логинимся под кредами пользователя: = {user.name}, {user.password}")
    public LoginPage login(User user) {
        fillInLoginField(user.getName());
        fillInPasswordField(user.getPassword());
        driver.findElement(submitButton).click();

        return this;
    }

    @Step("Заполняем поле ввода логина")
    public void fillInLoginField(String login) {
        driver.findElement(loginInput).sendKeys(login);
    }

    @Step("Заполняем поле ввода пароля")
    public void fillInPasswordField(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }

    @Step("Проверяем отображение сообщения об ошибке")
    public boolean isErrorDisplayed() {
        return driver.findElement(error).isDisplayed();
    }

    @Step("Получаем текст сообщения об ошибке")
    public String getErrorText() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(error));
        return driver.findElement(error).getText();
    }

    public String checkButtonWidth() {
        return driver.findElement(submitButton).getCssValue("width");
    }
}

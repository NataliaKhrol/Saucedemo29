package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;
import org.testng.annotations.*;
import pages.LoginPage;
import pages.ProductsPage;

import java.time.Duration;

public class BaseTest {
    WebDriver driver;
    LoginPage loginPage;
    ProductsPage productsPage;

    @AfterMethod(alwaysRun = true)
    public void close() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");

        options.addArguments("guest");
        options.addArguments("headless");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
        // driver.manage().window().setSize(new Dimension(1920, 1080));

        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
    }
}

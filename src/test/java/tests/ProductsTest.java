package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ProductsTest extends BaseTest {
    @Test
    public void checkGoodsAdded() throws InterruptedException {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        assertEquals(productsPage.getTitle(), "Products", "Заголовок страницы не соответствует");

        productsPage.addGoodsToCart("Sauce Labs Bolt T-Shirt");
        productsPage.addGoodsToCart(2);
        Thread.sleep(5000);
        assertEquals(productsPage.checkCounterValue(), "rgba(226, 35, 26, 1)");
    }
}

package tests;

import org.testng.annotations.Test;

import java.util.List;

import static enums.TitleNaming.PRODUCTS;
import static org.testng.Assert.assertEquals;
import static user.UserFactory.withAdminPermission;

public class ProductsTest extends BaseTest {
    @Test
    public void checkGoodsAdded() {
        List<String> goodsList =
                List.of(
                        "Sauce Labs Bolt T-Shirt",
                        "Sauce Labs Bike Light",
                        "Test.allTheThings() T-Shirt (Red)");

        System.out.println("ProductsTest is running in Thread: "
                + Thread.currentThread().getId());
        loginPage.open();
        loginPage.login(withAdminPermission());

        assertEquals(productsPage.getTitle(), PRODUCTS.getDisplayName(), "Заголовок страницы не соответствует");
        productsPage.addGoodsToCart(4);

        for (String goods : goodsList) {
            productsPage.addGoodsToCart(goods);
        }

        assertEquals(productsPage.checkCounterColor(), "rgba(226, 35, 26, 1)");
        assertEquals(productsPage.checkCounterValue(), 4);
    }
}

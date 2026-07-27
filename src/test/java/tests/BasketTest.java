package tests;

import org.testng.annotations.Test;

import java.util.List;

import static enums.TitleNaming.PRODUCTS;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static user.UserFactory.withAdminPermission;

public class BasketTest extends BaseTest {
    @Test
    public void checkGoodsAdded() {
        List<String> goodsList =
                List.of(
                        "Sauce Labs Bolt T-Shirt",
                        "Sauce Labs Bike Light",
                        "Test.allTheThings() T-Shirt (Red)");

        System.out.println("BasketTest is running in Thread: "
                + Thread.currentThread().getId());
        loginPage.open();
        loginPage.login(withAdminPermission());

        assertEquals(productsPage.getTitle(), PRODUCTS.getDisplayName(), "Заголовок страницы не соответствует");

        for (String goods : goodsList) {
            productsPage.addGoodsToCart(goods);
        }

        productsPage.navigationPanel.switchToCart();
        assertTrue(basketPage.getProductsNames().equals(goodsList));
    }
}

package com.solvd.laba.testing.web;

import com.solvd.laba.web.ShoppingCartPage;
import com.solvd.laba.web.components.ShoppingCartItem;
import com.solvd.laba.web.OrderCompletionPage;
import com.solvd.laba.web.CheckoutDetailsPage;
import com.solvd.laba.web.OrderSummaryPage;
import com.solvd.laba.web.LoginPage;
import com.solvd.laba.web.ProductListingPage;
import com.solvd.laba.web.components.ProductCatalog;
import com.solvd.laba.web.components.MainNavigationMenu;
import com.solvd.laba.web.service.UserAuthenticationService;
import com.zebrunner.carina.core.AbstractTest;
import com.zebrunner.carina.utils.R;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class WebTest extends AbstractTest {

    /**
     * Confirming logout navigates back to the login interface.
     * Process:
     * 1. Execute a successful login.
     * 2. Initiate logout via the main navigation.
     * 3. Check for the login interface to affirm logout success.
     */
    @Test
    public void logoutTest() {
        UserAuthenticationService authService = new UserAuthenticationService();
        ProductListingPage productListingPage = authService.successfulLogin();
        LoginPage loginPage = productListingPage.getMainNavigationMenu().signOut();
        assertTrue(loginPage.isPageOpened(), "Expecting the login interface post-logout to confirm user has been logged out.");
    }

    /**
     * Examining the accuracy of login with valid user data.
     * Steps:
     * 1. Load the login interface.
     * 2. Submit valid credentials and confirm entry.
     * 3. Check for redirect to the product listings as login success evidence.
     */
    @Test
    public void loginTestWithValidCredentials() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();
        assertTrue(loginPage.isPageOpened(), "The login interface should be visible immediately upon navigation.");
        ProductListingPage productListingPage = loginPage.loginAndNavigate(R.TESTDATA.get("valid_user"), R.TESTDATA.get("valid_password"));
        assertTrue(productListingPage.isPageOpened(), "Redirection to product listings signals successful login.");
    }

    /**
     * Verifying the system's response to incorrect login attempts.
     * Strategy:
     * 1. Access the login screen.
     * 2. Enter and submit invalid login details.
     * 3. Validate the display of an error alert.
     */
    @Test
    public void loginTestWithInvalidCredentials() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();
        assertTrue(loginPage.isPageOpened(), "Login screen should be actively displayed.");
        loginPage.loginAndNavigate(R.TESTDATA.get("incorrect_user"), R.TESTDATA.get("incorrect_password"));
        assertTrue(loginPage.isErrorOutputVisible(), "Incorrect login attempts should trigger an error alert.");
    }

    /**
     * Sorting and verifying product order by ascending price.
     * Workflow:
     * 1. Authenticate with correct details.
     * 2. Transition to the product overview screen.
     * 3. Confirm product presentation pre-sort.
     * 4. Execute a high-to-low price sort.
     * 5. Confirm post-sort reordering reflects ascending price.
     */
    @Test
    public void testProductSorting() {
        // Log in and get to the product listing page
        UserAuthenticationService authService = new UserAuthenticationService();
        ProductListingPage productListingPage = authService.successfulLogin();
        ProductCatalog productCatalog = productListingPage.getProductCatalog();

        // Ensure products are displayed before sorting
        Assert.assertTrue(productCatalog.areProductsDisplayed(), "Products should be visibly listed before sort operation.");

        // Test sorting by PRICE_LOW_TO_HIGH and verify
        Assert.assertTrue(productCatalog.sortAndVerify(ProductCatalog.SortOrder.PRICE_LOW_TO_HIGH),
                "Products should be sorted in ascending order by price.");

        // Test sorting by PRICE_HIGH_TO_LOW and verify
        Assert.assertTrue(productCatalog.sortAndVerify(ProductCatalog.SortOrder.PRICE_HIGH_TO_LOW),
                "Products should be sorted in descending order by price.");

        // Test sorting by A_TO_Z and verify
        Assert.assertTrue(productCatalog.sortAndVerify(ProductCatalog.SortOrder.A_TO_Z),
                "Products should be sorted alphabetically from A to Z.");

        // Test sorting by Z_TO_A and verify
        Assert.assertTrue(productCatalog.sortAndVerify(ProductCatalog.SortOrder.Z_TO_A),
                "Products should be sorted alphabetically from Z to A.");
    }

    /**
     * Validating cart operations from addition to verification.
     * Protocol:
     * 1. Secure login and navigate to product listings.
     * 2. Ensure visibility and availability of 'Add to Cart' options.
     * 3. Sequentially add products to the cart.
     * 4. Verify the switch to 'Remove' buttons post-addition.
     * 5. Proceed to cart and match product count to confirm correct additions.
     */
    @Test
    public void addItemsToCartTest() {
        UserAuthenticationService authService = new UserAuthenticationService();
        ProductListingPage productListingPage = authService.successfulLogin();
        ProductCatalog productCatalog = productListingPage.getProductCatalog();
        assertTrue(productCatalog.areProductsDisplayed(), "All products should be on display before cart operations.");
        assertTrue(productCatalog.areAddToCartButtonsVisible(), "An 'Add to Cart' option is expected for each product.");
        productCatalog.addAllItemsToCart();
        assertTrue(productCatalog.areRemoveFromCartButtonsVisible(), "Post-addition, 'Remove' options should replace 'Add to Cart' buttons.");
        int totalProductsAdded = productCatalog.getProductList().size();
        MainNavigationMenu navigationMenu = productListingPage.getMainNavigationMenu();
        ShoppingCartPage cartPage = navigationMenu.navigateToShoppingCart();
        List<ShoppingCartItem> cartProducts = cartPage.getShoppingCartItems();
        assertEquals(totalProductsAdded, cartProducts.size(), "The cart should contain a product count equal to the number added.");
    }

    /**
     * Assessing the checkout process from cart to completion.
     * Steps:
     * 1. Login and fill the shopping cart.
     * 2. Initiate checkout from the cart.
     * 3. Input necessary details at the checkout screen and proceed.
     * 4. Finalize the purchase and verify order completion acknowledgment.
     */
    @Test
    public void checkoutTest() {
        UserAuthenticationService authService = new UserAuthenticationService();
        ProductListingPage productListingPage = authService.successfulLogin();
        ProductCatalog productCatalog = productListingPage.getProductCatalog();
        productCatalog.addAllItemsToCart();
        MainNavigationMenu navigationMenu = productListingPage.getMainNavigationMenu();
        ShoppingCartPage cartPage = navigationMenu.navigateToShoppingCart();
        CheckoutDetailsPage checkoutDetailsPage = cartPage.proceedToCheckout();
        OrderSummaryPage orderSummaryPage = checkoutDetailsPage.completeCheckoutProcess(
                R.TESTDATA.get("first_name"),
                R.TESTDATA.get("last_name"),
                R.TESTDATA.get("code")
        );
        OrderCompletionPage orderCompletionPage = orderSummaryPage.finalizeOrder();
        assertTrue(orderCompletionPage.isOrderCompletionDisplayed(), "A confirmation of order completion should be visibly presented.");
    }

}
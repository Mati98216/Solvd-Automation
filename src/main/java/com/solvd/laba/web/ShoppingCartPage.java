package com.solvd.laba.web;

import com.solvd.laba.web.components.ShoppingCartItem;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@Getter
public class ShoppingCartPage extends AbstractPage {

    @FindBy(className = "cart_item")
    private List<ShoppingCartItem> shoppingCartItems;
    @FindBy(id = "checkout")
    private ExtendedWebElement checkoutButton;

    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutDetailsPage proceedToCheckout() {
        checkoutButton.click();
        return new CheckoutDetailsPage(getDriver());
    }
}

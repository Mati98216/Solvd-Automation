package com.solvd.laba.web.components;

import com.solvd.laba.web.ShoppingCartPage;
import com.solvd.laba.web.LoginPage;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import lombok.Getter;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Getter
public class MainNavigationMenu extends AbstractUIObject {
    @FindBy(id = "react-burger-menu-btn")
    private ExtendedWebElement menuToggleButton;
    @FindBy(id = "shopping_cart_container")
    private ExtendedWebElement shoppingCartButton;
    @FindBy(id = "logout_sidebar_link")
    private ExtendedWebElement logoutLink;

    public MainNavigationMenu(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public ShoppingCartPage navigateToShoppingCart() {
        shoppingCartButton.click();
        return new ShoppingCartPage(getDriver());
    }

    public LoginPage signOut() {
        menuToggleButton.click();
        waitUntil(ExpectedConditions.visibilityOf(logoutLink), 5);
        logoutLink.click();
        return new LoginPage(getDriver());
    }
}

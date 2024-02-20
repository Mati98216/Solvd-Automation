package com.solvd.laba.web;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@Getter
public class OrderCompletionPage extends AbstractPage {
    @FindBy(id = "checkout_complete_container")
    private ExtendedWebElement checkoutCompletionIndicator;

    public OrderCompletionPage(WebDriver driver) {
        super(driver);
    }

    public boolean isOrderCompletionDisplayed() {
        return checkoutCompletionIndicator.isDisplayed();
    }
}
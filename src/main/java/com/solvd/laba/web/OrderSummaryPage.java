package com.solvd.laba.web;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@Getter
public class OrderSummaryPage extends AbstractPage {

    @FindBy(id = "finish")
    private ExtendedWebElement completeOrderButton;

    @FindBy(id = "checkout_complete_container")
    private ExtendedWebElement checkoutCompletionIndicator;

    public OrderSummaryPage(WebDriver driver) {
        super(driver);
    }


    public OrderSummaryPage finalizeOrder() {
        completeOrderButton.click();
        return this;
    }


    public boolean isOrderCompletionDisplayed() {
        return checkoutCompletionIndicator.isDisplayed();
    }
}

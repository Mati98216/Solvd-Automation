package com.solvd.laba.web;

import com.solvd.laba.web.OrderCompletionPage;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@Getter
public class OrderSummaryPage extends AbstractPage {

    @FindBy(id = "finish")
    private ExtendedWebElement completeOrderButton;

    public OrderSummaryPage(WebDriver driver) {
        super(driver);
    }

    public OrderCompletionPage finalizeOrder() {
        completeOrderButton.click();
        return new OrderCompletionPage(getDriver());
    }
}

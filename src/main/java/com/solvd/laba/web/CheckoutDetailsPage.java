package com.solvd.laba.web;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@Getter
public class CheckoutDetailsPage extends AbstractPage {

    @FindBy(id = "first-name")
    ExtendedWebElement firstNameField;
    @FindBy(id = "last-name")
    ExtendedWebElement lastNameField;
    @FindBy(id = "postal-code")
    ExtendedWebElement postalCodeField;
    @FindBy(id = "continue")
    ExtendedWebElement continueCheckoutButton;

    public CheckoutDetailsPage(WebDriver driver) {
        super(driver);
    }

    private void enterCheckoutDetails(String firstName, String lastName, String postalCode) {
        firstNameField.type(firstName);
        lastNameField.type(lastName);
        postalCodeField.type(postalCode);
    }

    public OrderSummaryPage completeCheckoutProcess(String firstName, String lastName, String postalCode) {
        enterCheckoutDetails(firstName, lastName, postalCode);
        continueCheckoutButton.click();
        return new OrderSummaryPage(getDriver());
    }
}

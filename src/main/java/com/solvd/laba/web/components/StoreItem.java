package com.solvd.laba.web.components;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import lombok.Getter;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@Getter
public class StoreItem extends AbstractUIObject {
    @FindBy(xpath = ".//*[@class = 'inventory_item_name ']")
    private ExtendedWebElement productNameText;
    @FindBy(xpath = ".//*[@class = 'inventory_item_price']")
    private ExtendedWebElement productPriceText;
    @FindBy(xpath = ".//*[@class = 'btn btn_primary btn_small btn_inventory ']")
    private ExtendedWebElement addToCartButton;
    @FindBy(xpath = ".//*[@class = 'btn btn_secondary btn_small btn_inventory ']")
    private ExtendedWebElement removeFromCartButton;

    public StoreItem(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public void addToCart() {
        addToCartButton.click();
    }

    public void removeFromCart() {
        removeFromCartButton.click();
    }

    public String getProductName() {
        return productNameText.getText();
    }

    public Double getPriceInDouble() {
        return Double.valueOf(productPriceText.getText().replace("$", ""));
    }

    public boolean isAddButtonDisplayed() {
        return addToCartButton.isDisplayed();
    }

    public boolean isRemoveButtonDisplayed() {
        return removeFromCartButton.isDisplayed();
    }
}
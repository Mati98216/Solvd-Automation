package com.solvd.laba.web.components;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import lombok.Getter;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@Getter
public class ShoppingCartItem extends AbstractUIObject {
    @FindBy(xpath = ".//*[@class = 'inventory_item_name']")
    private ExtendedWebElement itemNameElement;
    @FindBy(xpath = ".//*[@class = 'inventory_item_price']")
    private ExtendedWebElement itemPriceElement;
    public ShoppingCartItem(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public String getItemNameText() {
        return itemNameElement.getText();
    }
}

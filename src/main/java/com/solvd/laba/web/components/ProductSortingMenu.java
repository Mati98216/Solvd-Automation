package com.solvd.laba.web.components;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import lombok.Getter;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;


@Getter
public class ProductSortingMenu extends AbstractUIObject {

    @FindBy(xpath = ".//*[@class = 'product_sort_container']")
    private ExtendedWebElement productSortDropdown;

    public ProductSortingMenu(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public void sortProductsBy(String value) {
        Select select = new Select(productSortDropdown.getElement());
        select.selectByValue(value);
    }
}
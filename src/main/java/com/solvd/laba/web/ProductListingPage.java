package com.solvd.laba.web;

import com.solvd.laba.web.components.ProductCatalog;
import com.solvd.laba.web.components.MainNavigationMenu;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@Getter
public class ProductListingPage extends AbstractPage {

    @FindBy(xpath = "//*[@class = 'primary_header']")
    private MainNavigationMenu mainNavigationMenu;
    @FindBy(xpath = "//*[@class = 'inventory_container']")
    private ProductCatalog productCatalog;

    public ProductListingPage(WebDriver driver) {
        super(driver);
        setPageURL("inventory.html");
    }
}
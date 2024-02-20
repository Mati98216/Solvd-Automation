package com.solvd.laba.web.components;

import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import lombok.Getter;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ProductCatalog extends AbstractUIObject {

    @FindBy(className = "inventory_item")
    private List<StoreItem> productList;

    public ProductCatalog(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public boolean areProductsDisplayed() {
        return !productList.isEmpty();
    }

    public List<Double> getProductPrices() {
        return productList.stream()
                .map(StoreItem::getPriceInDouble)
                .collect(Collectors.toList());
    }

    public boolean isPriceSortedDescending(List<Double> prices) {
        for (int i = 0; i < prices.size() - 1; i++) {
            double currentPrice = prices.get(i);
            double nextPrice = prices.get(i + 1);
            if (currentPrice < nextPrice) {
                return false;
            }
        }
        return true;
    }

    public void addAllItemsToCart() {
        productList.forEach(StoreItem::addToCart);
    }

    public boolean checkAddButtonDisplay() {
        return productList.stream().allMatch(StoreItem::isAddButtonDisplayed);
    }

    public boolean checkRemoveButtonDisplay() {
        return productList.stream().allMatch(StoreItem::isRemoveButtonDisplayed);
    }
}
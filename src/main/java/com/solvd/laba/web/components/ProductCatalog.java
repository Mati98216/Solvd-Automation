package com.solvd.laba.web.components;

import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Comparator;
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

    public void addAllItemsToCart() {
        productList.forEach(StoreItem::addToCart);
    }

    public boolean areAddToCartButtonsVisible() {
        return productList.stream().allMatch(StoreItem::isAddButtonDisplayed);
    }

    public boolean areRemoveFromCartButtonsVisible() {
        return productList.stream().allMatch(StoreItem::isRemoveButtonDisplayed);
    }
    public enum SortOrder {
        A_TO_Z("Name (A to Z)", Comparator.comparing(StoreItem::getProductName)),
        Z_TO_A("Name (Z to A)", Comparator.comparing(StoreItem::getProductName).reversed()),
        PRICE_LOW_TO_HIGH("Price (low to high)", Comparator.comparing(StoreItem::getPriceInDouble)),
        PRICE_HIGH_TO_LOW("Price (high to low)", Comparator.comparing(StoreItem::getPriceInDouble).reversed());

        private final String name;
        @Getter
        private final Comparator<StoreItem> comparator;

        SortOrder(String name, Comparator<StoreItem> comparator) {
            this.name = name;
            this.comparator = comparator;
        }

    }
    public List<String> getProductNames() {
        List<WebElement> productElements = driver.findElements(By.className("inventory_item_name")); // Example locator
        List<String> productNames = new ArrayList<>();
        for (WebElement element : productElements) {
            productNames.add(element.getText());
        }
        return productNames;
    }
    public void performSortAction(SortOrder sortOrder) {
        WebElement sortDropdown = driver.findElement(By.className("product_sort_container"));
        Select select = new Select(sortDropdown);
        select.selectByVisibleText(sortOrder.name);
    }
    public boolean sortAndVerify(SortOrder sortOrder) {
        performSortAction(sortOrder);

        List<Double> sortedProductPrices = getProductPrices();


        switch (sortOrder) {
            case PRICE_LOW_TO_HIGH:
                return isSortedAscending(sortedProductPrices);
            case PRICE_HIGH_TO_LOW:
                return isSortedDescending(sortedProductPrices);
            case A_TO_Z:
                List<String> productNames = getProductNames();
                return isSortedAlphabetically(productNames);
            case Z_TO_A:
                productNames = getProductNames();
                return isSortedAlphabeticallyReversed(productNames);
            default:
                return false;
        }
    }

    public boolean isSortedAscending(List<Double> prices) {
        for (int i = 0; i < prices.size() - 1; i++) {
            if (prices.get(i) > prices.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

    public boolean isSortedDescending(List<Double> prices) {
        for (int i = 0; i < prices.size() - 1; i++) {
            if (prices.get(i) < prices.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

    public boolean isSortedAlphabetically(List<String> names) {
        for (int i = 0; i < names.size() - 1; i++) {
            if (names.get(i).compareTo(names.get(i + 1)) > 0) {
                return false;
            }
        }
        return true;
    }

    public boolean isSortedAlphabeticallyReversed(List<String> names) {
        for (int i = 0; i < names.size() - 1; i++) {
            if (names.get(i).compareTo(names.get(i + 1)) < 0) {
                return false;
            }
        }
        return true;
    }
}
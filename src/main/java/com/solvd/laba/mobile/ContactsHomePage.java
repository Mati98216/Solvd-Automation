package com.solvd.laba.mobile;

import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.decorator.PageOpeningStrategy;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

@Slf4j
@Getter
@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = ContactsHomePage.class)
public class ContactsHomePage extends AbstractPage {
    @FindBy(id = "com.google.android.contacts:id/open_search_bar_text_view")
    private ExtendedWebElement searchField;
    @FindBy(id = "com.google.android.contacts:id/floating_action_button")
    private ExtendedWebElement addButton;
    @FindBy(id="com.google.android.contacts:id/open_search_view_edit_text")
    private ExtendedWebElement search;
    @FindBy(id="android:id/list")
    private List<ExtendedWebElement> contactList;


    public ContactsHomePage(WebDriver driver) {
        super(driver);
        setPageOpeningStrategy(PageOpeningStrategy.BY_ELEMENT);
        setUiLoadedMarker(searchField);
    }
    public void clickAddButton() {
        addButton.click();
    }
    public void clickSearchField(String text) {
        searchField.click();
        search.type(text);

    }


    public AddContactPage selectContactByIndex(String  name) {
        String xpathExpression = String.format("//android.widget.TextView[@content-desc='%s']", name);

        WebElement contact = getDriver().findElement(By.xpath(xpathExpression));

        if (contact != null) {
            contact.click();
            return new AddContactPage(getDriver());
        } else {
            throw new IllegalArgumentException("No contact found at index: " + name);
        }
    }
    public boolean isContactAdded(String firstName, String lastName) {
        try {
            WebElement contact = driver.findElement(By.xpath("//android.widget.TextView[@content-desc='"+firstName+" "+lastName+"']"));
            return contact != null;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    public boolean isContactPresent(String name) {
        try {
            WebElement contact = driver.findElement(By.xpath("//android.widget.TextView[@content-desc='" + name + "']"));
            return contact != null;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isInContactDetailsPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement editContactButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.google.android.contacts:id/menu_insert_or_edit")));
            return editContactButton != null;
        } catch (TimeoutException e) {
            return false;
        }
    }
}


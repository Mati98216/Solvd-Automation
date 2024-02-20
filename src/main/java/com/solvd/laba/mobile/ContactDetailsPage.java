package com.solvd.laba.mobile;


import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.decorator.PageOpeningStrategy;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import lombok.Getter;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Getter
@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = ContactDetailsPage.class)
public class ContactDetailsPage extends AbstractPage {
    @FindBy(id="com.google.android.contacts:id/menu_insert_or_edit")
    private ExtendedWebElement editContact;
    @FindBy(xpath = "//android.widget.ImageView[@index='2']")
    private ExtendedWebElement settings;
    @FindBy(id="com.google.android.contacts:id/verb_call")
    private  ExtendedWebElement call;
    @FindBy(id="com.google.android.contacts:id/verb_text")
    private ExtendedWebElement text;
    @FindBy (id="com.google.android.contacts:id/verb_video")
    private  ExtendedWebElement video;
    @FindBy (id="com.google.android.contacts:id/verb_email")
    private ExtendedWebElement email;
    @FindBy (id="com.google.android.contacts:id/menu_star")
    private ExtendedWebElement addToFavorites;
    @FindBy(id="com.google.android.contacts:id/title")
    private ExtendedWebElement delete;
    @FindBy(id="android:id/button1")
    private ExtendedWebElement confirmDelete;
    @FindBy(xpath = "//android.widget.Button[@content-desc='Remove from favorites']")
    private ExtendedWebElement removeFavorite;
    public ContactDetailsPage(WebDriver driver) {
        super(driver);
        setPageOpeningStrategy(PageOpeningStrategy.BY_ELEMENT);
        setUiLoadedMarker(editContact);
    }
    public void clickSettings() {
        settings.click();
    }

    public void clickDelete() {
        delete.click();
    }

    public void confirmDelete() {
        confirmDelete.click();
    }
    public boolean isContactInFavorites() {
        return removeFavorite.isElementPresent();
    }
    public void clickCall() {
        call.click();
    }


    public void clickAddToFavorites() {
        addToFavorites.click();
    }
    public boolean isCallInitiated() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement callScreenElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.google.android.dialer:id/icon_view")));
            return callScreenElement != null;
        } catch (TimeoutException e) {
            return false;
        }
    }
}

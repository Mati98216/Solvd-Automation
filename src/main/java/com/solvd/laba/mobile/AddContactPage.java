package com.solvd.laba.mobile;

import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.utils.mobile.IMobileUtils;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.decorator.PageOpeningStrategy;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = AddContactPage.class)
public class AddContactPage extends AbstractPage implements IMobileUtils {

   @FindBy(xpath = "//android.widget.EditText[@text='First name']")
    private ExtendedWebElement firstNameField;
    @FindBy(xpath = "//android.widget.EditText[@text='Last name']")
    private ExtendedWebElement lastNameField;

    @FindBy(xpath = "//android.widget.EditText[@text='Phone']")
    private ExtendedWebElement phoneField;

    @FindBy(xpath = "//android.widget.EditText[@text='Email']")
    private ExtendedWebElement emailField;
    @FindBy(id = "com.google.android.contacts:id/toolbar_button")
    private ExtendedWebElement saveButton;

    public AddContactPage(WebDriver driver) {
        super(driver);
        setPageOpeningStrategy(PageOpeningStrategy.BY_ELEMENT);
        setUiLoadedMarker(saveButton);
    }


    public void createContact(String firstName, String lastName, String phone, String email) {
        focusAndFillField(firstNameField, firstName);
        focusAndFillField(lastNameField, lastName);
        focusAndFillField(phoneField, phone);
        swipe(emailField);
        focusAndFillField(emailField, email);
        saveButton.click();
    }

    private void focusAndFillField(ExtendedWebElement element, String text) {
        element.click();
        element.type(text);
    }
}
package com.solvd.laba.web;


import com.solvd.laba.web.ProductListingPage;
import com.zebrunner.carina.utils.R;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@Getter
public class LoginPage extends AbstractPage {

    @FindBy(id = "user-name")
    private ExtendedWebElement usernameField;
    @FindBy(id = "password")
    private ExtendedWebElement passwordField;
    @FindBy(id = "login-button")
    private ExtendedWebElement loginButton;
    @FindBy(xpath = "//*[@id= 'login_button_container']/div/form/div[3]")
    private ExtendedWebElement loginErrorNotification;

    public LoginPage(WebDriver driver) {
        super(driver);
        setPageURL("");
    }

    private void typeUserLogin(String login) {
        usernameField.type(login);
    }

    private void typeUserPassword(String password) {
        passwordField.type(password);
    }

    public void submitLogin() {
        loginButton.click();
    }

    public boolean isErrorOutputVisible() {
        return loginErrorNotification.isVisible();
    }



    public ProductListingPage loginAndNavigate(String login, String password) {
        typeUserLogin(login);
        typeUserPassword(password);
        submitLogin();
        return new ProductListingPage(getDriver());
    }
}

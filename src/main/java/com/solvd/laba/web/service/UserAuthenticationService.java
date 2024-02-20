package com.solvd.laba.web.service;

import com.solvd.laba.web.LoginPage;
import com.solvd.laba.web.ProductListingPage;
import com.zebrunner.carina.utils.R;
import com.zebrunner.carina.webdriver.IDriverPool;

public class UserAuthenticationService implements IDriverPool {

    private final LoginPage loginPage;

    public UserAuthenticationService() {
        this.loginPage = new LoginPage(getDriver());
    }

    public ProductListingPage successfulLogin() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();
        return loginPage.loginAndNavigate(R.TESTDATA.get("valid_user"), R.TESTDATA.get("valid_password"));
    }
}
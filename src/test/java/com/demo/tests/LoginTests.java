package com.demo.tests;

import com.demo.models.User;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        if(!app.getUser().isLoginLinkPresent()){
            app.getUser().clickLogoutButton();
        }

    }

    @Test
    public void loginPositiveTest() {
        app.getUser().clickLoginLink();
        app.getUser().fillLoginForm(new User().setEmail("kl5@gmail.com").setPassword("Aa12345!"));
        app.getUser().clickLoginButton();
        Assert.assertTrue(app.getUser().isElementPresent(By.cssSelector("[href='/logout']")));
    }

    @Test
    public void loginNonExistingUserNegativeTest() {
        app.getUser().clickLoginLink();
        app.getUser().fillLoginForm(new User().setEmail("kl00@gmail.com").setPassword("Aa12345!"));
        app.getUser().clickLoginButton();
        Assert.assertTrue(app.getUser().isLoginErrorMessagePresent());
    }

    @Test
    public void loginWrongPasswordNegativeTest() {
        app.getUser().clickLoginLink();
        app.getUser().fillLoginForm(new User().setEmail("kl5@gmail.com").setPassword("Aa12345!5"));
        app.getUser().clickLoginButton();
        Assert.assertTrue(app.getUser().isLoginErrorMessagePresent());
    }



}

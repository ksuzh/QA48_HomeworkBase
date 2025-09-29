package com.demo.tests;

import com.demo.data.UserData;
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
        app.getUser().fillLoginForm(new User().setEmail(UserData.EMAIL).setPassword(UserData.PASSWORD));
        app.getUser().clickLoginButton();
        Assert.assertTrue(app.getUser().isLogoutLinkPresent());
    }

    @Test
    public void loginNonExistingUserNegativeTest() {
        app.getUser().clickLoginLink();
        app.getUser().fillLoginForm(new User().setEmail("kl00@gmail.com").setPassword(UserData.PASSWORD));
        app.getUser().clickLoginButton();
        Assert.assertTrue(app.getUser().isLoginErrorMessagePresent());
    }

    @Test
    public void loginWrongPasswordNegativeTest() {
        app.getUser().clickLoginLink();
        app.getUser().fillLoginForm(new User().setEmail(UserData.EMAIL).setPassword(UserData.PASSWORD + "5"));
        app.getUser().clickLoginButton();
        Assert.assertTrue(app.getUser().isLoginErrorMessagePresent());
    }



}

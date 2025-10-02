package com.demo.tests;

import com.demo.data.UserData;
import com.demo.models.User;
import com.demo.utils.DataProviders;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RegistrationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if(!app.getUser().isRegisterLinkPresent()){
            app.getUser().clickLogoutButton();
        }

    }

    @Test
    public void positiveRegistrationTests() {
        int i = (int) (System.currentTimeMillis() / 1000) % 3600;
        app.getUser().clickRegisterLink();
        app.getUser().clickRadioBtnF();
        app.getUser().fillInRegisterForm(new User().setFirstName(UserData.FIRSTNAME).setLastName(UserData.LASTNAME).
                setEmail(UserData.EMAIL.replace("@", "+" + i + "@")).setPassword(UserData.PASSWORD).
                setConfirmPassword(UserData.CONFIRMPASSWORD));
        app.getUser().clickRegisterButton();
        Assert.assertTrue(app.getUser().isSuccessfulRegistrationMsgPresent());
//        Assert.assertTrue(app.getUser().isElementPresent(By.xpath("//input[@class='button-1 register-continue-button']")));

    }


    @Test(dataProvider = "validRegistration", dataProviderClass = DataProviders.class)
    public void positiveRegistrationDataProviderFromScvTests(User user) {
        app.getUser().clickRegisterLink();
        app.getUser().clickRadioBtnF();
        app.getUser().fillInRegisterForm(user);
        app.getUser().clickRegisterButton();
        Assert.assertTrue(app.getUser().isSuccessfulRegistrationMsgPresent());
    }

    @Test(dataProvider = "inValidRegistration", dataProviderClass = DataProviders.class)
    public void negativeRegistrationDataProviderFromScvTests(User user, String errorField) {
        app.getUser().clickRegisterLink();
        app.getUser().clickRadioBtnF();
        app.getUser().fillInRegisterForm(user);
        app.getUser().clickRegisterButton();

        String errorMsg = app.getUser().getErrorMessageForField(errorField);
        Assert.assertFalse(errorMsg.isEmpty());
    }
    

    @Test
    public void emptyNameNegativeRegistrationTest() {
        int i = (int) (System.currentTimeMillis() / 1000) % 3600;
        app.getUser().clickRegisterLink();
        app.getUser().fillInRegisterForm(new User().setLastName(UserData.LASTNAME).
                setEmail(UserData.EMAIL.replace("@", "+" + "@")).setPassword(UserData.PASSWORD).
                setConfirmPassword(UserData.CONFIRMPASSWORD));
        app.getUser().clickRegisterButton();
        Assert.assertTrue(app.getUser().isErrorMessageFirstNamePresent());
    }

    @Test
    public void invalidEmailNegativeRegistrationTest() {
        app.getUser().clickRegisterLink();
        app.getUser().fillInRegisterForm(new User().setFirstName(UserData.FIRSTNAME).setLastName(UserData.LASTNAME).
                setEmail(UserData.EMAIL.replace("@", "")).setPassword(UserData.PASSWORD).
                setConfirmPassword(UserData.CONFIRMPASSWORD));
        app.getUser().clickRegisterButton();
        Assert.assertTrue(app.getUser().isErrorMessageInvalidEmailPresent());
    }


}

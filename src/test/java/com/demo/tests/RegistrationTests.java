package com.demo.tests;

import com.demo.models.User;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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
        app.getUser().fillInRegisterForm(new User().setFirstName("Ksen1").setLastName("Kseg").
                setEmail("kl5" + i + "@gmail.com").setPassword("Aa12345!").
                setConfirmPassword("Aa12345!"));
        app.getUser().clickRegisterButton();
        Assert.assertTrue(app.getUser().isElementPresent(By.xpath("//div[.='\n" +
                "            Your registration completed\n" +
                "        ']")));
        Assert.assertTrue(app.getUser().isElementPresent(By.xpath("//input[@class='button-1 register-continue-button']")));

    }

    @Test
    public void emptyNameNegativeRegistrationTest() {
        int i = (int) (System.currentTimeMillis() / 1000) % 3600;
        app.getUser().clickRegisterLink();
        app.getUser().fillInRegisterForm(new User().setLastName("Kseg").
                setEmail("kl5" + i + "@gmail.com").setPassword("Aa12345!").
                setConfirmPassword("Aa12345!"));
        app.getUser().clickRegisterButton();
        Assert.assertTrue(app.getUser().isErrorMessageFirstNamePresent());
    }

    @Test
    public void invalidEmailNegativeRegistrationTest() {
        app.getUser().clickRegisterLink();
        app.getUser().fillInRegisterForm(new User().setLastName("Kseg").
                setEmail("kl5gmail.com").setPassword("Aa12345!").
                setConfirmPassword("Aa12345!"));
        app.getUser().clickRegisterButton();
        Assert.assertTrue(app.getUser().isErrorMessageInvalidEmailEmailPresent());
    }


}

package com.demo.tests;

import com.demo.models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DeleteItemTests extends TestBase {
    @BeforeMethod
    public void preCondition() {
        if(!app.getUser().isLoginLinkPresent()){
            app.getUser().clickLogoutButton();
        }

        app.getUser().clickLoginLink();
        app.getUser().fillLoginForm(new User().setEmail("kl5@gmail.com").setPassword("Aa12345!"));
        app.getUser().clickLoginButton();

        app.getItem().clickBooksLink();
        app.getItem().addItem(2);
        app.getItem().addItem(1);
        app.getItem().addItem(3);
        app.getItem().clickCartLink();
    }

    @Test
    public void deleteItemPositiveTests() {
        int sizeBefore = app.getItem().cartSize();
        System.out.println(sizeBefore);
        app.getItem().deleteItem("Fiction");

        int sizeAfter = app.getItem().cartSize();
        Assert.assertEquals(sizeAfter, sizeBefore - 1);
    }


}

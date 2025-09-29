package com.demo.tests;

import com.demo.data.UserData;
import com.demo.models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddItemTest extends TestBase {
    @BeforeMethod
    public void preConditionLogin() {
        if(!app.getUser().isLoginLinkPresent()){
            app.getUser().clickLogoutButton();
        }

        app.getUser().clickLoginLink();
        app.getUser().fillLoginForm(new User().setEmail(UserData.EMAIL).setPassword(UserData.PASSWORD));
        app.getUser().clickLoginButton();
    }

    @Test
    public void testAddItemPositive() {
        app.getItem().clickBooksLink();
        //click(By.xpath("//input[@class='button-2 product-box-add-to-cart-button'][1]"));
        //click(By.xpath("(//input[@value='Add to cart'])[1]"));
        app.getItem().addItem(1);
        app.getItem().addItem(3);
        Assert.assertTrue(app.getItem().isItemAdded(app.getItem().addItem(1)));


    }

}

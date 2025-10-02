package com.demo.fw;

import com.demo.models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserHelper extends BaseHelper {

    public UserHelper(WebDriver driver) {
        super(driver);
    }

    public void clickRegisterButton() {
        click(By.id("register-button"));
    }

    public void fillInRegisterForm(User user) {
        type(By.id("FirstName"), user.getFirstName());
        type(By.id("LastName"), user.getLastName());
        type(By.id("Email"), user.getEmail());
        type(By.id("Password"), user.getPassword());
        type(By.id("ConfirmPassword"), user.getConfirmPassword());
    }

    public void clickRadioBtnF() {
        click(By.id("gender-female"));
    }

    public void clickRegisterLink() {
        click(By.cssSelector("[href='/register']"));
    }

    public void clickLoginButton() {
        click(By.xpath("//input[@value='Log in']"));
    }

    public void fillLoginForm(User user) {
        type(By.id("Email"), user.getEmail());
        type(By.id("Password"), user.getPassword());
    }

    public void clickLoginLink() {
        click(By.cssSelector("[href='/login']"));
    }

    public void clickLogoutButton() {
        click(By.cssSelector("[href='/logout']"));
    }

    public boolean isRegisterLinkPresent() {
        return isElementPresent(By.cssSelector("[href='/register']"));
    }

    public boolean isLoginLinkPresent() {
        return isElementPresent(By.cssSelector("[href='/login']"));
    }

    public boolean isSuccessfulRegistrationMsgPresent() {
        return isElementPresent(By.xpath("//div[.='\n" +
                "            Your registration completed\n" +
                "        ']"));
    }

    public boolean isErrorMessageFirstNamePresent() {
        return isElementPresent(By.xpath("//span[@class='field-validation-error']//span[@for='FirstName']"));

    }

    public boolean isErrorMessageInvalidEmailPresent() {
        return isElementPresent(By.xpath("//span[@class='field-validation-error']//span[@for='Email']"));
    }

    public boolean isLoginErrorMessagePresent() {
        return isElementPresent(By.xpath("//div[@class='validation-summary-errors']"));
    }

    public boolean isLogoutLinkPresent() {
        return isElementPresent(By.cssSelector("[href='/logout']"));
    }

    public String getErrorMessageForField(String errorField) {
        return driver.findElement(By.xpath("//span[@class='field-validation-error' " +
                "                   and @data-valmsg-for='" + errorField + "']")).getText();

    }
}

package com.demo.fw;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ItemHelper extends BaseHelper {
    public ItemHelper(WebDriver driver) {
            super(driver);
    }

    public int cartSize() {
        if (isElementPresent(By.cssSelector(".product-name"))) {
            return driver.findElements(By.cssSelector(".product-name")).size();
        }
        return 0;
    }

    public void clickBooksLink() {
        click(By.cssSelector("[href='/books']"));
    }

    public String addItem(int ind) {
        click(By.xpath("(//input[@value='Add to cart'])["+ ind +"]"));
        String itemName = driver.findElement(
                By.xpath("//input[@value='Add to cart']" +
                        "/ancestor::div[@class='item-box']//h2[@class='product-title']/a")
        ).getText();
        pause(2000);
        return itemName;
    }

    public boolean isItemAdded(String itemName) {
        clickCartLink();
        List<WebElement> items = driver.findElements(By.cssSelector(".product-name"));
        for (WebElement item : items) {
            if (item.getText().contains(itemName)) {
                return true;
            }
        }
        return false;
    }

    public void clickCartLink() {
        click(By.cssSelector("[href='/cart']"));
    }

    public int itemQuantity(String itemName) {
        List<WebElement> items = driver.findElements(By.xpath("//a[@class='product-name']"));
        for(WebElement item : items) {
            if (item.getText().contains(itemName)) {
                WebElement itemRow = item.findElement(By.xpath("./ancestor::tr[@class='cart-item-row']"));
                WebElement quantityEl = itemRow.findElement(By.xpath(".//input[@class='qty-input']"));
                int quantity = Integer.parseInt(quantityEl.getAttribute("value"));
                return quantity;
            }
        }
        return 0;
    }

    public void deleteItem(String itemName) {
        List<WebElement> items = driver.findElements(By.xpath("//a[@class='product-name']"));
        for(WebElement item : items) {
            if (item.getText().contains(itemName)) {
                WebElement itemRow = item.findElement(By.xpath("./ancestor::tr[@class='cart-item-row']"));
//                WebElement quantityEl = itemRow.findElement(By.xpath(".//input[@class='qty-input']"));
//                int quantity = Integer.parseInt(quantityEl.getAttribute("value"));
                clickRemoveFromCartCheckbox(itemRow);
                click(By.name("updatecart"));
                pause(2000);

            }
        }

    }

    private void clickRemoveFromCartCheckbox(WebElement itemRow) {
        clickInElement(itemRow, By.xpath(".//input[@name='removefromcart']"));
    }
}

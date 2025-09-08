import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.List;

public class AddItemTest extends TestBase {
    @Test
    public void testAddItemPositive() {
        click(By.cssSelector("[href='/books']"));
        //click(By.xpath("//input[@class='button-2 product-box-add-to-cart-button'][1]"));
        click(By.xpath("(//input[@value='Add to cart'])[2]"));
        click(By.xpath("(//input[@value='Add to cart'])[1]"));

        Assert.assertTrue(isItemAdded("Fiction"));

    }

    public boolean isItemAdded(String itemName) {
        click(By.cssSelector("[href='/cart']"));
        List<WebElement> items = driver.findElements(By.cssSelector(".product-name"));
        for (WebElement item : items) {
            if (item.getText().contains(itemName)) {
                return true;
            }
        }
        return false;
    }

    @AfterMethod
    public void deleteAddedItem() {
        click(By.name("removefromcart"));
        click(By.name("updatecart"));
    }

}

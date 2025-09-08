import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DeleteItemTests extends TestBase {
    @BeforeMethod
    public void preCondition() {
        click(By.cssSelector("[href='/books']"));
        click(By.xpath("(//input[@value='Add to cart'])[2]"));
        click(By.xpath("(//input[@value='Add to cart'])[1]"));
        click(By.cssSelector("[href='/cart']"));
    }

    @Test
    public void deleteItem() {
        int sizeBefore = cartSize();
        click(By.name("removefromcart"));
        click(By.name("updatecart"));
        pause(1000);
        int sizeAfter = cartSize();
        Assert.assertEquals(sizeAfter, sizeBefore - 1);
    }

    private int cartSize() {
        if (isElementPresent(By.cssSelector(".product-name"))) {
            return driver.findElements(By.cssSelector(".product-name")).size();
        }
        return 0;
    }

    public void pause(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

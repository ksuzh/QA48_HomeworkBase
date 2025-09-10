import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddItemTest extends TestBase {
    @BeforeMethod
    public void preConditionLogin() {
        clickLoginLink();
        fillLoginForm();
        clickLoginButton();
    }

    @Test
    public void testAddItemPositive() {
        clickBooksLink();
        //click(By.xpath("//input[@class='button-2 product-box-add-to-cart-button'][1]"));
        //click(By.xpath("(//input[@value='Add to cart'])[1]"));
        addItem(1);
        addItem(3);
        Assert.assertTrue(isItemAdded(addItem(1)));


    }

}

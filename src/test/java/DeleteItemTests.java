import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DeleteItemTests extends TestBase {
    @BeforeMethod
    public void preCondition() {
        clickLoginLink();
        fillLoginForm();
        clickLoginButton();

        clickBooksLink();
        addItem(2);
        addItem(1);
        addItem(3);
        clickCartLink();
    }

    @Test
    public void deleteItemPositiveTests() {
        int sizeBefore = cartSize();
        deleteItem("Health Book");

        int sizeAfter = cartSize();
        Assert.assertEquals(sizeAfter, sizeBefore - 1);
    }


}

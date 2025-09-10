import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {
    @Test
    public void testLogin() {
        clickLoginLink();
        fillLoginForm();
        clickLoginButton();
        Assert.assertTrue(isElementPresent(By.cssSelector("[href='/logout']")));
    }

}

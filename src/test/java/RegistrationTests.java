import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegistrationTests extends TestBase {

    @Test
    public void positiveRegistrationTests() {
        int i = (int) (System.currentTimeMillis() / 1000) % 3600;
        clickRegisterLink();
        clickRadioBtnF();
        fillInRegisterForm(i);
        clickRegisterButton();
        Assert.assertTrue(isElementPresent(By.xpath("//div[.='\n" +
                "            Your registration completed\n" +
                "        ']")));
        Assert.assertTrue(isElementPresent(By.xpath("//input[@class='button-1 register-continue-button']")));


    }

}

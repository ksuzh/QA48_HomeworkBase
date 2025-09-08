import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegistrationTests extends TestBase {

    @Test
    public void positiveRegistrationTests() {
        int i = (int) (System.currentTimeMillis() / 1000) % 3600;
        click(By.cssSelector("[href='/register']"));

        click(By.id("gender-female"));
        type(By.id("FirstName"), "Ksen1");
        type(By.id("LastName"), "Kseg");
        type(By.id("Email"), "kl5" + i + "@gmail.com");
        type(By.id("Password"), "Aa12345!");
        type(By.id("ConfirmPassword"), "Aa12345!");

        click(By.id("register-button"));

        Assert.assertTrue(isElementPresent(By.xpath("//div[.='\n" +
                "            Your registration completed\n" +
                "        ']")));
        Assert.assertTrue(isElementPresent(By.xpath("//input[@class='button-1 register-continue-button']")));


    }
}

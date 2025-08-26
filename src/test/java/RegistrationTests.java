import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegistrationTests extends TestBase {

    @Test
    public void positiveRegistrationTests() {
        click(By.cssSelector("[href='/register']"));
        click(By.id("gender-female"));

        click(By.id("FirstName"));
        type(By.id("FirstName"), "Ksen1");

        click(By.id("LastName"));
        type(By.id("LastName"), "Kseg");

        click(By.id("Email"));
        type(By.id("Email"), "kl5@gmail.com");

        click(By.id("Password"));
        type(By.id("Password"), "Aa12345!");
        click(By.id("ConfirmPassword"));
        type(By.id("ConfirmPassword"), "Aa12345!");

        click(By.id("register-button"));

        Assert.assertTrue(isElementPresent(By.xpath("//div[.='\n" +
                "            Your registration completed\n" +
                "        ']")));
        Assert.assertTrue(isElementPresent(By.xpath("//input[@class='button-1 register-continue-button']")));


    }
}

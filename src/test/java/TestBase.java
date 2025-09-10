import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;
import java.util.List;

public class TestBase {
    WebDriver driver;

    @BeforeMethod
    public void setUp() throws Exception {
        driver = new ChromeDriver();
        driver.navigate().to("https://demowebshop.tricentis.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterMethod(enabled = false)
    public void tearDown() {
        driver.quit();
    }

    public boolean isElementPresent(By locator) {
        return driver.findElements(locator).size() > 0;
    }

    public void click(By locator) {
        driver.findElement(locator).click();
    }

    public void type(By locator, String text) {
        click(locator);
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(text);
    }

    protected int cartSize() {
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

    public void clickRegisterButton() {
        click(By.id("register-button"));
    }

    public void fillInRegisterForm(int i) {
        type(By.id("FirstName"), "Ksen1");
        type(By.id("LastName"), "Kseg");
        type(By.id("Email"), "kl5" + i + "@gmail.com");
        type(By.id("Password"), "Aa12345!");
        type(By.id("ConfirmPassword"), "Aa12345!");
    }

    public void clickRadioBtnF() {
        click(By.id("gender-female"));
    }

    public void clickRegisterLink() {
        click(By.cssSelector("[href='/register']"));
    }

    protected void clickLoginButton() {
        click(By.xpath("//input[@value='Log in']"));
    }

    public void fillLoginForm() {
        type(By.id("Email"), "kl5@gmail.com");
        type(By.id("Password"), "Aa12345!");
    }

    public void clickLoginLink() {
        click(By.cssSelector("[href='/login']"));
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

    public void deleteItem(String itemName) {
        List<WebElement> items = driver.findElements(By.xpath("//a[@class='product-name']"));
        for(WebElement item : items) {
            if (item.getText().contains(itemName)) {
                click(By.name("removefromcart"));
                click(By.name("updatecart"));
                pause(2000);
            }
        }

    }
}

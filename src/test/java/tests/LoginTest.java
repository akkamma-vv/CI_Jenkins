package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTest {

        WebDriver driver;

        @BeforeMethod
        public void setup() {

            WebDriverManager.chromedriver().setup();

            driver = new ChromeDriver();

            driver.manage().window().maximize();

            driver.get("https://the-internet.herokuapp.com/login");
        }

        @Test
        public void validLoginTest() {

            driver.findElement(By.id("username")).sendKeys("tomsmith");

            driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");

            driver.findElement(By.cssSelector("button[type='submit']")).click();

            String message = driver.findElement(By.id("flash")).getText();

            Assert.assertTrue(message.contains("You logged into a secure area!"));
        }

        @Test
        public void invalidLoginTest() {

            driver.findElement(By.id("username")).sendKeys("wrong");

            driver.findElement(By.id("password")).sendKeys("wrong");

            driver.findElement(By.cssSelector("button[type='submit']")).click();

            String message = driver.findElement(By.id("flash")).getText();

            Assert.assertTrue(message.contains("Your username is invalid!"));
        }

        @Test
        public void emptyFieldsTest() {

            driver.findElement(By.cssSelector("button[type='submit']")).click();

            String message = driver.findElement(By.id("flash")).getText();

            Assert.assertTrue(message.contains("Your username is invalid!"));
        }

        @AfterMethod
        public void tearDown() {

            driver.quit();
        }
}

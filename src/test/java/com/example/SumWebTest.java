package com.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File; // ✅ Added missing import
import static org.junit.Assert.assertEquals;

public class SumWebTest {

    private WebDriver driver;

    @Before
    public void setUp() {
        // Set path to chromedriver if needed
        // Example: System.setProperty("webdriver.chrome.driver", "path/to/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void testSumCalculation() throws Exception {
        // ✅ Example URL — replace with your actual one
        driver.get("https://github.com/ShivammmKshirsagar/SeleniumWebTest/blob/main/src/test/resources/sum.html");

        // Find input fields and button
        WebElement num1Field = driver.findElement(By.id("num1"));
        WebElement num2Field = driver.findElement(By.id("num2"));
        WebElement sumButton = driver.findElement(By.id("calculate"));
        WebElement resultField = driver.findElement(By.id("result"));

        // Input numbers
        num1Field.sendKeys("5");
        num2Field.sendKeys("7");
        sumButton.click();

        // Wait a bit for the result to update (simple delay or use WebDriverWait)
        Thread.sleep(1000);

        // Verify result
        String result = resultField.getText();
        assertEquals("12", result);

        // ✅ Example use of File class (to ensure File is needed)
        File screenshotDir = new File("screenshots");
        if (!screenshotDir.exists()) {
            screenshotDir.mkdir();
        }
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

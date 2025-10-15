package com.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class SumWebTest {

    private WebDriver driver;

    @Before
    public void setUp() {
        // ✅ Chrome options for headless mode + local file access
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new"); // Headless mode for Jenkins
        options.addArguments("--allow-file-access-from-files"); // Allow local file access
        options.addArguments("--disable-gpu"); // Optional, but recommended for headless

        // Initialize ChromeDriver with options
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @Test
    public void testSumOfTwoNumbers() throws InterruptedException {
        // ✅ Use the correct file URL for your HTML in Jenkins workspace
        String url = "file:///C:/ProgramData/Jenkins/.jenkins/workspace/SeleniumWebSumTest/src/test/resources/sum.html";
        driver.get(url);

        // Explicit wait until the first input is present
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("num1")));

        // Locate elements
        WebElement num1 = driver.findElement(By.id("num1"));
        WebElement num2 = driver.findElement(By.id("num2"));
        WebElement calcButton = driver.findElement(By.id("calcBtn"));
        WebElement result = driver.findElement(By.id("result"));

        // Input numbers
        num1.sendKeys("10");
        num2.sendKeys("20");

        // Click calculate
        calcButton.click();

        // Wait for JS to update the result
        Thread.sleep(1000);

        // Verify result
        String output = result.getText().trim();
        System.out.println("Result displayed: " + output);
        assertEquals("Sum = 30", output);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

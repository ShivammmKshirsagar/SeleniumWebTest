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
    private WebDriverWait wait; // Reusable wait object
    
    // ✅ Define URL once
    private final String url = "file:///C:/ProgramData/Jenkins/.jenkins/workspace/SeleniumWebSumTest/src/test/resources/sum.html";


    @Before
    public void setUp() {
        // ✅ Chrome options for headless mode + local file access
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new"); // Headless mode for Jenkins
        options.addArguments("--allow-file-access-from-files"); // Allow local file access
        options.addArguments("--disable-gpu"); // Optional, but recommended for headless
        options.addArguments("--no-sandbox"); // Recommended for CI environments
        options.addArguments("--disable-dev-shm-usage"); // Recommended for CI environments

        // Initialize ChromeDriver with options
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        
        // ✅ Initialize wait object once
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @Test
    public void testSumOfTwoNumbers() {
        driver.get(url);

        // Explicit wait until the first input is present
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

        // ✅ Replaced Thread.sleep with a more reliable explicit wait
        wait.until(ExpectedConditions.textToBePresentInElement(result, "Sum = 30"));

        // Verify result
        String output = result.getText().trim();
        System.out.println("Result (Positive): " + output);
        assertEquals("Sum = 30", output);
    }

    // --- START: Added Test Cases ---

    @Test
    public void testSumOfNegativeNumbers() {
        driver.get(url);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("num1")));

        WebElement num1 = driver.findElement(By.id("num1"));
        WebElement num2 = driver.findElement(By.id("num2"));
        WebElement calcButton = driver.findElement(By.id("calcBtn"));
        WebElement result = driver.findElement(By.id("result"));

        // Input numbers
        num1.sendKeys("-5");
        num2.sendKeys("-10");
        calcButton.click();

        // Wait for result
        wait.until(ExpectedConditions.textToBePresentInElement(result, "Sum = -15"));

        // Verify result
        String output = result.getText().trim();
        System.out.println("Result (Negative): " + output);
        assertEquals("Sum = -15", output);
    }

    @Test
    public void testSumOfMixedNumbers() {
        driver.get(url);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("num1")));

        WebElement num1 = driver.findElement(By.id("num1"));
        WebElement num2 = driver.findElement(By.id("num2"));
        WebElement calcButton = driver.findElement(By.id("calcBtn"));
        WebElement result = driver.findElement(By.id("result"));

        // Input numbers
        num1.sendKeys("15");
        num2.sendKeys("-5");
        calcButton.click();

        // Wait for result
        wait.until(ExpectedConditions.textToBePresentInElement(result, "Sum = 10"));

        // Verify result
        String output = result.getText().trim();
        System.out.println("Result (Mixed): " + output);
        assertEquals("Sum = 10", output);
    }

    @Test
    public void testSumWithZero() {
        driver.get(url);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("num1")));

        WebElement num1 = driver.findElement(By.id("num1"));
        WebElement num2 = driver.findElement(By.id("num2"));
        WebElement calcButton = driver.findElement(By.id("calcBtn"));
        WebElement result = driver.findElement(By.id("result"));

        // Input numbers
        num1.sendKeys("100");
        num2.sendKeys("0");
        calcButton.click();

        // Wait for result
        wait.until(ExpectedConditions.textToBePresentInElement(result, "Sum = 100"));

        // Verify result
        String output = result.getText().trim();
        System.out.println("Result (Zero): " + output);
        assertEquals("Sum = 100", output);
    }

    // --- END: Added Test Cases ---

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

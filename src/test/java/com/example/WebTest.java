package com.example;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import static org.junit.Assert.assertEquals;

public class WebTest {

    @Test
    public void testGoogleTitle() {
        // Setup Chrome driver
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        try {
            // Open Google
            driver.get("https://www.google.com");

            // Verify title
            String title = driver.getTitle();
            assertEquals("Google", title);

            // Display success message
            System.out.println("✅ Website opened successfully: " + driver.getCurrentUrl());

        } catch (Exception e) {
            System.out.println("❌ Failed to open website: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}

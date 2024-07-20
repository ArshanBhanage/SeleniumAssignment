package com.Amazon;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class CommonUtils {

    public static WebDriver setBrowser(String browser, WebDriver driver){
        if (browser.equalsIgnoreCase("Chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://amazon.com");
        driver.manage().window().maximize();

        return driver;
    }

    public static void removeToaster(WebDriver driver){
        try{
            WebElement toasterRemove = driver.findElement(By.xpath("//div[@class='a-section glow-toaster glow-toaster-theme-default glow-toaster-slot-default nav-coreFlyout nav-flyout']//input[@data-action-type='DISMISS']"));
            toasterRemove.click();
        }catch (Exception e){
            System.out.println("Pop up not found..directly clicking today's deal");
        }
    }

    public static void refreshCaptcha(WebDriver driver) {
        try {
            if (driver.findElement(By.xpath("//*[text()='Enter the characters you see below']")).isDisplayed()){
                driver.navigate().refresh();
            }
        } catch (NoSuchElementException e) {
            System.out.println("Element not found");
        }
    }

    public static void changeLocation(WebDriver driver) throws InterruptedException {
        // change location to UK as item available only in UK
        WebElement locationButton = driver.findElement(By.id("glow-ingress-block"));
        locationButton.click();

        // select location dropdown
        WebElement locationsDropdown = driver.findElement(By.xpath("//div[@class=' a-declarative']/span[@class='a-dropdown-container']"));
        locationsDropdown.click();

        // select UK
        WebElement UKOption = driver.findElement(By.xpath("//select[@id='GLUXCountryList']/optgroup[1]/option[@value='GB']"));
        UKOption.click();

        // click done
        driver.findElement(By.xpath("//button[@name='glowDoneButton']")).click();

        // wait for location change
        Thread.sleep(3000);
    }
}


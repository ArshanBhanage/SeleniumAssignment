package com.Amazon;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import java.time.Duration;

public class Test5 {

    static WebDriver driver;
    static String browser = "chrome";

    @Test
    public void imagesInDepartmentsPage(){

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

        try {
            if (driver.findElement(By.xpath("//*[text()='Enter the characters you see below']")).isDisplayed()){
                driver.navigate().refresh();
            }
        } catch (NoSuchElementException e) {
            System.out.println("Element not found");
        }

        try{
            WebElement toasterRemove = driver.findElement(By.xpath("//div[@class='a-section glow-toaster glow-toaster-theme-default glow-toaster-slot-default nav-coreFlyout nav-flyout']//input[@data-action-type='DISMISS']"));
            toasterRemove.click();
        }catch (Exception e){
            System.out.println("Pop up not found..directly clicking today's deal");
        }

        // today's deal button
        WebElement todaysDeal = driver.findElement(By.xpath("//div[@id='nav-xshop']//a[1]"));
        todaysDeal.click();

        // see more button
        WebElement seeMore = driver.findElement(By.xpath("//button[@class='SeeMoreButton-module__container_j2YuDOVGk8QHFxg6xPQk']"));
        seeMore.click();

        // find images on department page(due to lazy loading only images visible to us will be counted)
        System.out.println(driver.findElements(By.xpath("//div[@class='ProductCard-module__imageWrapper_ytp7KTuzmF4mDTFCQLzr']")).size());
    }
}

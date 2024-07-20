package com.Amazon;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class Test1 {

    static WebDriver driver;
    static String browser = "chrome";

    @Test
    public void MoviesAndTv(){

        if (browser.equalsIgnoreCase("Chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        }

        SoftAssert softAssert = new SoftAssert();

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

        // movies and tv radio button
        WebElement moviesAndTvRadioBtn = driver.findElement(By.xpath("//span[text()='Movies & TV']"));
        moviesAndTvRadioBtn.click();

        // select first movie
        WebElement firstMovieCard = driver.findElement(By.xpath("//div[@class='a-section ProductCardImage-module__wrapper_YgLz4kq6ekChj01qeqOf']"));
        firstMovieCard.click();

        WebElement addToCartBtn = driver.findElement(By.id("add-to-cart-button"));
        addToCartBtn.click();
//        // select non prime user
//        WebElement nonPrimeUserRadioBtn = driver.findElement(By.xpath("//i[@class='a-icon a-accordion-radio a-icon-radio-inactive']"));
//        nonPrimeUserRadioBtn.click();
//
//        // click on add to cart button
//        WebElement addToCartBtn = driver.findElement(By.id("addToCart_feature_div"));
//        addToCartBtn.click();

        // check if item is added to the cart
        softAssert.assertEquals(driver.findElement(By.xpath("//h1[@class='a-size-medium-plus a-color-base sw-atc-text a-text-bold']")).getText(), "Added to Cart");

        softAssert.assertAll();

        System.out.println("Test case passed successfully");

        driver.quit();
    }

}

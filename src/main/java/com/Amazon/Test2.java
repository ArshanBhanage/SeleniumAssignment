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

public class Test2 {

    static WebDriver driver;
    static String browser = "chrome";

    @Test
    public void Books() throws InterruptedException {

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

        // change location
        changeLocation();

        // today's deal button
        WebElement todaysDeal = driver.findElement(By.xpath("//div[@id='nav-xshop']//a[1]"));
        todaysDeal.click();

        // see more button
        WebElement seeMore = driver.findElement(By.xpath("//button[@class='SeeMoreButton-module__container_j2YuDOVGk8QHFxg6xPQk']"));
        seeMore.click();

        // movies and tv radio button
        WebElement moviesAndTvRadioBtn = driver.findElement(By.xpath("//span[text()='Books']"));
        moviesAndTvRadioBtn.click();

        // select first movie
        WebElement firstMovieCard = driver.findElement(By.xpath("//div[@class='a-section ProductCardImage-module__wrapper_YgLz4kq6ekChj01qeqOf']"));
        firstMovieCard.click();

        // add to cart button click
        driver.findElement(By.id("add-to-cart-button")).click();

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

        driver.quit(); // kills the instance and clears the memory -> 'n' tabs open n tabs will be closed

    }

    public void changeLocation() throws InterruptedException {
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

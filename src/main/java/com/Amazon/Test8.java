package com.Amazon;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Test8 {
    static WebDriver driver;
    static String browser = "chrome";

    @Test
    public void addToCartInBrands() throws InterruptedException {

        driver = CommonUtils.setBrowser(browser, driver);

        SoftAssert softAssert = new SoftAssert();

        CommonUtils.refreshCaptcha(driver);

        CommonUtils.removeToaster(driver);

        // change location to UK as item available only in UK
        CommonUtils.changeLocation(driver);

        // today's deal button
        WebElement todaysDeal = driver.findElement(By.xpath("//div[@id='nav-xshop']//a[1]"));
        todaysDeal.click();

        // see more button
        WebElement seeMore = driver.findElement(By.xpath("//button[@class='SeeMoreButton-module__container_j2YuDOVGk8QHFxg6xPQk'][@aria-labelledby='see-more-brands-label']"));
        seeMore.click();

        // check first checkbox
        WebElement secondCheckBox = driver.findElement(By.xpath("//div[@aria-labelledby='brands']/span[2]"));
        secondCheckBox.click();

        // select first item
        WebElement firstItem = driver.findElement(By.xpath("//div[@data-index='0']/div/div/div[1]"));
        firstItem.click();

        // select size dropdown
        driver.findElement(By.xpath("//span[@class='twister-dropdown-highlight transparentTwisterDropdownBorder']")).click();
        driver.findElement(By.xpath("//div[starts-with(@id,'a-popover-')]//li[starts-with(@id, 'size_name_')][@class='a-dropdown-item dropdownAvailable']")).click();

        // select non prime user
        driver.findElement(By.id("newAccordionRow_1")).click();

        // add to cart
        driver.findElement(By.id("add-to-cart-button")).click();

        // check if item is added to the cart
        softAssert.assertEquals(driver.findElement(By.xpath("//h1[@class='a-size-medium-plus a-color-base sw-atc-text a-text-bold']")).getText(), "Added to Cart");

        driver.quit();

        softAssert.assertAll();

        System.out.println("Test case passed successfully");
    }

//    public void changeLocation() throws InterruptedException {
//        // change location to UK as item available only in UK
//        WebElement locationButton = driver.findElement(By.id("glow-ingress-block"));
//        locationButton.click();
//
//        // select location dropdown
//        WebElement locationsDropdown = driver.findElement(By.xpath("//div[@class=' a-declarative']/span[@class='a-dropdown-container']"));
//        locationsDropdown.click();
//
//        // select UK
//        WebElement UKOption = driver.findElement(By.xpath("//select[@id='GLUXCountryList']/optgroup[1]/option[@value='GB']"));
//        UKOption.click();
//
//        // click done
//        driver.findElement(By.xpath("//button[@name='glowDoneButton']")).click();
//
//        // wait for location change
//        Thread.sleep(3000);
//    }


}

package com.Amazon;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class Test5 {

    static WebDriver driver;
    static String browser = "chrome";

    @Test
    public void imagesInDepartmentsPage(){

        driver = CommonUtils.setBrowser(browser, driver);

        CommonUtils.refreshCaptcha(driver);

        CommonUtils.removeToaster(driver);

        // today's deal button
        WebElement todaysDeal = driver.findElement(By.xpath("//div[@id='nav-xshop']//a[1]"));
        todaysDeal.click();

        // see more button
        WebElement seeMore = driver.findElement(By.xpath("//button[@class='SeeMoreButton-module__container_j2YuDOVGk8QHFxg6xPQk']"));
        seeMore.click();

        // find images on department page(due to lazy loading only images visible to us will be counted)
        System.out.println(driver.findElements(By.xpath("//div[@class='ProductCard-module__imageWrapper_ytp7KTuzmF4mDTFCQLzr']")).size());

        driver.quit();
    }
}

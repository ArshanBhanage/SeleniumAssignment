package com.Amazon;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

public class Test4 {

    static WebDriver driver;
    static String browser = "chrome";

    @Test
    public void printRadioButtons(){

        driver = CommonUtils.setBrowser(browser, driver);

        CommonUtils.refreshCaptcha(driver);

        CommonUtils.removeToaster(driver);

        // today's deal button
        WebElement todaysDeal = driver.findElement(By.xpath("//div[@id='nav-xshop']//a[1]"));
        todaysDeal.click();

        // see more button
        WebElement seeMore = driver.findElement(By.xpath("//button[@class='SeeMoreButton-module__container_j2YuDOVGk8QHFxg6xPQk']"));
        seeMore.click();

        List<WebElement> radioButtonsUnderDepartments = driver.findElements(By.xpath("//div[@data-a-input-name='departments']"));

        for(WebElement radioButton : radioButtonsUnderDepartments){
            Assert.assertTrue(radioButton.isEnabled());
            System.out.println(radioButton.getText());
        }

        driver.quit();

        System.out.println("Test case passed successfully");
    }
}

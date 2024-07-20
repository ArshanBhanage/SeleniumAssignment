package com.Amazon;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.util.List;

public class Test6 {

    static WebDriver driver;
    static String browser = "chrome";

    @Test
    public void numberOfCheckBoxesInBrands(){

        driver = CommonUtils.setBrowser(browser, driver);

        SoftAssert softAssert = new SoftAssert();

        CommonUtils.refreshCaptcha(driver);

        CommonUtils.removeToaster(driver);

        // today's deal button
        WebElement todaysDeal = driver.findElement(By.xpath("//div[@id='nav-xshop']//a[1]"));
        todaysDeal.click();

        // see more button
        WebElement seeMore = driver.findElement(By.xpath("//button[@class='SeeMoreButton-module__container_j2YuDOVGk8QHFxg6xPQk'][@aria-labelledby='see-more-brands-label']"));
        seeMore.click();

        // print all the checkboxes
        List<WebElement> checkBoxes = driver.findElements(By.xpath("//div[@aria-labelledby='brands']/span"));

        System.out.println("No of CheckBoxes: " + checkBoxes.size());

        for (WebElement checkBox : checkBoxes) {
            System.out.println(checkBox.getText());
        }

        softAssert.assertAll();

        System.out.println("Test case passed successfully");

        driver.quit();
    }

}

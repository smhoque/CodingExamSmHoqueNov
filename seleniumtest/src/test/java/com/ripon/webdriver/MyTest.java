package com.ripon.webdriver;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by riponctg on 1/18/2017.
 */
public class MyTest {
    @Test
    public void startWebDriver(){
        WebDriver driver = new FirefoxDriver();
        driver.navigate().to("https://www.amazon.com");
        Assert.assertTrue("title should start", driver.getTitle().startsWith("amazon"));

        driver.close();
        driver.quit();

    }
}

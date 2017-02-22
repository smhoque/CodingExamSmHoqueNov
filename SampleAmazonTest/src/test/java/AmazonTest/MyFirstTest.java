package AmazonTest;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by riponctg on 1/26/2017.
 */
public class MyFirstTest {
    @Test
    public void startWebDriver(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\riponctg\\IdeaProjects\\SampleAmazonTest\\driver\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.navigate().to("http://seleniumsimplified.com");
          Assert.assertTrue("title should start with Selenium Simplified",
                  driver.getTitle().startsWith("Selenium Simplified"));
        driver.close();
        driver.quit();
    }

}

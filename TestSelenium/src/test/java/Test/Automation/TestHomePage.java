package Test.Automation;

import junit.framework.TestCase;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Simple test using the WicketTester
 */
public class TestHomePage extends TestCase
{
    @Test
    public void test1(){
        WebDriver driver = new ChromeDriver();
       System.setProperty("webdriver.chrome.driver", "C:\\Users\\riponctg\\IdeaProjects\\TestSelenium\\src\\lib\\chromedriver.exe");
        driver.get("https://www.amazon.com");
        driver.close();
        driver.quit();

}

}

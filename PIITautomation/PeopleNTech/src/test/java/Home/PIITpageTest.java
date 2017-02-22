package Home;

import Base.CommonApi;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by riponctg on 2/12/2017.
 */
public class PIITpageTest extends CommonApi {

    @Test
    public void testPIIThome() {
        System.out.println(driver.getCurrentUrl());
        driver.get("http://www.gmail.com");

        // Enter userd id
        driver.findElement(By.id("Email")).sendKeys("eriponctg@gmail.com");
        driver.manage().timeouts().implicitlyWait(5,
                TimeUnit.SECONDS);
//        WebElement element = driver.findElement(By.id("Email"));
//        element.sendKeys("eriponctg@gmail.com");
        driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/div[1]/form/div[1]/div/input")).click();
        // WebElement element1 = driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/div[1]/form/div[1]/div/input"));
        //     element1.click();
        //wait 5 secs for  userid to be entered
        driver.manage().timeouts().implicitlyWait(10,
                TimeUnit.SECONDS);
        //Enter Password
        driver.findElement(By.id("Passwd")).sendKeys("$Ctg1025");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //WebElement element2 = driver.findElement(By.id("Passwd"));
        //element2.sendKeys("$Ctg1025");
        driver.findElement(By.id("signIn")).click();
//        WebElement element3 = driver.findElement(By.id("signIn"));
//        element3.click();
        //Submit button
        //element.submit();
/*
        WebElement myDynamicElement = (new WebDriverWait(driver, 15)).until(ExpectedConditions.presenceOfElementLocated(By.id("gbg4")));
        driver.findElement(By.id("gbg4")).click();

        //press signout button
        driver.findElement(By.id("gb_71")).click();
        ;
*/

    }
}

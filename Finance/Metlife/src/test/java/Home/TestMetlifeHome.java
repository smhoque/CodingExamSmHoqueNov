package Home;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

/**
 * Created by riponctg on 2/1/2017.
 */
public class TestMetlifeHome  extends  CommonApi{
    @Test
    public void testMetlifeHome(){
        System.out.println(driver.getCurrentUrl());
        driver.findElement(By.xpath("html/body/div[1]/div[1]/div[1]/a/span[1]")).click();
        driver.findElement(By.xpath("html/body/div[1]/div[1]/div[2]/a/span")).click();
    }
}

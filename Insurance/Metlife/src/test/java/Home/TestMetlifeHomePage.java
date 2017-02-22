package Home;

import Base.CommonApi;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

/**
 * Created by riponctg on 2/5/2017.
 */
public class TestMetlifeHomePage extends CommonApi{
    @Test
    public void testMetLife(){
        System.out.println(driver.getCurrentUrl());
        driver.findElement(By.xpath("html/body/div[6]/div/div/ul/li[1]/a")).click();
        driver.findElement(By.xpath("html/body/div[6]/div/nav/ul/li[2]/a/p")).click();
        driver.findElement(By.xpath("html/body/div[6]/div/nav/ul/li[1]/a/div/p")).click();
    }
}

package Home;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

/**
 * Created by riponctg on 2/1/2017.
 */
public class TestChaseHome extends CommonApi {
    @Test
    public void testChaseHome(){
        System.out.println(driver.getCurrentUrl());
        driver.findElement(By.xpath("html/body/div[2]/div[5]/header/div[4]/div[1]/a")).click();

    }
}

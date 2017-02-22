package Home;

import Base.CommonApi;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

/**
 * Created by riponctg on 2/2/2017.
 */
public class TestAmazonHome extends CommonApi {
    @Test
    public void testItem(){
        System.out.println(driver.getCurrentUrl());
        driver.findElement(By.xpath(""));
    }
}

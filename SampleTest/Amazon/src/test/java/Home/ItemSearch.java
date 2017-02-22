package Home;

import Base.Common;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

/**
 * Created by riponctg on 1/4/2017.
 */
public class ItemSearch extends Common{
    @Test
    public void testhome() {
        System.out.println(driver.getCurrentUrl());
//        clickByXpath(".//*[@id='nav-xshop']/a[2]");
        driver.findElement(By.xpath(".//*[@id='nav-xshop']/a[2]")).click();
    }
}

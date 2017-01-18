package Home;

import Base.CommonApi;
import org.testng.annotations.Test;

/**
 * Created by riponctg on 1/12/2017.
 */
public class TestCnnHome  extends CommonApi{
    @Test
    public void testHome(){
        clickByXpath(".//*[@id='nav']/div[2]/div[2]/a[2]");
        navigateBack();
        clickByXpath(".//*[@id='nav']/div[2]/div[2]/a[3]");


    }

}

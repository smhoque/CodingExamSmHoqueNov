package Base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

/**
 * Created by riponctg on 2/2/2017.
 */
public class CommonApi {
    public WebDriver driver = null;

    @BeforeMethod
    public void prepare() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\riponctg\\neon\\workspace\\SeleniumProject1\\driver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get("https://www.amazon.com");
        driver.manage().window().maximize();

    }

    @AfterMethod
    public void tearDown() throws Exception {
        driver.close();
    }
}

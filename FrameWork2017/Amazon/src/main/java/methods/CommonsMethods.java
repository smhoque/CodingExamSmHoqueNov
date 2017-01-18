package methods;

import Base.CommonApi;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by riponctg on 1/12/2017.
 */
public class CommonsMethods extends CommonApi {
    public boolean isAlreadyLogIn = false;
    public ResultSet rs = null;
    Connection connect = null;
    Statement statement = null;
    PreparedStatement ps = null;
    ResultSet resultSet = null;


    public void UserLogin() throws Exception {


        // String[] userCre = loginCredential(); // To get the module login details


        if (!isAlreadyLogIn) {
            clickByXpath(".//*[@id='nav-link-yourAccount']/span[2]");
            sleepFor(2);
            typeByXpath(".//*[@id='ap_email']", "nahreen789@gmail.com");
            sleepFor(2);
            typeByXpath(".//*[@id='ap_password']", "0606440");
            sleepFor(2);
            clickByXpath(".//*[@id='signInSubmit']");
            sleepFor(2);
            isAlreadyLogIn = true;
        }


    }


    public void scrollDown() {
        ((JavascriptExecutor) driver).executeScript("scroll(0, 3000)");
    }

    public void scrollUp() {
        ((JavascriptExecutor) driver).executeScript("scroll(0, -3000)");
    }


    public void mouseHoverByXpath(String locator) {
        try {
            WebElement element = driver.findElement(By.xpath(locator));
            Actions action = new Actions(driver);
            action.moveToElement(element).perform();
        } catch (Exception ex) {
            System.out.println("First attempt has been done, This is second try");
            WebElement element = driver.findElement(By.cssSelector(locator));
            Actions action = new Actions(driver);
            action.moveToElement(element).perform();
        }
    }
}


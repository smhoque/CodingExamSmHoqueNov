package pageFactoryLogin;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * Created by riponctg on 1/12/2017.
 */
public class SearchItems {
    @FindBy(how = How.CSS, using = "#twotabsearchtextbox")
    public static WebElement searchInput;


    @FindBy(how = How.CSS, using = ".nav-input")
    public static WebElement searchSubmit;


    public void searchFor(String item) {
        searchInput.sendKeys(item, Keys.ENTER);
    }

    public void clearSearchInput() {
        searchInput.clear();
    }

}

package SearchItems;

import PageFactorySearch.DropDownMenuPage;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by riponctg on 1/12/2017.
 */
public class DropDownMenuSearch extends DropDownMenuPage {
    @Test
    public void dropDownMenu() throws InterruptedException {
        List<String> text = getMenus();
        typeOnInputSearch(text);
    }

}

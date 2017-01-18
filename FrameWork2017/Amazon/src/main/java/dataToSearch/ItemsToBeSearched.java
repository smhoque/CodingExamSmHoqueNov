package dataToSearch;

import Utility.DataReader;

import java.io.IOException;

/**
 * Created by riponctg on 1/12/2017.
 */
public class ItemsToBeSearched {
    //Option 2, supply search.data from External source like excel files.
    DataReader dr = new DataReader();
    public String [] getData()throws IOException {
        String path = System.getProperty("user.dir")+"/data/file1.xls";
        String [] st = dr.fileReader(path);
        return st;
    }
}

package datastructure;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by riponctg on 3/27/2016.
 */
public class UseArrayList {
    public static void main(String[] args){
        List<String> list = new ArrayList<String>();
        list.add("NY");
        list.add("CA");
        list.add("AZ");


    //    for(String city:list){
      //      System.out.println(city);
        //}

        Iterator it = list.iterator();
        while(it.hasNext()){
            System.out.println(it.next());

        }


    }


}

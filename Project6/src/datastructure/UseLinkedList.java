package datastructure;

import java.util.LinkedList;

/**
 * Created by riponctg on 3/27/2016.
 */
public class UseLinkedList {
    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<String>();
        list.add("NY");
        list.add("CA");
        list.add("AZ");


        for(String city:list){
            System.out.println(city);

        }


    }

}

package datastructure;

import java.util.*;

/**
 * Created by riponctg on 3/27/2016.
 */
public class UseNestedMap {
    public static void main(String[] args) {
        List<String> cityofUSA = new ArrayList<String>();
        cityofUSA.add("NY");
        cityofUSA.add("CA");
        cityofUSA.add("AZ");
        cityofUSA.add("WA");

        List<String> cityofCANADA = new ArrayList<String>();
        cityofCANADA.add("Montreal");
        cityofCANADA.add("Toronto");
        cityofCANADA.add("Vancuber");

        List<String> cityofBANGLADESH = new ArrayList<String>();
        cityofBANGLADESH.add("Dhaka");
        cityofBANGLADESH.add("Comilla");
        cityofBANGLADESH.add("Sylhet");

        Map<String, List<String>> map = new HashMap<String, List<String>>();
        map.put("USA", cityofUSA);
        map.put("Canada", cityofCANADA);
        map.put("Bangladesh", cityofBANGLADESH);

        for(Map.Entry<String,List<String>> city:map.entrySet()){
            System.out.println(city.getKey() + " " + city.getValue());
        }



    }
}

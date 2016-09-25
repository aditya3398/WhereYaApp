package wya.whereyaat;

/**
 * Created by shuka on 9/25/2016.
 */

public class Person {

    private String name = "";
    private String location = "";

    public Person(String name, String location){
        this.name = name;
        this.location = location;
    }

    public String getName(){
        return name;
    }

    public String getLocation(){
        return location;
    }




}

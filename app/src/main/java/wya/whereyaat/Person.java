package wya.whereyaat;

/**
 * Created by shuka on 9/25/2016.
 */

public class Person {

    private String name = "";
    private String location = "";
    private double latitude;
    private double longitude;

    public Person(String name, String location, double longitudeHolder, double latHolder){
        this.name = name;
        this.location = location;
        latitude = latHolder;
        longitude = longitudeHolder;
    }

    public String getName(){
        return name;
    }

    public String getLocation(){
        return location;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}

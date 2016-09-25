package wya.whereyaat;

/**
 * Created by shuka on 9/25/2016.
 */

public class Location implements Comparable{

    private int frequency = 0;
    private String location = "";

    public Location(int frequency, String location){
        this.frequency = frequency;
        this.location = location;
    }

    public String getLocation(){
        return location;
    }
    public int getFrequency(){
        return frequency;
    }
    public void setLocation(String location){
        this.location = location;
    }
    public void setFrequency(int frequency){
        this.frequency = frequency;
    }

    @Override
    public int compareTo(Object l) {

        int comparefreq = ((Location)l).getFrequency();
        return this.frequency-comparefreq;
    }
}

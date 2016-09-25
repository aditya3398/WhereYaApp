package wya.whereyaat;

import android.graphics.Bitmap;

/**
 * Created by bshah on 9/25/2016.
 */

public class PlaceThing {
    public String author;
    public String title;
    public String id;
    public String address;
    public String time;
    //public Bitmap image;
    public double longitude;
    public double latitude;

    public PlaceThing(String authorHolder, String titleHolder, String idHolder, String addressHolder, String timeHolder, double longitudeHolder, double latitudeHolder){
        author = authorHolder;
        title = titleHolder;
        id = idHolder;
        address = addressHolder;
        time = timeHolder;
        //image = imageHolder;
        longitude = longitudeHolder;
        latitude = latitudeHolder;
    }

    public PlaceThing(String authorHolder, String timeHolder, double longitudeHolder, double latitudeHolder){
        author = authorHolder;
        title = "";
        id = "";
        address = "";
        time = timeHolder;
        //image = null;
        longitude = longitudeHolder;
        latitude = latitudeHolder;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    /*
    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }*/
}

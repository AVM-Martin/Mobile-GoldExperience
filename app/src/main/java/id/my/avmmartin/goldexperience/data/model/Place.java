package id.my.avmmartin.goldexperience.data.model;

public class Place {
    private int id;
    private String name;
    private int rating;
    private String desc;
    private double longitude;
    private double latitude;

    @Override
    public String toString() {
        return getName();
    }

    // constructor

    public Place(int id, String name, int rating, String desc, double longitude, double latitude) {
        setId(id);
        setName(name);
        setRating(rating);
        setDesc(desc);
        setLongitude(longitude);
        setLatitude(latitude);
    }

    // getter

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getRating() {
        return rating;
    }

    public String getDesc() {
        return desc;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    // setter

    private void setId(int id) {
        this.id = id;
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setRating(int rating) {
        this.rating = rating;
    }

    private void setDesc(String desc) {
        this.desc = desc;
    }

    private void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    private void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}

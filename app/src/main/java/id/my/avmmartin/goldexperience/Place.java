package id.my.avmmartin.goldexperience;

final class Place {
    String name;
    int rating;
    String desc;
    double longitude;
    double latitude;

    public Place(String _name, int _rating, String _desc, double _longitude, double _latitude) {
        name = _name;
        rating = _rating;
        desc = _desc;
        longitude = _longitude;
        latitude = _latitude;
    }

    @Override public String toString() {
        return name;
    }
}

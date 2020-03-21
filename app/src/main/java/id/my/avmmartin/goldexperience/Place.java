package id.my.avmmartin.goldexperience;

final class Place {
    int id;
    String name;
    int rating;
    String desc;
    double longitude;
    double latitude;

    public Place(
        int _id, String _name, int _rating,
        String _desc, double _longitude, double _latitude
    ) {
        id = _id;
        name = _name;
        rating = _rating;
        desc = _desc;
        longitude = _longitude;
        latitude = _latitude;
    }

    @Override
    public String toString() {
        return name;
    }
}

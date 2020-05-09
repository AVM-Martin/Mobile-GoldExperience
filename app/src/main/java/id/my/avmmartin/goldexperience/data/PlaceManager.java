package id.my.avmmartin.goldexperience.data;

import java.util.List;
import java.util.Vector;

import id.my.avmmartin.goldexperience.data.model.Place;

public class PlaceManager extends Vector<Place> {
    List<Place> getPlaces() {
        return this;
    }

    Place getPlaceById(int placeId) {
        return get(placeId);
    }

    // singleton

    private static PlaceManager instance = new PlaceManager();

    static PlaceManager getInstance() {
        return instance;
    }

    private PlaceManager() {
        // TODO: hard coded database
        add(new Place(
            size(), "Potato Head", 4,
            "The best place in Bali", -6.2000809, 106.7833355
        ));
        add(new Place(
            size(), "Pink Beach", 5,
            "The best place in Lombok", -6.2261741, 106.9078293)
        );
    }
}

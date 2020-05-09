package id.my.avmmartin.goldexperience.data;

import android.content.Context;

import java.util.List;
import java.util.Vector;

import id.my.avmmartin.goldexperience.data.model.Place;

public class PlaceManager extends Vector<Place> {
    List<Place> getPlaces() {
        return PlaceManager.this;
    }

    Place getPlaceById(int placeId) {
        return get(placeId - 1); // Ahh, annoying offset on placeId
    }

    // constructor

    PlaceManager(Context context) {
        // TODO: hard coded database
        add(new Place(
            1, "Potato Head", 4,
            "The best place in Bali", -6.2000809, 106.7833355
        ));
        add(new Place(
            2, "Pink Beach", 5,
            "The best place in Lombok", -6.2261741, 106.9078293)
        );
    }
}

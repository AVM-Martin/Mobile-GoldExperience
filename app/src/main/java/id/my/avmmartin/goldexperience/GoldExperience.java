package id.my.avmmartin.goldexperience;

import android.app.Application;

import java.util.Date;
import java.util.HashMap;
import java.util.Vector;

final public class GoldExperience extends Application {
    final private String GUEST = "";

    private String user = GUEST;
    private HashMap<String, UserProfile> users = new HashMap<>();
    private Vector<Place> places = new Vector<>();

    public GoldExperience() {
        // TODO: hard-coded database
        places.add(new Place(
            1, "Potato Head", 4,
            "The best place in Bali",
            -6.2000809, 106.7833355
        ));
        places.add(new Place(
            2, "Pink Beach", 5,
            "The best place in Lombok",
            -6.2261741, 106.9078293
        ));
    }

    boolean login(String email, String password) {
        if (!users.containsKey(email)) {
            return false;
        } else if (!users.get(email).password.equals(password)) {
            return false;
        } else {
            user = email;
            return true;
        }
    }

    boolean is_logged_in() {
        return !user.equals(GUEST);
    }

    void logout() {
        user = GUEST;
    }

    String get_user() {
        return user;
    }

    UserProfile get_profile() {
        return users.get(user);
    }

    public void register_user(String email, UserProfile profile) {
        users.put(email, profile);
    }

    private void unregister_user(String email) {
        users.remove(email);
    }

    public void update_user(String email, UserProfile profile) {
        logout();
        unregister_user(get_user());

        register_user(email, profile);
        login(email, profile.password);
    }

    public Vector<Place> get_places() {
        // TODO: call the JSON API at https://api.myjson.com/bins/iocic
        return places;
    }

    public Place get_place(int id) {
        // TODO: call the JSON API at https://api.myjson.com/bins/iocic
        return places.get(id);
    }
}

package id.my.avmmartin.goldexperience;

import android.app.Application;

import java.util.Date;
import java.util.HashMap;
import java.util.Vector;

final public class GoldExperience extends Application {
    final private String GUEST = "";

    private String user = GUEST;
    HashMap<String, UserProfile> users = new HashMap<>();
    Vector<Place> places = new Vector<>();

    public GoldExperience() {
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

    String getUser() {
        return user;
    }

    UserProfile getProfile() {
        return users.get(user);
    }

    public void register_or_update(String email, UserProfile new_user) {
        users.put(email, new_user);
    }
}

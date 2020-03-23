package id.my.avmmartin.goldexperience;

import android.app.Application;

import java.util.Vector;

final public class GoldExperience extends Application {
    final static String INTENT_EMAIL = "id.my.avmmartin.goldexperience.EMAIL";
    final static String INTENT_PLACE_ID = "id.my.avmmartin.goldexperience.PLACE_ID";

    final private int GUEST = -1;

    private int user_id = GUEST;
    private Vector<UserProfile> users = new Vector<>();
    private Vector<Place> places = new Vector<>();
    private Vector<Plan> plans = new Vector<>();

    public GoldExperience() {
        // TODO: hard-coded database
        places.add(new Place(
            places.size(), "Potato Head", 4,
            "The best place in Bali", -6.2000809, 106.7833355
        ));
        places.add(new Place(
            places.size(), "Pink Beach", 5,
            "The best place in Lombok", -6.2261741, 106.9078293)
        );
    }

    boolean login(String email, String password) {
        // TODO QUIZ: hard-coded find user
        for (int idx = 0; idx < users.size(); idx++) {
            UserProfile user = users.get(idx);

            if (user.email.equals(email)) {
                if (user.password.equals(password)) {
                    user_id = idx;
                    return true;
                }
            }
        }

        return false;
    }

    boolean is_logged_in() {
        return user_id != GUEST;
    }

    void logout() {
        user_id = GUEST;
    }

    public int get_user_id() {
        return user_id;
    }

    UserProfile get_profile() {
        return users.get(user_id);
    }

    public void register_user(UserProfile user) {
        // TODO QUIZ: validate duplicate email
        user.id = users.size();
        users.add(user);
    }

    public void update_user(UserProfile user) {
        users.set(user_id, user);
    }

    public Vector<Place> get_places() {
        // TODO: call the JSON API at https://api.myjson.com/bins/iocic
        return places;
    }

    public Place get_place(int id) {
        // TODO: call the JSON API at https://api.myjson.com/bins/iocic
        return places.get(id);
    }

    public void add_new_plan(Plan plan) {
        plan.id = plans.size();
        plans.add(plan);
    }

    public Vector<Plan> get_user_plans() {
        Vector<Plan> result = new Vector<>();

        for (Plan plan: plans) {
            if (plan.fk_userid == user_id) {
                result.add(plan);
            }
        }

        return result;
    }
}

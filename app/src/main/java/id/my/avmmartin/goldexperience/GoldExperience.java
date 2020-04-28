package id.my.avmmartin.goldexperience;

import android.app.Application;

import java.util.Vector;

import id.my.avmmartin.goldexperience.data.model.Place;
import id.my.avmmartin.goldexperience.data.model.Plan;
import id.my.avmmartin.goldexperience.data.model.User;

public class GoldExperience extends Application {
    private static final int GUEST = -1;

    private int userId = GUEST;
    private Vector<User> users = new Vector<>();
    private Vector<Place> places = new Vector<>();
    private Vector<Plan> plans = new Vector<>();

    public boolean login(String email, String password) {
        for (int idx = 0; idx < users.size(); idx++) {
            User user = users.get(idx);

            if (user.getEmail().equals(email)) {
                if (user.isValidPassword(password)) {
                    userId = idx;
                    return true;
                }
            }
        }

        return false;
    }

    public boolean isLoggedIn() {
        return userId != GUEST;
    }

    public void logout() {
        userId = GUEST;
    }

    public int getUserId() {
        return userId;
    }

    public User getProfile() {
        return users.get(userId);
    }

    public void registerUser(User user) {
        user.setId(users.size());
        users.add(user);
    }

    public void updateUser(User user) {
        users.set(userId, user);
    }

    public Vector<Place> getPlaces() {
        // TODO: call the JSON API at https://api.myjson.com/bins/iocic
        return places;
    }

    public Place getPlace(int id) {
        // TODO: call the JSON API at https://api.myjson.com/bins/iocic
        return places.get(id);
    }

    public void addNewPlan(Plan plan) {
        plan.setId(plans.size());
        plans.add(plan);
    }

    public Vector<Plan> getUserPlans() {
        Vector<Plan> result = new Vector<>();

        for (Plan plan: plans) {
            if (plan.getFkUserId() == userId) {
                result.add(plan);
            }
        }

        return result;
    }

    public void deletePlan(int id) {
        int planIndex = -1;

        for (int idx = 0; idx < plans.size(); idx++) {
            Plan plan = plans.get(idx);

            if (plan.getId() == id) {
                planIndex = idx;
            }
        }

        if (planIndex != -1) {
            plans.remove(planIndex);
        }
    }

    // constructor

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
}

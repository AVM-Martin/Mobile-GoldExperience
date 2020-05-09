package id.my.avmmartin.goldexperience.data;

import android.content.Context;

import java.util.List;

import id.my.avmmartin.goldexperience.data.model.Place;
import id.my.avmmartin.goldexperience.data.model.Plan;
import id.my.avmmartin.goldexperience.data.model.User;
import id.my.avmmartin.goldexperience.exception.InvalidCredentialsException;
import id.my.avmmartin.goldexperience.exception.UserNotFoundException;
import id.my.avmmartin.goldexperience.utils.Constants;

import static java.lang.Thread.sleep;

public class DataManager {
    public void register(User user) {
        // TODO: welcome message via SMS
        userManager.insertNewUser(user);
    }

    public void login(String email, String password) throws InvalidCredentialsException {
        try {
            User user = userManager.getUserByEmail(email);

            if (!user.isValidPassword(password)) {
                throw new InvalidCredentialsException();
            }

            preferencesManager.setUserId(user.getId());

        } catch (UserNotFoundException e) {
            throw new InvalidCredentialsException();
        }
    }

    public void logout() {
        preferencesManager.setUserId(Constants.GUEST);
    }

    public boolean isLoggedIn() {
        return getAppUserId() != Constants.GUEST;
    }

    public int getAppUserId() {
        return preferencesManager.getUserId();
    }

    public User getAppUser() {
        return getUserById(getAppUserId());
    }

    // place

    public List<Place> getPlaces() {
        return placeManager.getPlaces();
    }

    public Place getPlace(int placeId) {
        return placeManager.getPlaceById(placeId);
    }

    // plan

    public void insertNewPlan(Plan plan) {
        planManager.insertNewPlan(plan);
    }

    public Plan getPlanByPosition(int position) {
        return planManager.getPlanByUserByPosition(getAppUserId(), position);
    }

    public int planSize() {
        return planManager.sizeByUser(getAppUserId());
    }

    public void deletePlanById(int planId) {
        planManager.deletePlanById(getAppUserId(), planId);
    }

    // user

    public User getUserById(int userId) {
        return userManager.getUserById(userId);
    }

    public void updateUser(User user) {
        userManager.updateUser(user);
    }

    // constructor

    private PlaceManager placeManager;
    private PlanManager planManager;
    private PreferencesManager preferencesManager;
    private UserManager userManager;

    public DataManager(Context context) {
        // TODO: call the JSON API at https://api.myjson.com/bins/iocic
        placeManager = PlaceManager.getInstance();
        planManager = new PlanManager(context);
        preferencesManager = new PreferencesManager(context);
        userManager = new UserManager(context);
    }
}
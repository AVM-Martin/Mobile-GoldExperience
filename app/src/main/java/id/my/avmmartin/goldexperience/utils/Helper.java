package id.my.avmmartin.goldexperience.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Helper {
    public static boolean isValidEmail(String email) {
        if (!email.contains("@")) {
            return false;
        } else if (email.indexOf("@") != email.lastIndexOf("@")) {
            return false;
        } else if (!email.contains(".")) {
            return false;
        } else {
            return !email.contains("@.") && !email.contains(".@");
        }
    }

    public static boolean isValidPassword(String password) {
        boolean containsAlpha = false;
        boolean containsDigit = false;

        for (int idx = 0; idx < password.length(); idx++) {
            if (Character.isLetter(password.charAt(idx))) {
                containsAlpha = true;
            } else if (Character.isDigit(password.charAt(idx))) {
                containsDigit = true;
            }
        }

        return password.length() >= 6 && containsAlpha && containsDigit;
    }

    public static boolean isValidPhoneNumber(String phone) {
        return phone.startsWith("+62");
    }

    public static String toDateFormat(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.FORMAT_DATE, Locale.US);
        return sdf.format(date);
    }

    public static String toTimeFormat(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.FORMAT_TIME, Locale.US);
        return sdf.format(date);
    }

    // constructor

    private Helper() {
        // none
    }
}

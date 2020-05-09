package id.my.avmmartin.goldexperience.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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

    public static String toDateFormat(Calendar calendar) {
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.FORMAT_DATE, Locale.US);
        return sdf.format(calendar.getTime());
    }

    public static String toTimeFormat(Calendar calendar) {
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.FORMAT_TIME, Locale.US);
        return sdf.format(calendar.getTime());
    }

    public static String getFormattedDate(Calendar date, Calendar time) {
        return toDateFormat(date) + " " + toTimeFormat(time);
    }

    public static Calendar getEndOfToday() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);

        return calendar;
    }

    // constructor

    private Helper() {
        // none
    }
}

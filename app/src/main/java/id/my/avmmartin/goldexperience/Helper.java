package id.my.avmmartin.goldexperience;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

class Helper {
    static boolean is_valid_email(String email) {
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

    static boolean is_valid_password(String password) {
        boolean contains_alpha = false;
        boolean contains_digit = false;

        for (int idx = 0; idx < password.length(); idx++) {
            if (Character.isLetter(password.charAt(idx))) {
                contains_alpha = true;
            } else if (Character.isDigit(password.charAt(idx))) {
                contains_digit = true;
            }
        }

        return password.length() >= 6 && contains_alpha && contains_digit;
    }

    static boolean is_valid_phone_number(String phone) {
        return phone.startsWith("+62");
    }

    static String to_date_format(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(GoldExperience.FORMAT_DATE, Locale.US);
        return sdf.format(date);
    }

    static String to_time_format(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(GoldExperience.FORMAT_TIME, Locale.US);
        return sdf.format(date);
    }
}

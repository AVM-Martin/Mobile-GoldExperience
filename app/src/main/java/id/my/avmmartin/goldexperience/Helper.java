package id.my.avmmartin.goldexperience;

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
        if (password.length() < 6) {
            return false;
        } else {
            return password.matches(".*(\\w.*\\d|\\d.*\\w).*");
        }
    }

    static boolean is_valid_phone_number(String phone) {
        return phone.startsWith("+62");
    }
}

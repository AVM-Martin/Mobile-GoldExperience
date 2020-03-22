package id.my.avmmartin.goldexperience;

import java.util.Date;

final class UserProfile {
    String email;
    String password;
    String fullname;
    Date birthday;
    String phone;
    boolean usertype_vip;
    boolean sex_male;

    public UserProfile(
        String _email, String _password, String _fullname, Date _birthday,
        String _phone, boolean _usertype_vip, boolean _sex_male
    ) {
        email = _email;
        password = _password;
        fullname = _fullname;
        birthday = _birthday;
        phone = _phone;
        usertype_vip = _usertype_vip;
        sex_male = _sex_male;
    }
}

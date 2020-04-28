package id.my.avmmartin.goldexperience.data.model;

import java.util.Date;

public class User {
    private int id;
    private String email;
    private String password;
    private String fullName;
    private Date birthday;
    private String phone;
    private boolean userTypeVIP;
    private boolean sexMale;

    public boolean isValidPassword(String password) {
        return getPassword().equals(password);
    }

    // constructor

    public User(
        int id, String email, String password, String fullName,
        Date birthday, String phone, boolean userTypeVIP, boolean sexMale
    ) {
        setId(id);
        setEmail(email);
        setPassword(password);
        setFullName(fullName);
        setBirthday(birthday);
        setPhone(phone);
        setUserTypeVIP(userTypeVIP);
        setSexMale(sexMale);
    }

    // getter

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    private String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getPhone() {
        return phone;
    }

    public boolean isUserTypeVIP() {
        return userTypeVIP;
    }

    public boolean isSexMale() {
        return sexMale;
    }

    // setter

    public void setId(int id) {
        this.id = id;
    }

    private void setEmail(String email) {
        this.email = email;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    private void setFullName(String fullName) {
        this.fullName = fullName;
    }

    private void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    private void setPhone(String phone) {
        this.phone = phone;
    }

    private void setUserTypeVIP(boolean userTypeVIP) {
        this.userTypeVIP = userTypeVIP;
    }

    private void setSexMale(boolean sexMale) {
        this.sexMale = sexMale;
    }
}

package id.my.avmmartin.goldexperience.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.util.Calendar;
import java.util.Date;

import id.my.avmmartin.goldexperience.GoldExperience;
import id.my.avmmartin.goldexperience.R;
import id.my.avmmartin.goldexperience.data.model.User;
import id.my.avmmartin.goldexperience.utils.Helper;

abstract class ProfileForm extends AppCompatActivity {
    protected GoldExperience mainApp;
    private EditText etEmail;
    private EditText etPassword;
    private EditText etFullName;
    private EditText etBirthday;
    private EditText etPhone;
    private Spinner spUserType;
    private RadioGroup rdSex;
    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initComponents();
        setEvents();
    }

    private void initComponents() {
        mainApp = (GoldExperience)this.getApplication();
        etEmail = findViewById(R.id.t_profile_et_email);
        etPassword = findViewById(R.id.t_profile_et_password);
        etFullName = findViewById(R.id.t_profile_et_fullname);
        etBirthday = findViewById(R.id.t_profile_et_birthday);
        etPhone = findViewById(R.id.t_profile_et_phone);
        spUserType = findViewById(R.id.t_profile_sp_usertype);
        rdSex = findViewById(R.id.t_profile_rd_sex);
        calendar = Calendar.getInstance();
    }

    private void setEvents() {
        etBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etBirthdayOnClick(view);
            }
        });
    }

    protected void loadData(User user) {
        etEmail.setText(user.getEmail());
        etPassword.setText("");
        etFullName.setText(user.getFullName());
        etBirthday.setText(Helper.toDateFormat(user.getBirthday()));
        etPhone.setText(user.getPhone());
        spUserType.setSelection(user.isUserTypeVIP() ? 1 : 0);
        ((RadioButton) rdSex.getChildAt(user.isSexMale() ? 1 : 2)).setChecked(true);

        calendar.setTime(user.getBirthday());
    }

    private void etBirthdayOnClick(View view) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
            this,
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int day) {
                    calendar.set(Calendar.YEAR, year);
                    calendar.set(Calendar.MONTH, month);
                    calendar.set(Calendar.DAY_OF_MONTH, day);
                    etBirthday.setText(Helper.toDateFormat(calendar.getTime()));
                }
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    protected User getUser() throws Exception {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        String fullName = etFullName.getText().toString();
        Date birthday = calendar.getTime();
        String phone = etPhone.getText().toString();
        boolean userTypeVIP = spUserType.getSelectedItem().toString().contains("VIP");
        boolean sexMale = rdSex.getCheckedRadioButtonId() == R.id.t_profile_rd_sex_male;

        if (email.equals("")) {
            throw new Exception(getString(R.string.warning_email_filled));
        } else if (!Helper.isValidEmail(email)) {
            throw new Exception(getString(R.string.warning_email_invalid));
        } else if (password.equals("")) {
            throw new Exception(getString(R.string.warning_password_filled));
        } else if (!Helper.isValidPassword(password)) {
            throw new Exception(getString(R.string.warning_password_invalid));
        } else if (fullName.equals("")) {
            throw new Exception(getString(R.string.warning_full_name_filled));
        } else if (etBirthday.getText().toString().equals("")) {
            throw new Exception(getString(R.string.warning_birthday_filled));
        } else if (phone.equals("")) {
            throw new Exception(getString(R.string.warning_phone_number_filled));
        } else if (!Helper.isValidPhoneNumber(phone)) {
            throw new Exception(getString(R.string.warning_phone_number_invalid));
        } else if (rdSex.getCheckedRadioButtonId() == -1) {
            throw new Exception(getString(R.string.warning_gender_filled));
        } else {
            return new User(-1, email, password, fullName, birthday, phone, userTypeVIP, sexMale);
        }
    }
}

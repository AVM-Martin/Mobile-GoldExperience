package id.my.avmmartin.goldexperience;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Calendar;
import java.util.Date;

abstract class ProfileForm extends AppCompatActivity {
    protected GoldExperience main_app;
    private EditText et_email;
    private EditText et_password;
    private EditText et_fullname;
    private EditText et_birthday;
    private EditText et_phone;
    private Spinner sp_usertype;
    private View rd_sex;
    private Calendar calendar;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init_components();
        set_events();
    }

    private void init_components() {
        main_app = (GoldExperience)this.getApplication();
        et_email = findViewById(R.id.t_profile_et_email);
        et_password = findViewById(R.id.t_profile_et_password);
        et_fullname = findViewById(R.id.t_profile_et_fullname);
        et_birthday = findViewById(R.id.t_profile_et_birthday);
        et_phone = findViewById(R.id.t_profile_et_phone);
        sp_usertype = findViewById(R.id.t_profile_sp_usertype);
        rd_sex = findViewById(R.id.t_profile_rd_sex);
        calendar = Calendar.getInstance();
    }

    private void set_events() {
        et_birthday.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                et_birthday_onclick(view);
            }
        });
    }

    protected void load_data(UserProfile user) {
        // TODO QUIZ: load data
        et_email.setText(user.email);
        et_password.setText(user.password);
        et_fullname.setText(user.fullname);
        et_birthday.setText(Helper.to_date_format(user.birthday));
        et_phone.setText(user.phone);
        // sp_usertype = findViewById(R.id.t_profile_sp_usertype);
        // rd_sex = findViewById(R.id.t_profile_rd_sex);
        calendar.setTime(user.birthday);
    }

    private void et_birthday_onclick(View view) {
        DatePickerDialog date_picker_dialog = new DatePickerDialog(
            this,
            new DatePickerDialog.OnDateSetListener() {
                @Override public void onDateSet(DatePicker view, int year, int month, int day) {
                    calendar.set(Calendar.YEAR, year);
                    calendar.set(Calendar.MONTH, month);
                    calendar.set(Calendar.DAY_OF_MONTH, day);
                    et_birthday.setText(Helper.to_date_format(calendar.getTime()));
                }
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        );
        date_picker_dialog.show();
    }

    protected UserProfile get_user_profile() throws Exception {
        // TODO QUIZ: get data
        String email = et_email.getText().toString();
        String password = et_password.getText().toString();
        String fullname = et_fullname.getText().toString();
        Date birthday = calendar.getTime();
        String phone = et_phone.getText().toString();
        boolean usertype = true;
        boolean sex = false;

        if (email.equals("")) {
            throw new Exception(getString(R.string.warning_email_filled));
        } else if (!Helper.is_valid_email(email)) {
            throw new Exception(getString(R.string.warning_email_invalid));
        } else if (password.equals("")) {
            throw new Exception(getString(R.string.warning_password_filled));
        } else if (!Helper.is_valid_password(password)) {
            throw new Exception(getString(R.string.warning_password_invalid));
        } else if (fullname.equals("")) {
            throw new Exception(getString(R.string.warning_full_name_filled));
        } else if (birthday.equals("")) {
            throw new Exception(getString(R.string.warning_birthday_filled));
        } else if (phone.equals("")) {
            throw new Exception(getString(R.string.warning_phone_number_filled));
        } else if (!Helper.is_valid_phone_number(phone)) {
            throw new Exception(getString(R.string.warning_phone_number_invalid));
        } else if (false) {
            throw new Exception(getString(R.string.warning_user_type_filled));
        } else if (false) {
            throw new Exception(getString(R.string.warning_gender_filled));
        } else {
            return new UserProfile(-1, email, password, fullname, birthday, phone, usertype, sex);
        }
    }
}

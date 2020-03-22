package id.my.avmmartin.goldexperience;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

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
    }

    private void set_events() {
        et_birthday.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                et_birthday_onclick(view);
            }
        });
    }

    private void et_birthday_onclick(View view) {
        // TODO QUIZ: date picker dialog
        // final Calendar c = Calendar.getInstance();
        // DatePickerDialog datePickerDialog = new DatePickerDialog(
        //     this,
        //     new DatePickerDialog.OnDateSetListener() {
        //         @Override
        //         public void onDateSet(DatePicker view, int year, int month, int day) {
        //             et_birthday.setText(day + "-" + month + "-" + year);
        //         }
        //     },
        //     c.get(c.YEAR), c.get(c.MONTH), c.get(c.DAY_OF_MONTH)
        // );
        // datePickerDialog.show();
    }

    protected String get_email() throws Exception {
        String email = et_email.getText().toString();

        if (email.equals("")) {
            throw new Exception("Email must be filled");
        } else if (!Helper.is_valid_email(email)) {
            throw new Exception("Email format is invalid");
        } else {
            return email;
        }
    }

    protected UserProfile get_user_profile() throws Exception {
        // TODO QUIZ: get data
        String email = get_email();
        String password = et_password.getText().toString();
        String fullname = et_fullname.getText().toString();
        // birthday
        String phone = et_phone.getText().toString();
        // usertype
        // sex

        if (password.equals("")) {
            throw new Exception("Password must be filled");
        } else if (!Helper.is_valid_password(password)) {
            throw new Exception("Password format is invalid");
        } else if (fullname.equals("")) {
            throw new Exception("Full name must be filled");
        } else if (false) {
            throw new Exception("Birthday must be filled");
        } else if (phone.equals("")) {
            throw new Exception("Phone number must be filled");
        } else if (!Helper.is_valid_phone_number(phone)) {
            throw new Exception("Phone number format is invalid");
        } else if (false) {
            throw new Exception("Please fill in your user type");
        } else if (false) {
            throw new Exception("Please fill in your gender");
        } else {
            return new UserProfile(
                email, password, fullname, new Date(), phone, false, false
            );
        }
    }
}

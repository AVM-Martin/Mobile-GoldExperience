package id.my.avmmartin.goldexperience;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class RegisterActivity extends AppCompatActivity {
    private GoldExperience main_app;
    private EditText et_email;
    private EditText et_password;
    private EditText et_fullname;
    private EditText et_birthday;
    private EditText et_phone;
    private Spinner sp_usertype;
    private View rd_sex;
    private CheckBox cb_tnc;
    private Button btn_login;
    private Button btn_register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        init_components();
        set_events();
    }

    private void init_components() {
        main_app = (GoldExperience)this.getApplication();
        et_email = findViewById(R.id.register_et_email);
        et_password = findViewById(R.id.register_et_password);
        et_fullname = findViewById(R.id.register_et_fullname);
        et_birthday = findViewById(R.id.register_et_birthday);
        et_phone = findViewById(R.id.register_et_phone);
        sp_usertype = findViewById(R.id.register_sp_usertype);
        rd_sex = findViewById(R.id.register_rd_sex);
        cb_tnc = findViewById(R.id.register_cb_tnc);
        btn_login = findViewById(R.id.register_btn_login);
        btn_register = findViewById(R.id.register_btn_register);
    }

    private void set_events() {
        et_birthday.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                et_birthday_onclick(view);
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                btn_login_onclick(view);
            }
        });
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                btn_register_onclick(view);
            }
        });
    }

    private void et_birthday_onclick(View view) {
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

    private void btn_login_onclick(View view) {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void btn_register_onclick(View view) {
        String email = et_email.getText().toString();
        String password = et_password.getText().toString();
        String fullname = et_fullname.getText().toString();
        // String birthday = et_birthday.getText().toString();
        String phone = et_phone.getText().toString();
        // usertype
        // sex
        boolean tnc = cb_tnc.isChecked();

        if (email.equals("")) {
            Toast.makeText(RegisterActivity.this, "Email must be filled", Toast.LENGTH_SHORT).show();
        } else if (!Helper.is_valid_email(email)) {
            Toast.makeText(RegisterActivity.this, "Email format is invalid", Toast.LENGTH_SHORT).show();
        } else if (password.equals("")) {
            Toast.makeText(RegisterActivity.this, "Password must be filled", Toast.LENGTH_SHORT).show();
        } else if (!Helper.is_valid_password(password)) {
            Toast.makeText(RegisterActivity.this, "Password format is invalid", Toast.LENGTH_SHORT).show();
        } else if (fullname.equals("")) {
            Toast.makeText(RegisterActivity.this, "Full name must be filled", Toast.LENGTH_SHORT).show();
        } else if (false) {
            Toast.makeText(RegisterActivity.this, "Birthday must be filled", Toast.LENGTH_SHORT).show();
        } else if (phone.equals("")) {
            Toast.makeText(RegisterActivity.this, "Phone number must be filled", Toast.LENGTH_SHORT).show();
        } else if (!Helper.is_valid_phone_number(phone)) {
            Toast.makeText(RegisterActivity.this, "Phone number format is invalid", Toast.LENGTH_SHORT).show();
        } else if (false) {
            Toast.makeText(RegisterActivity.this, "Please fill in your user type", Toast.LENGTH_SHORT).show();
        } else if (false) {
            Toast.makeText(RegisterActivity.this, "Please fill in your gender", Toast.LENGTH_SHORT).show();
        } else if (!tnc) {
            Toast.makeText(RegisterActivity.this, "Please check the Terms and Comdition", Toast.LENGTH_SHORT).show();
        } else {
            main_app.register_or_update(email, new UserProfile(
                password, fullname, new Date(), phone, false, false
            ));
            main_app.login(email, password);

            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }
}

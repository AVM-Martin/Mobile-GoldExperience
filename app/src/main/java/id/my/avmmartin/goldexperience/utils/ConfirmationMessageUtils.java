package id.my.avmmartin.goldexperience.utils;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.telephony.SmsManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.Random;

public class ConfirmationMessageUtils {
    private SmsManager smsManager;
    private String confirmationCode;

    // singleton style

    private static ConfirmationMessageUtils instance = new ConfirmationMessageUtils();

    public static ConfirmationMessageUtils getInstance(Activity activity) {
        int sendSMSPermission = ContextCompat.checkSelfPermission(
            activity,
            Manifest.permission.SEND_SMS
        );

        if(sendSMSPermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                activity,
                new String[]{
                    Manifest.permission.SEND_SMS
                },
                1
            );
        }

        return instance;
    }

    private ConfirmationMessageUtils() {
        smsManager = SmsManager.getDefault();
    }

    // private method

    private void generateConfirmationCode() {
        confirmationCode = Integer.toString(new Random().nextInt(10000));
    }

    // public method

    public void sendConfirmationCode(String phoneNumber) {
        generateConfirmationCode();

        smsManager.sendTextMessage(
            phoneNumber,
            null,
            "Your Gold Experience confirmation code is " + confirmationCode + ".",
            null,
            null
        );
    }

    public boolean confirmCode(String code) {
        return confirmationCode.equals(code);
    }
}

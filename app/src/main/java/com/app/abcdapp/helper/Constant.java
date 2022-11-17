package com.app.abcdapp.helper;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import java.util.UUID;

public class Constant {
    public static final String MainBaseUrl = "https://abcd.greymatterworks.in/";
    //public static final String MainBaseUrl = "http://192.168.43.38/abcd/";
    public static final String BaseUrl = MainBaseUrl + "api/";
    public static final String LOGIN_URL = BaseUrl + "login.php";
    public static final String IMPORT_DATA_URL = BaseUrl + "import_data.php";
    public static final String REGISTER_URL = BaseUrl + "register.php";
    public static final String WALLET_URL = BaseUrl + "wallet.php";
    public static final String UPDATE_USER_URL = BaseUrl + "updateuser.php";
    public static final String USER_DETAILS_URL = BaseUrl + "userdetails.php";
    public static final String UPDATE_BANK_URL = BaseUrl + "updatebank.php";
    public static final String WITHDRAWAL_URL = BaseUrl + "withdrawal.php";
    public static final String WITHDRAWAL_LIST_URL = BaseUrl + "withdrawal_lists.php";



    public static final String ID = "id";
    public static final String PROFILE = "profile";
    public static final String DEVICE_VERIFY = "device_verify";
    public static final String USER_ID = "user_id";
    public static final String MOBILE = "mobile";
    public static final String STUDENT_NAME = "student_name";
    public static final String ID_NUMBER = "id_number";
    public static final String ECITY = "ecity";
    public static final String PIN_CODE = "pin_code";
    public static final String CODES = "codes";
    public static final String IMPORT_DATA = "import_data";
    public static final String ACCOUNT_NUM = "account_num";
    public static final String HOLDER_NAME = "holder_name";
    public static final String BANK = "bank";
    public static final String BRANCH = "branch";
    public static final String IFSC = "ifsc";
    public static final String EARN = "earn";
    public static final String WITHDRAWAL = "withdrawal";

    public static final String EMAIL = "email";
    public static final String NAME = "name";
    public static final String BALANCE = "balance";
    public static final String REFER_CODE = "refer_code";
    public static final String TODAY_CODES = "today_codes";
    public static final String TOTAL_CODES = "total_codes";

    public static final String SUCCESS = "success";
    public static final String MESSAGE = "message";
    public static final String DATA = "data";
    public static final String BANK_DETAILS = "bank_details";
    public static final String USER_DETAILS = "user_details";

    public static final String DATE = "date";
    public static final String DOB = "dob";

    public static final String AMOUNT = "amount";
    public static final String ORGANIZER_ID = "organizer_id";
    public static final String CITY = "city";
    public static final String PINCCODE = "pincode";
    public static final String PASSWORD = "password";
    public static final String DEVICE_ID = "device_id";
    public static final String REFERRED_BY = "referred_by";
    @SuppressLint("HardwareIds")
    public static final  String getDeviceId(Activity activity) {

        String deviceId;

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            deviceId = android.provider.Settings.Secure.getString(activity.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
            // Toast.makeText(context,  deviceId, Toast.LENGTH_SHORT).show();

        } else {

            final TelephonyManager mTelephony = (TelephonyManager) activity.getSystemService(Context.TELEPHONY_SERVICE);


            if (mTelephony.getDeviceId() != null) {
                deviceId = mTelephony.getDeviceId();
            } else {
                deviceId = android.provider.Settings.Secure.getString(activity.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
                //  Toast.makeText(LoginActivity.this,  deviceId, Toast.LENGTH_SHORT).show();
            }
        }

        return deviceId;

    }
}
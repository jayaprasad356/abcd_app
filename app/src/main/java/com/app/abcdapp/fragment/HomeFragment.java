package com.app.abcdapp.fragment;

import static com.app.abcdapp.helper.Constant.AD_STATUS;
import static com.app.abcdapp.helper.Constant.getHistoryDays;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.abcdapp.helper.ApiConfig;
import com.app.abcdapp.helper.Constant;
import com.app.abcdapp.helper.DatabaseHelper;
import com.app.abcdapp.helper.Session;
import com.app.abcdapp.java.GenericTextWatcher;
import com.app.abcdapp.R;
import com.app.abcdapp.model.GenerateCodes;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.google.android.material.snackbar.Snackbar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class HomeFragment extends Fragment {

    TextView tvName,tvPincode,tvCity, tvId,tvTodayCodes,tvTotalCodes,tvHistorydays;
    EditText edName,edPincode,edCity;
    Button btnGenerate;


    EditText otp_textbox_one, otp_textbox_two, otp_textbox_three, otp_textbox_four,otp_textbox_five,otp_textbox_six,otp_textbox_seven,otp_textbox_eight,otp_textbox_nine,otp_textbox_ten;
    DatabaseHelper databaseHelper;
    ArrayList<GenerateCodes> generateCodes = new ArrayList<GenerateCodes>();
    Session session;
    Activity activity;
    ScrollView frame;

    String Idnumber = "";

    Handler handler;
    long code_generate_time = 0;
    public static Dialog dialog = null;
    Button btnAutoFill;
    TextView tvBalance;
    View root;
    private String AdId = "";



    public HomeFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_home, container, false);

        activity = getActivity();
        session = new Session(activity);
        AdId = session.getData(Constant.AD_REWARD_ID);
        databaseHelper = new DatabaseHelper(getActivity());
        MobileAds.initialize(activity);
        loadRewardedVideoAd();


        handler = new Handler();
        try {
            code_generate_time = Long.parseLong(session.getData(Constant.CODE_GENERATE_TIME)) * 1000;


        }catch (Exception e){
            code_generate_time = 3 * 1000;


        }

        dialog = new Dialog(activity);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setContentView(R.layout.customdia2);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        dialog.show();
        GotoActivity();
        tvBalance = root.findViewById(R.id.tvBalance);
        tvName = root.findViewById(R.id.tvName);
        tvPincode = root.findViewById(R.id.tvPincode);
        tvCity = root.findViewById(R.id.tvCity);
        tvId = root.findViewById(R.id.tvId);
        edName = root.findViewById(R.id.edName);
        edPincode = root.findViewById(R.id.edPincode);
        edCity = root.findViewById(R.id.edCity);
        tvTodayCodes = root.findViewById(R.id.tvTodayCodes);
        tvTotalCodes = root.findViewById(R.id.tvTotalCodes);
        tvHistorydays = root.findViewById(R.id.tvHistorydays);
        btnGenerate = root.findViewById(R.id.btnGenerate);
        btnAutoFill = root.findViewById(R.id.btnAutoFill);
        frame = root.findViewById(R.id.frame);

        otp_textbox_one = root.findViewById(R.id.otp_edit_box1);
        otp_textbox_two = root.findViewById(R.id.otp_edit_box2);
        otp_textbox_three = root.findViewById(R.id.otp_edit_box3);
        otp_textbox_four = root.findViewById(R.id.otp_edit_box4);
        otp_textbox_five = root.findViewById(R.id.otp_edit_box5);
        otp_textbox_six = root.findViewById(R.id.otp_edit_box6);
        otp_textbox_seven = root.findViewById(R.id.otp_edit_box7);
        otp_textbox_eight = root.findViewById(R.id.otp_edit_box8);
        otp_textbox_nine = root.findViewById(R.id.otp_edit_box9);
        otp_textbox_ten = root.findViewById(R.id.otp_edit_box10);
        setCodeValue();


        EditText[] edit = {otp_textbox_one, otp_textbox_two, otp_textbox_three, otp_textbox_four,otp_textbox_five,otp_textbox_six,otp_textbox_seven,otp_textbox_eight,otp_textbox_nine,otp_textbox_ten};
        otp_textbox_one.addTextChangedListener(new GenericTextWatcher(otp_textbox_one, edit));
        otp_textbox_two.addTextChangedListener(new GenericTextWatcher(otp_textbox_two, edit));
        otp_textbox_three.addTextChangedListener(new GenericTextWatcher(otp_textbox_three, edit));
        otp_textbox_four.addTextChangedListener(new GenericTextWatcher(otp_textbox_four, edit));
        otp_textbox_five.addTextChangedListener(new GenericTextWatcher(otp_textbox_five, edit));
        otp_textbox_six.addTextChangedListener(new GenericTextWatcher(otp_textbox_six, edit));
        otp_textbox_seven.addTextChangedListener(new GenericTextWatcher(otp_textbox_seven, edit));
        otp_textbox_eight.addTextChangedListener(new GenericTextWatcher(otp_textbox_eight, edit));
        otp_textbox_nine.addTextChangedListener(new GenericTextWatcher(otp_textbox_nine, edit));
        otp_textbox_ten.addTextChangedListener(new GenericTextWatcher(otp_textbox_ten, edit));
        generateCodes = databaseHelper.getLimitCodes();

        btnAutoFill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showRewardedVideoAd();
            }
        });

        btnGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Idnumber = otp_textbox_one.getText().toString().trim() + otp_textbox_two.getText().toString().trim() +
                        otp_textbox_three.getText().toString().trim() + otp_textbox_four.getText().toString().trim() + otp_textbox_five.getText().toString().trim() +
                        otp_textbox_six.getText().toString().trim() + otp_textbox_seven.getText().toString().trim() + otp_textbox_eight.getText().toString().trim() +
                        otp_textbox_nine.getText().toString().trim() + otp_textbox_ten.getText().toString().trim();
                if (!tvName.getText().toString().trim().equals(edName.getText().toString().trim())){

                   // Toast.makeText(getActivity(), "Name not match", Toast.LENGTH_SHORT).show();
                    edName.setError("Name not match");
                    edName.requestFocus();
                    return;

                }
                else if (!tvId.getText().toString().trim().equals(Idnumber.toString().trim())){


                    // Toast.makeText(getActivity(), "Id number not match", Toast.LENGTH_SHORT).show();
                    otp_textbox_ten.setError("Id number not match");
                    otp_textbox_ten.requestFocus();
                    return;
                }
                else if (!tvCity.getText().toString().trim().equals(edCity.getText().toString().trim())){

                   // Toast.makeText(getActivity(), "City not match", Toast.LENGTH_SHORT).show();
                    edCity.setError("City not match");
                    edCity.requestFocus();
                    return;
                }
                else if (!tvPincode.getText().toString().trim().equals(edPincode.getText().toString().trim())){

                   // Toast.makeText(getActivity(), "Pin code not match", Toast.LENGTH_SHORT).show();
                    edPincode.setError("Pin code not match");
                    edPincode.requestFocus();
                    return;
                }
                else {
                    if (ApiConfig.isConnected(activity)){
                        if (session.getData(Constant.CODE_GENERATE).equals("1")){
                            session.setInt(Constant.CODES,session.getInt(Constant.CODES) + 1);
                            FragmentManager fm = getActivity().getSupportFragmentManager();
                            fm.beginTransaction().replace(R.id.Container, new GenrateQRFragment()).commit();

                        }else {
                            Toast.makeText(activity, "You are Restricted for Generating Code", Toast.LENGTH_SHORT).show();
                        }

                    }



                }

            }
        });



        return root;
    }
    // creating RewardedVideoAd object
    private RewardedVideoAd AdMobrewardedVideoAd;

    // AdMob Rewarded Video Ad Id


    void loadRewardedVideoAd()
    {
        // initializing RewardedVideoAd Object
        // RewardedVideoAd  Constructor Takes Context as its
        // Argument
        AdMobrewardedVideoAd
                = MobileAds.getRewardedVideoAdInstance(activity);
        // Rewarded Video Ad Listener
        AdMobrewardedVideoAd.setRewardedVideoAdListener(
                new RewardedVideoAdListener() {
                    @Override
                    public void onRewardedVideoAdLoaded()
                    {
                        Log.d("REWARDED","onRewardedVideoAdLoaded");
                    }

                    @Override
                    public void onRewardedVideoAdOpened()
                    {
                        Log.d("REWARDED","onRewardedVideoAdOpened");
                    }

                    @Override
                    public void onRewardedVideoStarted()
                    {
                        Log.d("REWARDED","onRewardedVideoStarted");
                    }

                    @Override
                    public void onRewardedVideoAdClosed()
                    {
                        Log.d("REWARDED","onRewardedVideoAdClosed");
                    }

                    @Override
                    public void onRewarded(
                            RewardItem rewardItem)
                    {
                        addRewardCode();

                        Log.d("REWARDED","onRewarded");

                    }

                    @Override
                    public void
                    onRewardedVideoAdLeftApplication()
                    {
                        Log.d("REWARDED","onRewardedVideoAdLeftApplication");

                    }

                    @Override
                    public void onRewardedVideoAdFailedToLoad(
                            int i)
                    {
                        Log.d("REWARDED","onRewardedVideoAdFailedToLoad");

                    }

                    @Override
                    public void onRewardedVideoCompleted()
                    {
                        Log.d("REWARDED","onRewardedVideoCompleted");

                    }
                });

        // Loading Rewarded Video Ad
        AdMobrewardedVideoAd.loadAd(
                AdId, new AdRequest.Builder().build());
    }

    private void setCodeValue() {
        tvTodayCodes.setText((session.getInt(Constant.TODAY_CODES) + session.getInt(Constant.CODES))+ "");
        tvTotalCodes.setText((session.getInt(Constant.TOTAL_CODES) + session.getInt(Constant.CODES))+ "");
        double current_bal = (double) (session.getInt(Constant.CODES) * 0.17);
        double orgval = (double) current_bal + Double.parseDouble(session.getData(Constant.BALANCE));
        tvBalance.setText(String.format("%.2f", orgval)+"");
        tvHistorydays.setText(getHistoryDays(session.getData(Constant.JOINED_DATE)));
    }

    @SuppressLint("ShowToast")
    private void addRewardCode() {
        int codereward = Integer.parseInt(session.getData(Constant.REWARD));
        session.setInt(Constant.CODES,session.getInt(Constant.CODES) + codereward);
        Snackbar snackbar = Snackbar.make(root, "Code Rewarded Successfully", 5000);
        snackbar.getView().setBackgroundColor(ContextCompat.getColor(activity, R.color.green_500));
        View view = snackbar.getView();
        FrameLayout.LayoutParams params =(FrameLayout.LayoutParams)view.getLayoutParams();
        params.gravity = Gravity.CENTER;
        root.setLayoutParams(params);
        snackbar.show();
        setCodeValue();
        dialog.cancel();
    }

    public void showRewardedVideoAd()
    {
        if (AdMobrewardedVideoAd.isLoaded()) {
            AdMobrewardedVideoAd.show();
        }
        else {
            AdMobrewardedVideoAd.loadAd(
                    AdId, new AdRequest.Builder().build());
        }
    }


    private void GotoActivity()
    {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                frame.setVisibility(View.VISIBLE);
                dialog.cancel();
                if (session.getData(AD_STATUS).equals("1")){
                    SimpleDateFormat df = new SimpleDateFormat("dd/M/yyyy hh:mm:ss", Locale.getDefault());
                    Date c = Calendar.getInstance().getTime();
                    String currentDate = df.format(c);
                    if (!session.getBoolean(Constant.LAST_UPDATED_DATE_STATUS_AD)){
                        session.setData(Constant.LAST_UPDATED_DATE_AD,currentDate);
                        session.setBoolean(Constant.LAST_UPDATED_DATE_STATUS_AD,true);

                    }
                    Date date1 = null;
                    try {
                        date1 = df.parse(currentDate);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    Date date2 = null;
                    try {
                        date2 = df.parse(session.getData(Constant.LAST_UPDATED_DATE_AD));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    long different = date1.getTime() - date2.getTime();
                    long secondsInMilli = 1000;
                    long minutesInMilli = secondsInMilli * 60;
                    long hoursInMilli = minutesInMilli * 60;
                    long elapsedHours = different / hoursInMilli;
                    long elapsedMinutue = different / minutesInMilli;

                    if (elapsedMinutue >= Long.parseLong(session.getData(Constant.AD_SHOW_TIME))){
                        session.setBoolean(Constant.LAST_UPDATED_DATE_STATUS_AD,false);
                        showAdDialog();


                    }


                }






            }
        },code_generate_time);
    }

    private void showAdDialog() {
        Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.ad_code_layout);
        dialog.setCancelable(false);
        ImageView imgClose = dialog.findViewById(R.id.imgClose);
        Button btnReward = dialog.findViewById(R.id.btnReward);
        btnReward.setText("Click to Get "+session.getData(Constant.REWARD)+" Codes Free");
        Window window = dialog.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();
        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btnReward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                showRewardedVideoAd();
            }
        });


    }


    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onResume() {
        super.onResume();


        tvName.setText(generateCodes.get(0).getStudent_name());
        tvPincode.setText(generateCodes.get(0).getPin_code());
        tvCity.setText(generateCodes.get(0).getEcity());
        tvId.setText(generateCodes.get(0).getId_number());


    }



}
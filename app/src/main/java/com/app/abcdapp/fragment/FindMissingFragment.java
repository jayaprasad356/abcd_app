package com.app.abcdapp.fragment;

import static com.app.abcdapp.helper.Constant.getHistoryDays;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.abcdapp.Adapter.CitiesAdapter;
import com.app.abcdapp.Adapter.PincodeAdapter;
import com.app.abcdapp.R;
import com.app.abcdapp.activities.FindMissingActivity;
import com.app.abcdapp.activities.MainActivity;
import com.app.abcdapp.helper.ApiConfig;
import com.app.abcdapp.helper.Constant;
import com.app.abcdapp.helper.DatabaseHelper;
import com.app.abcdapp.helper.Session;
import com.app.abcdapp.model.GenerateCodes;

import java.util.ArrayList;
import java.util.Random;

public class FindMissingFragment extends Fragment {

    Button btnCities,btnpincode,btnVerfifyPincode,btnVerfifyCity,btnGenerate;
    LinearLayout llCityLocation,llPincode;
    EditText edCity,edPincode;
    Dialog dialog;
    CitiesAdapter citiesAdapter;
    PincodeAdapter pincodeAdapter;
    Activity activity;
    DatabaseHelper databaseHelper;
    RecyclerView rvCityData,rvPincodeData;
    EditText edName;
    TextView tvSkip;
    EditText otp_textbox_one, otp_textbox_two, otp_textbox_three, otp_textbox_four,otp_textbox_five,otp_textbox_six,otp_textbox_seven,otp_textbox_eight,otp_textbox_nine,otp_textbox_ten;

    Session session;

    ArrayList<GenerateCodes> generateCodes = new ArrayList<GenerateCodes>();
    Button btnCreateCode;

    TextView tvTodayCodes,tvTotalCodes,tvHistorydays;
    TextView tvBalance;
    public FindMissingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_find_missing, container, false);
        activity = getActivity();
        session = new Session(activity);
        databaseHelper = new DatabaseHelper(activity);




        btnCities = root.findViewById(R.id.btnCities);
        btnpincode = root.findViewById(R.id.btnpincode);
        llPincode = root.findViewById(R.id.llPincode);
        llCityLocation = root.findViewById(R.id.llCityLocation);
        btnVerfifyCity = root.findViewById(R.id.btnVerfifyCity);
        btnVerfifyPincode = root.findViewById(R.id.btnVerfifyPincode);
        edName = root.findViewById(R.id.edName);
        edCity = root.findViewById(R.id.edCity);
        edPincode = root.findViewById(R.id.edPincode);
        btnGenerate = root.findViewById(R.id.btnGenerate);
        tvSkip = root.findViewById(R.id.tvSkip);
        tvBalance = root.findViewById(R.id.tvBalance);
        btnCreateCode = root.findViewById(R.id.btnCreateCode);
        tvTodayCodes = root.findViewById(R.id.tvTodayCodes);
        tvTotalCodes = root.findViewById(R.id.tvTotalCodes);
        tvHistorydays = root.findViewById(R.id.tvHistorydays);

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



        generateCodes = databaseHelper.getMissingCodes();
        final int min = 0;
        final int max = 99;
        final int random = new Random().nextInt((max - min) + 1) + min;
        //final int random = 1;
        edName.setText(generateCodes.get(random).getStudent_name());
        setIdValue(generateCodes.get(random).getId_number());
        setCodeValue();
        tvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.fm.beginTransaction().replace(R.id.Container, new FindMissingFragment()).commitAllowingStateLoss();

            }
        });
        btnCreateCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                session.setData(Constant.WORK_ACTIVITY,"Create Code");
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.Container, new HomeFragment()).commit();
            }
        });




        btnCities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new Dialog(activity);
                dialog.setContentView(R.layout.cities_dialog_layout);
                rvCityData = dialog.findViewById(R.id.rvCityData);
                Window window = dialog.getWindow();
                window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false);
                rvCityData.setLayoutManager(linearLayoutManager);

                citylist();
                dialog.show();
//                Intent intent = new Intent(activity,CitiesActivity.class);
//                startActivity(intent);
            }
        });
        btnpincode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new Dialog(activity);
                dialog.setContentView(R.layout.pincode_dialog_layout);
                rvPincodeData = dialog.findViewById(R.id.rvPincodeData);
                Window window = dialog.getWindow();
                window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false);
                rvPincodeData.setLayoutManager(linearLayoutManager);

                pincodelist();
                dialog.show();
//                Intent intent = new Intent(activity,PincodeActivity.class);
//                startActivity(intent);
            }
        });

        btnVerfifyCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!edCity.getText().toString().trim().equals("")){
                    if (generateCodes.get(random).getEcity().equals(edCity.getText().toString().trim())){
                        Toast.makeText(activity, "Verify Successfully", Toast.LENGTH_SHORT).show();

                        llPincode.setVisibility(View.VISIBLE);
                        btnpincode.setVisibility(View.VISIBLE);
                        btnCities.setVisibility(View.GONE);
                        btnVerfifyCity.setVisibility(View.GONE);
                        edCity.setCompoundDrawablesWithIntrinsicBounds(null, null, ContextCompat.getDrawable(activity, R.drawable.ic_baseline_check_24), null);
                        edCity.setEnabled(false);

                    }
                    else {
                        Toast.makeText(activity, "City Wrong", Toast.LENGTH_SHORT).show();


                    }

                }

            }
        });
        btnVerfifyPincode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!edPincode.getText().toString().trim().equals("")){
                    if (generateCodes.get(random).getPin_code().equals(edPincode.getText().toString().trim())){
                        Toast.makeText(activity, "Verify Successfully", Toast.LENGTH_SHORT).show();
                        btnpincode.setVisibility(View.GONE);
                        btnCities.setVisibility(View.GONE);
                        btnVerfifyPincode.setVisibility(View.GONE);
                        edPincode.setCompoundDrawablesWithIntrinsicBounds(null, null, ContextCompat.getDrawable(activity, R.drawable.ic_baseline_check_24), null);
                        btnGenerate.setVisibility(View.VISIBLE);
                        edPincode.setEnabled(false);

                    }
                    else {
                        Toast.makeText(activity, "Pincode Wrong", Toast.LENGTH_SHORT).show();


                    }

                }

            }
        });

        btnGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        });
        
        return root;



    }

    private void pincodelist() {
        ArrayList<GenerateCodes> generateCodes = new ArrayList<GenerateCodes>();
        generateCodes = databaseHelper.getMissingCodes();

        pincodeAdapter = new PincodeAdapter(activity,generateCodes,dialog,FindMissingFragment.this);
        rvPincodeData.setAdapter(pincodeAdapter);



    }
    private void setCodeValue() {
        try {
            tvTodayCodes.setText((session.getInt(Constant.TODAY_CODES) + session.getInt(Constant.CODES))+ "");
            tvTotalCodes.setText((session.getInt(Constant.TOTAL_CODES) + session.getInt(Constant.CODES))+ "");
            double current_bal = (double) (session.getInt(Constant.CODES) * 0.17);
            double orgval = (double) current_bal + Double.parseDouble(session.getData(Constant.BALANCE));
            tvBalance.setText(String.format("%.2f", orgval)+"");


        }catch (Exception e){

        }
        tvHistorydays.setText(getHistoryDays(session.getData(Constant.JOINED_DATE)));

    }


    private void setIdValue(String id_number)
    {
        String id1,id2,id3,id4,id5,id6,id7,id8,id9,id10;
        id1=id_number.substring(0,1);
        id2=id_number.substring(1,2);
        id3=id_number.substring(2,3);
        id4=id_number.substring(3,4);
        id5=id_number.substring(4,5);
        id6=id_number.substring(5,6);
        id7=id_number.substring(6,7);
        id8=id_number.substring(7,8);
        id9=id_number.substring(8,9);
        id10=id_number.substring(9,10);
        otp_textbox_one.setText(id1);
        otp_textbox_two.setText(id2);
        otp_textbox_three.setText(id3);
        otp_textbox_four.setText(id4);
        otp_textbox_five.setText(id5);
        otp_textbox_six.setText(id6);
        otp_textbox_seven.setText(id7);
        otp_textbox_eight.setText(id8);
        otp_textbox_nine.setText(id9);
        otp_textbox_ten.setText(id10);

    }

    public void setCityValue(String ecity) {
        edCity.setText(ecity);
    }
    public void setPincodeValue(String pincode) {
        edPincode.setText(pincode);
    }


    private void citylist() {
        ArrayList<GenerateCodes> generateCodes = new ArrayList<GenerateCodes>();
        generateCodes = databaseHelper.getMissingCodes();

        citiesAdapter = new CitiesAdapter(activity,generateCodes,dialog,FindMissingFragment.this);
        rvCityData.setAdapter(citiesAdapter);




    }
}
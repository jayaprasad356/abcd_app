package com.app.abcdapp.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.abcdapp.activities.GenrateQRActivity;
import com.app.abcdapp.helper.Constant;
import com.app.abcdapp.helper.DatabaseHelper;
import com.app.abcdapp.helper.Session;
import com.app.abcdapp.java.GenericTextWatcher;
import com.app.abcdapp.R;
import com.app.abcdapp.model.GenerateCodes;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

    TextView tvName,tvPincode,tvCity, tvId,tvTodayCodes,tvTotalCodes;
    EditText edName,edPincode,edCity;
    Button btnGenerate;


    EditText otp_textbox_one, otp_textbox_two, otp_textbox_three, otp_textbox_four,otp_textbox_five,otp_textbox_six,otp_textbox_seven,otp_textbox_eight,otp_textbox_nine,otp_textbox_ten;
    DatabaseHelper databaseHelper;
    ArrayList<GenerateCodes> generateCodes = new ArrayList<GenerateCodes>();
    Session session;
    Activity activity;

    String Idnumber = "";



    public HomeFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        activity = getActivity();
        session = new Session(activity);

        databaseHelper = new DatabaseHelper(getActivity());


        tvName = root.findViewById(R.id.tvName);
        tvPincode = root.findViewById(R.id.tvPincode);
        tvCity = root.findViewById(R.id.tvCity);
        tvId = root.findViewById(R.id.tvId);
        edName = root.findViewById(R.id.edName);
        edPincode = root.findViewById(R.id.edPincode);
        edCity = root.findViewById(R.id.edCity);
        tvTodayCodes = root.findViewById(R.id.tvTodayCodes);
        tvTotalCodes = root.findViewById(R.id.tvTotalCodes);
        btnGenerate = root.findViewById(R.id.btnGenerate);


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

        tvTodayCodes.setText(session.getInt(Constant.CODES)+ "");
        tvTotalCodes.setText(session.getInt(Constant.CODES)+ "");




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
        generateCodes = databaseHelper.getAllCodes();

        btnGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Idnumber = otp_textbox_one.getText().toString().trim() + otp_textbox_two.getText().toString().trim() +
                        otp_textbox_three.getText().toString().trim() + otp_textbox_four.getText().toString().trim() + otp_textbox_five.getText().toString().trim() +
                        otp_textbox_six.getText().toString().trim() + otp_textbox_seven.getText().toString().trim() + otp_textbox_eight.getText().toString().trim() +
                        otp_textbox_nine.getText().toString().trim() + otp_textbox_ten.getText().toString().trim();
                if (!tvName.getText().toString().trim().equals(edName.getText().toString().trim())){

                    Toast.makeText(getActivity(), "Name not match", Toast.LENGTH_SHORT).show();
                }else if (!tvCity.getText().toString().trim().equals(edCity.getText().toString().trim())){

                    Toast.makeText(getActivity(), "City not match", Toast.LENGTH_SHORT).show();
                }
                else if (!tvPincode.getText().toString().trim().equals(edPincode.getText().toString().trim())){

                    Toast.makeText(getActivity(), "Pin code not match", Toast.LENGTH_SHORT).show();
                }

                else if (!tvId.getText().toString().trim().equals(Idnumber.toString().trim())){


                    Toast.makeText(getActivity(), "Id number not match", Toast.LENGTH_SHORT).show();
                }

                else {

                    session.setInt(Constant.CODES,session.getInt(Constant.CODES) + 1);
                    Intent intent = new Intent(getActivity(), GenrateQRActivity.class);
                    startActivity(intent);

                }

            }
        });
        Log.d("ID_NUMBER","Id number not match" + edit.toString());


        return root;
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
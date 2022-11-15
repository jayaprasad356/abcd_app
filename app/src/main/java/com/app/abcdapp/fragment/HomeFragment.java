package com.app.abcdapp.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.abcdapp.activities.GenrateQRActivity;
import com.app.abcdapp.java.GenericTextWatcher;
import com.app.abcdapp.R;

import java.util.Random;


public class HomeFragment extends Fragment {

    private static final String ALLOWED_CHARACTERS ="QWERTYUIOPASDFGHJKLZXCVBNM";
    private static final String ALLOWED_NUMBER ="0123456789";
    TextView tvName,tvPincode,tvCity,tvOtp;
    EditText edName,edPincode,edCity,edOtp;
    Button btnGenerate;


    EditText otp_textbox_one, otp_textbox_two, otp_textbox_three, otp_textbox_four,otp_textbox_five,otp_textbox_six,otp_textbox_seven,otp_textbox_eight,otp_textbox_nine,otp_textbox_ten;




    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_home, container, false);


        tvName = root.findViewById(R.id.tvName);
        tvPincode = root.findViewById(R.id.tvPincode);
        tvCity = root.findViewById(R.id.tvCity);
        tvOtp = root.findViewById(R.id.tvOtp);
        edName = root.findViewById(R.id.edName);
        edPincode = root.findViewById(R.id.edPincode);
        edCity = root.findViewById(R.id.edCity);
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



        btnGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                if (!tvName.getText().toString().trim().equals(edName.getText().toString().trim())){

                    Toast.makeText(getActivity(), "Name not match", Toast.LENGTH_SHORT).show();
                }else if (!tvCity.getText().toString().trim().equals(edCity.getText().toString().trim())){

                    Toast.makeText(getActivity(), "City not match", Toast.LENGTH_SHORT).show();
                }
                else if (!tvPincode.getText().toString().trim().equals(edPincode.getText().toString().trim())){

                    Toast.makeText(getActivity(), "Pin code not match", Toast.LENGTH_SHORT).show();
                }
                else if (!tvOtp.getText().toString().trim().equals(edit.toString().trim())){

                    Toast.makeText(getActivity(), "OTP not match", Toast.LENGTH_SHORT).show();
                }

                else {

                    Intent intent = new Intent(getActivity(), GenrateQRActivity.class);
                    startActivity(intent);

                }

            }
        });


        return root;
    }


    @Override
    public void onStart() {
        super.onStart();
        tvName.setText(getRandomString(10));
        tvPincode.setText(getRandomNumber(6));
        tvCity.setText(getRandomString(5));
        tvOtp.setText(getRandomString(2)+getRandomNumber(8));
    }



    private static String getRandomString(final int sizeOfRandomString)
    {
        final Random random=new Random();
        final StringBuilder sb=new StringBuilder(sizeOfRandomString);
        for(int i=0;i<sizeOfRandomString;++i)
            sb.append(ALLOWED_CHARACTERS.charAt(random.nextInt(ALLOWED_CHARACTERS.length())));
        return sb.toString();
    }


    private static String getRandomNumber(final int sizeOfRandomString)
    {
        final Random random=new Random();
        final StringBuilder sb=new StringBuilder(sizeOfRandomString);
        for(int i=0;i<sizeOfRandomString;++i)
            sb.append(ALLOWED_NUMBER.charAt(random.nextInt(ALLOWED_NUMBER.length())));
        return sb.toString();
    }
}
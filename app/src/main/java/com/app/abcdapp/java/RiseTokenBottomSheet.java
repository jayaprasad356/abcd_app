package com.app.abcdapp.java;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.app.abcdapp.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class RiseTokenBottomSheet extends BottomSheetDialogFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable
            ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.risetoken_bottom_sheet_lyt,container,false);
        Button RiseToken = view.findViewById(R.id.btnRiseToken);
        EditText etTitle,etDes;
        etTitle = view.findViewById(R.id.EtTitle);
        etDes = view.findViewById(R.id.EtDescription);
        RiseToken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(requireActivity(), "Done", Toast.LENGTH_SHORT).show();
            }
        });
        return view;

    }
    }

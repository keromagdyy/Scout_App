package com.scoutapp.kmagdy;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.scoutapp.kmagdy.databinding.FragmentDetailsBinding;

import java.util.Calendar;

public class DetailsFragment extends Fragment {
    FragmentDetailsBinding binding;
    DatabaseReference database;
    String name = "", teamName ="", stage ="", address ="", phoneNumber ="", birthDate ="", father="", enteringDate ="", rank ="", hobby ="", promiseDate ="", church ="";
    DatePickerDialog.OnDateSetListener birthDateListener, promiseDateListener, enteringDateListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDetailsBinding.inflate(inflater, container, false);

        database = FirebaseDatabase.getInstance().getReference("members");


        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController= Navigation.findNavController(getView());
                boolean flag=navController.popBackStack();
                if(!flag){
                    getActivity().finish();
                }
            }
        });

        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (!name.equals("") && teamName !="" && rank !="" && stage !="" && address !="" && phoneNumber !="" && father !="" && hobby !="" && church !="") {
                    name = binding.txtName.getText().toString();
                    teamName = binding.txtTeamName.getSelectedItem().toString();
                    rank = binding.txtRank.getText().toString();
                    stage = binding.txtStage.getText().toString();
                    address = binding.txtAddress.getText().toString();
                    phoneNumber = binding.txtPhoneNumber.getText().toString();
                    father = binding.txtFather.getText().toString();
                    hobby = binding.txtHobby.getText().toString();
                    church = binding.txtChurch.getText().toString();


                    MembersModel model = new MembersModel(name, teamName, rank,name, teamName, rank,name, teamName, rank);
                    database.push().setValue(model);



                    NavController navController = Navigation.findNavController(getView());
                    boolean flag = navController.popBackStack();
                    if (!flag) {
                        getActivity().finish();
                    }
//                } else {
//                    Toast.makeText(getContext(), "mfesh data", Toast.LENGTH_SHORT).show();
//                }
            }
        });

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        binding.txtBirthDate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), android.R.style.Theme_Holo_Light_Dialog_MinWidth, birthDateListener, year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        birthDateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                birthDate = d + "/" + (m+1) + "/" + y;
                binding.txtBirthDate.setText(birthDate);
            }
        };


        binding.txtEnteringDate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), android.R.style.Theme_Holo_Light_Dialog_MinWidth, enteringDateListener, year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        enteringDateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                enteringDate = d + "/" + (m+1) + "/" + y;
                binding.txtEnteringDate.setText(birthDate);
            }
        };


        binding.txtPromiseDate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), android.R.style.Theme_Holo_Light_Dialog_MinWidth, promiseDateListener, year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        promiseDateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                promiseDate = d + "/" + (m+1) + "/" + y;
                binding.txtPromiseDate.setText(birthDate);
            }
        };

        return binding.getRoot();
    }
}
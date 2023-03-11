package com.scoutapp.kmagdy;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.scoutapp.kmagdy.databinding.FragmentDetailsBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class DetailsFragment extends Fragment {
    FragmentDetailsBinding binding;

    DatabaseReference database;
    ArrayList<MembersModel> members;

    String name = "", teamName ="", stage ="", address ="", phoneNumber ="", birthDate ="", father="", enteringDate ="", rank ="", hobby ="", promiseDate ="", church ="";
    DatePickerDialog.OnDateSetListener birthDateListener, promiseDateListener, enteringDateListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDetailsBinding.inflate(inflater, container, false);

        database = FirebaseDatabase.getInstance().getReference("members");

        if (getArguments() != null) {
            name = getArguments().getString("name");
            rank = getArguments().getString("rank");
            teamName = getArguments().getString("team_name");
            binding.btnUpdate.setText("Update");
            showData();
        }

        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(getContext(), R.array.team_name, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.txtTeamName.setAdapter(arrayAdapter);


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
                if (getArguments() != null) {
                    updateData();
                } else {
                    addData();
                }
            }
        });

        binding.txtTeamName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                teamName = binding.txtTeamName.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

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
                binding.txtEnteringDate.setText(enteringDate);
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
                binding.txtPromiseDate.setText(promiseDate);
            }
        };

        return binding.getRoot();
    }


    void showData() {

        members = new ArrayList<>();
        List<String> arrTeamName = Arrays.asList(getResources().getStringArray(R.array.team_name));


        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                MembersModel model = null;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    model = snapshot.getValue(MembersModel.class);
                    if (model.getName().equals(name) && model.getRank().equals(rank) && teamName.equals(teamName)) {

                        binding.txtName.setText(model.getName());
                        for (int i = 0 ; i < arrTeamName.size() ; i++) {
                            if (arrTeamName.get(i).equals(model.getTeamName())) {
                                binding.txtTeamName.setSelection(i);
                            }
                        }
                        binding.txtRank.setText(model.getRank());
                        binding.txtAddress.setText(model.getAddress());
                        binding.txtPhoneNumber.setText(model.getPhoneNumber());
                        binding.txtBirthDate.setText(model.getBirthdate());
                        binding.txtFather.setText(model.getFather());
                        binding.txtEnteringDate.setText(model.getDateEntering());
                        binding.txtStage.setText(model.getStage());
                        binding.txtHobby.setText(model.getHobby());
                        binding.txtName.setText(model.getPromiseDate());
                        binding.txtChurch.setText(model.getChurch());

                    }
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), "on Cancelled", Toast.LENGTH_SHORT).show();
            }
        });
    }

    void addData(){
        name = binding.txtName.getText().toString();
        rank = binding.txtRank.getText().toString();
        stage = binding.txtStage.getText().toString();
        address = binding.txtAddress.getText().toString();
        phoneNumber = binding.txtPhoneNumber.getText().toString();
        father = binding.txtFather.getText().toString();
        hobby = binding.txtHobby.getText().toString();
        church = binding.txtChurch.getText().toString();

        if (!name.equals("") && !teamName.equals("") && !teamName.equals("أسم الفريق") && !rank.equals("") && !stage.equals("") && !address.equals("")&& !phoneNumber.equals("")&& !father.equals("") &&!hobby.equals("") && !church.equals("") && !promiseDate.equals("") && !enteringDate.equals("") && !birthDate.equals("")) {

            MembersModel model = new MembersModel(name, rank,teamName,stage,address,phoneNumber,father,hobby,church,birthDate,enteringDate,promiseDate);
            database.push().setValue(model);



            NavController navController = Navigation.findNavController(getView());
            boolean flag = navController.popBackStack();
            if (!flag) {
                getActivity().finish();
            }
        } else {
            Toast.makeText(getContext(), "data not found", Toast.LENGTH_SHORT).show();
        }

    }

    void updateData() {
        DatabaseReference databaseUpdate = FirebaseDatabase.getInstance().getReference("members").child(name);
        name = binding.txtName.getText().toString();
        teamName = binding.txtTeamName.getSelectedItem().toString();
        rank = binding.txtRank.getText().toString();
        stage = binding.txtStage.getText().toString();
        address = binding.txtAddress.getText().toString();
        phoneNumber = binding.txtPhoneNumber.getText().toString();
        father = binding.txtFather.getText().toString();
        hobby = binding.txtHobby.getText().toString();
        church = binding.txtChurch.getText().toString();
        birthDate = binding.txtBirthDate.getText().toString();
        enteringDate = binding.txtEnteringDate.getText().toString();
        promiseDate = binding.txtPromiseDate.getText().toString();

        if (!name.equals("") && !teamName.equals("") && !teamName.equals("أسم الفريق") && !rank.equals("") && !stage.equals("") && !address.equals("")&& !phoneNumber.equals("")&& !father.equals("") &&!hobby.equals("") && !church.equals("") && !promiseDate.equals("") && !enteringDate.equals("") && !birthDate.equals("")) {

            MembersModel model = new MembersModel(name, rank,teamName,stage,address,phoneNumber,father,hobby,church,birthDate,enteringDate,promiseDate);
            databaseUpdate.setValue(model);



            NavController navController = Navigation.findNavController(getView());
            boolean flag = navController.popBackStack();
            if (!flag) {
                getActivity().finish();
            }
        } else {
            Toast.makeText(getContext(), "data not found", Toast.LENGTH_SHORT).show();
        }

    }
}
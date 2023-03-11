package com.scoutapp.kmagdy;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.scoutapp.kmagdy.databinding.FragmentHomeBinding;
import com.scoutapp.kmagdy.databinding.FragmentSplashBinding;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    FragmentHomeBinding binding;
    MembersAdapter adapter;
    DatabaseReference database;
    ArrayList<MembersModel> members;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        members = new ArrayList<>();
        database = FirebaseDatabase.getInstance().getReference("members");

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                members.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    MembersModel model = snapshot.getValue(MembersModel.class);
                    members.add(model);

                }

                adapter = new MembersAdapter(HomeFragment.this, members);
                binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                binding.recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), "on Cancelled", Toast.LENGTH_SHORT).show();

            }
        });


        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavOptions.Builder navBuilder = new NavOptions.Builder();
                Navigation.findNavController(getView()).navigate(R.id.detailsFragment, null, navBuilder.build());
            }
        });

        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ArrayList<MembersModel> membersModels = new ArrayList<>();

                if (adapterView.getSelectedItem().equals("كل الفرق")) {
                    membersModels.addAll(members);
                }

                for (int j = 0; j < members.size(); j++) {
                    if (members.get(j).getTeamName().equals(adapterView.getSelectedItem())) {
                        membersModels.add(members.get(j));
                    }
                }
                adapter = new MembersAdapter(HomeFragment.this, membersModels);

                binding.recyclerView.setLayoutManager(new LinearLayoutManager(HomeFragment.this.getActivity(), LinearLayoutManager.VERTICAL, false));
                binding.recyclerView.setAdapter(adapter);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                adapter = new MembersAdapter(HomeFragment.this, members);

                binding.recyclerView.setLayoutManager(new LinearLayoutManager(HomeFragment.this.getActivity(), LinearLayoutManager.VERTICAL, false));
                binding.recyclerView.setAdapter(adapter);

            }
        });


        return binding.getRoot();
    }

}
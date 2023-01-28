package com.scoutapp.kmagdy;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scoutapp.kmagdy.databinding.FragmentDetailsBinding;
import com.scoutapp.kmagdy.databinding.FragmentHomeBinding;

public class DetailsFragment extends Fragment {
    FragmentDetailsBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDetailsBinding.inflate(inflater, container, false);

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
                NavController navController= Navigation.findNavController(getView());
                boolean flag=navController.popBackStack();
                if(!flag){
                    getActivity().finish();
                }
            }
        });

        return binding.getRoot();
    }
}
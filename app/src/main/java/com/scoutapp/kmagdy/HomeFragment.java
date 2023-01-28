package com.scoutapp.kmagdy;

import android.os.Bundle;

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

import com.scoutapp.kmagdy.databinding.FragmentHomeBinding;
import com.scoutapp.kmagdy.databinding.FragmentSplashBinding;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    FragmentHomeBinding binding;
    MembersAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);




        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavOptions.Builder navBuilder = new NavOptions.Builder();
                Navigation.findNavController(getView()).navigate(R.id.detailsFragment, null, navBuilder.build());
            }
        });


        ArrayList<MembersModel> members = new ArrayList<>();
        members.add(new MembersModel("كيرلس طارق","عريف أول","فريق متقدم"));
        members.add(new MembersModel("كيرلس مجدى","مساعد عريف","فريق كشاف"));
        members.add(new MembersModel("مينا جبرائيل","عريف ثانى","فريق كشاف"));
        members.add(new MembersModel("اندرو عادل","فرد","فريق متقدم"));
        members.add(new MembersModel("مارينا كمال","عريف أول","فريق زهرات"));
        members.add(new MembersModel("يوستينا نشأت","عريف ثانى","فريق مرشدات"));
        members.add(new MembersModel("مريم مراد","فرد","فريق زهرات"));
        members.add(new MembersModel("استير ابراهيم","مساعد","فريق زهرات"));
        members.add(new MembersModel("كاراس اسامة","عريف أول","فريق أشبال"));
        members.add(new MembersModel("بولا حنا","فرد","فريق أشبال"));
        members.add(new MembersModel("بيتر بديع","فرد","فريق كشاف"));
        members.add(new MembersModel("اسحق شنودة","فرد","فريق متقدم"));
        members.add(new MembersModel("ماريا شنودة","عريف أول","فريق رائدات"));
        members.add(new MembersModel("مريم مختار","فرد","فريق رائدات"));

        adapter = new MembersAdapter(this, members);

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity(),LinearLayoutManager.VERTICAL,false));
        binding.recyclerView.setAdapter(adapter);

        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ArrayList<MembersModel> membersModels =  new ArrayList<>();

                if (adapterView.getSelectedItem().equals("كل الفرق")) {
                    membersModels.addAll(members);
                }

                for (int j = 0 ; j < members.size() ; j++) {
                    if (members.get(j).teamName.equals(adapterView.getSelectedItem())){
                        membersModels.add(members.get(j));
                    }
                }
                adapter = new MembersAdapter(HomeFragment.this, membersModels);

                binding.recyclerView.setLayoutManager(new LinearLayoutManager(HomeFragment.this.getActivity(),LinearLayoutManager.VERTICAL,false));
                binding.recyclerView.setAdapter(adapter);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                adapter = new MembersAdapter(HomeFragment.this, members);

                binding.recyclerView.setLayoutManager(new LinearLayoutManager(HomeFragment.this.getActivity(),LinearLayoutManager.VERTICAL,false));
                binding.recyclerView.setAdapter(adapter);

            }
        });


        return binding.getRoot();
    }

    ArrayList<MembersModel> getData(){
        ArrayList<MembersModel> members = new ArrayList<>();
        members.add(new MembersModel("كيرلس طارق","عريف أول","فريق متقدم"));
        members.add(new MembersModel("كيرلس مجدى","مساعد عريف","فريق كشاف"));
        members.add(new MembersModel("مينا جبرائيل","عريف ثانى","فريق كشاف"));
        members.add(new MembersModel("اندرو عادل","فرد","فريق متقدم"));
        members.add(new MembersModel("مارينا كمال","عريف أول","فريق زهرات"));
        members.add(new MembersModel("يوستينا نشأت","عريف ثانى","فريق مرشدات"));
        members.add(new MembersModel("مريم مراد","فرد","فريق زهرات"));
        members.add(new MembersModel("استير ابراهيم","مساعد","فريق زهرات"));
        members.add(new MembersModel("كاراس اسامة","عريف أول","فريق أشبال"));
        members.add(new MembersModel("بولا حنا","فرد","فريق أشبال"));
        members.add(new MembersModel("بيتر بديع","فرد","فريق كشاف"));
        members.add(new MembersModel("اسحق شنودة","فرد","فريق متقدم"));
        members.add(new MembersModel("ماريا شنودة","عريف أول","فريق رائدات"));
        members.add(new MembersModel("مريم مختار","فرد","فريق رائدات"));

        return members;
    }

}
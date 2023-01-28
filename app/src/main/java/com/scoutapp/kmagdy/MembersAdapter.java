package com.scoutapp.kmagdy;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.scoutapp.kmagdy.databinding.MembersItemBinding;

import java.util.ArrayList;

public class MembersAdapter extends RecyclerView.Adapter<MembersAdapter.Holder> {
    ArrayList<MembersModel> members = new ArrayList<>();
    MembersItemBinding binding;
    Fragment fragment;

    public MembersAdapter(Fragment fragment, ArrayList<MembersModel> members) {
        this.fragment = fragment;
        this.members = members;
    }

    @NonNull
    @Override
    public MembersAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = MembersItemBinding.inflate(LayoutInflater.from(parent.getContext())
                ,parent, false);

        return new Holder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull MembersAdapter.Holder holder, int position) {
        holder.onBind(position,members.get(position));
    }

    @Override
    public int getItemCount() {
        return members.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        public Holder(@NonNull View itemView) {
            super(itemView);
        }
        public void onBind(int position,MembersModel member){

            binding.txtName.setText(member.getName());
            binding.txtTeamName.setText(member.getTeamName());
            binding.txtRank.setText(member.getRank());
            Bundle bundle = new Bundle();
            NavOptions.Builder navBuilder = new NavOptions.Builder();

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Navigation.findNavController(itemView).navigate(R.id.detailsFragment, null, navBuilder.build());
                }
            });
        }
    }
}

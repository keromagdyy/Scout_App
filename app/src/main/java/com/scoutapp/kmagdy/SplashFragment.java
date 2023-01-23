package com.scoutapp.kmagdy;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.scoutapp.kmagdy.databinding.FragmentSplashBinding;

public class SplashFragment extends Fragment {
    FragmentSplashBinding binding;
    Animation logoAnim = null;
    Animation logoChurchAnim = null;
    Animation poweredByAnim = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSplashBinding.inflate(inflater, container, false);
        binding.txtChurch.setVisibility(View.GONE);
        binding.txtPoweredBy.setVisibility(View.GONE);
        binding.txtNames.setVisibility(View.GONE);
        binding.imgLogo.setVisibility(View.GONE);

        logoChurchAnim = AnimationUtils.loadAnimation(getContext(), R.anim.fade);
        binding.txtChurch.setVisibility(View.VISIBLE);
        binding.txtChurch.setAnimation(logoChurchAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                logoAnim = AnimationUtils.loadAnimation(getContext(), R.anim.logo_anim);
                binding.imgLogo.setVisibility(View.VISIBLE);
                binding.imgLogo.setAnimation(logoAnim);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        poweredByAnim = AnimationUtils.loadAnimation(getContext(), R.anim.fade);
                        binding.txtPoweredBy.setVisibility(View.VISIBLE);
                        binding.txtNames.setVisibility(View.VISIBLE);
                        binding.txtPoweredBy.setAnimation(poweredByAnim);
                        binding.txtNames.setAnimation(poweredByAnim);


                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Navigation.findNavController(getView()).navigate(R.id.action_splashFragment_to_homeFragment);
                            }
                        },3200);

                    }
                },1800);

            }
        },500);


        return binding.getRoot();
    }
}
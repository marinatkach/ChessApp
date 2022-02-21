package com.example.chessapp.ui.admin;

import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.chessapp.Application;
import com.example.chessapp.R;
import com.example.chessapp.databinding.FragmentAdminBinding;
import com.example.chessapp.helpers.AppHelpers;

public class AdminFragment extends Fragment {

    private FragmentAdminBinding binding;

    private AdminViewController adminViewController;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentAdminBinding.inflate(inflater, container, false);
        adminViewController = new AdminViewController(this);
        final View root = binding.getRoot();

        if(adminViewController.hasAdminAccess()){
            processLogin(binding);
        }else{
            processLogout(binding);
        }

        binding.adminLoginOutBtn.setOnClickListener(event ->{
            String password = binding.adminPasswordEditText.getText().toString();
            if(adminViewController.hasAdminAccess()) // logout
            {
                adminViewController.logout();
                processLogout(binding);
            }
            else { // login
                boolean hasAccess = adminViewController.getAdminAccess(password);
                if(hasAccess) {
                    processLogin(binding);
                }
                else {
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(getContext(), R.string.wrong_password, duration);
                    toast.show();
                }
            }
        });

        binding.adminShowByRadiusBtn.setOnClickListener(e -> {
            adminViewController.setVisitedByRadius(Application.DISTANCE_SET_AS_VISITED_BY_RADIUS_METERS);
        });

        binding.adminShowAllClubsBtn.setOnClickListener(e -> {
            adminViewController.setVisitedClubs();
        });

        binding.adminShowAllCafesBtn.setOnClickListener(e -> {
            adminViewController.setVisitedCafes();
        });

        binding.adminShowAllOutdoorsBtn.setOnClickListener(e -> {
            adminViewController.setVisitedOutdoors();
        });

        binding.adminResetAllBtn.setOnClickListener(e -> {
            adminViewController.setAllPlacesAsUnvisited();
        });

        int km = (int)(Application.DISTANCE_SET_AS_VISITED_BY_RADIUS_METERS/1000);
        binding.adminShowByRadiusBtn.setText(String.format("R = %s km", km) );

        return root;
    }



    private void setEnable(Button button, boolean isEnable){
        button.setEnabled(isEnable);

        if(isEnable){
            button.setVisibility(View.VISIBLE);
        }else{
            button.setVisibility(View.INVISIBLE);
        }
    }

    private void processLogin(FragmentAdminBinding binding){
        Log.i("Admin","Process Login");
        binding.adminLoginOutBtn.setText(R.string.bnt_admin_logout);

        EditText passwordInput = binding.adminPasswordEditText;
        String currentInput = passwordInput.getText().toString();

        passwordInput.setText(AppHelpers.hideString(currentInput));
        passwordInput.setInputType(InputType.TYPE_NULL);
        passwordInput.setFocusable(View.NOT_FOCUSABLE);

        binding.adminAccessTextView.setText(R.string.admin_you_are_admin);


        setEnable(binding.adminResetAllBtn, true);
        setEnable(binding.adminShowAllCafesBtn, true);
        setEnable(binding.adminShowAllClubsBtn, true);
        setEnable(binding.adminShowAllOutdoorsBtn, true);
        setEnable(binding.adminShowByRadiusBtn, true);

    }

    private void processLogout(FragmentAdminBinding binding){
        Log.i("Admin","Process Logout");
        binding.adminLoginOutBtn.setText(R.string.bnt_admin_login);

        EditText passwordInput = binding.adminPasswordEditText;

        passwordInput.setText("");
        passwordInput.setInputType(InputType.TYPE_CLASS_TEXT);
        passwordInput.setFocusable(View.FOCUSABLE);

        binding.adminAccessTextView.setText(R.string.admin_you_are_user);

        setEnable(binding.adminResetAllBtn, false);
        setEnable(binding.adminShowAllCafesBtn, false);
        setEnable(binding.adminShowAllClubsBtn, false);
        setEnable(binding.adminShowAllOutdoorsBtn, false);
        setEnable(binding.adminShowByRadiusBtn, false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
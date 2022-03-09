package com.example.chessapp.ui.gps;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.chessapp.R;
import com.example.chessapp.databinding.FragmentGpsBinding;

public class GpsFragment extends Fragment {

    private FragmentGpsBinding binding;

    private GpsController controller ;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentGpsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        controller = new GpsController(this);

        updateBtnText(false);

        if(controller.isGpsEnabled()){
            controller.checkAndRequestPermission();
        }

        binding.gpsBtnOnOff.setOnClickListener(t -> {
            if(controller.isGpsEnabled()){
                controller.disableGps(this);
            }else{
                controller.enableGps();
                controller.checkAndRequestPermission();
            }
            updateBtnText(true);
        });

        return root;
    }

    @Override
    public void onResume() {
        updateBtnText(false);
        super.onResume();
    }


    /**
     * update test of button
     * @param reverse
     */
    public void updateBtnText(boolean reverse){
        boolean isEnabled = controller.isGpsEnabled();
        if(reverse) isEnabled = !isEnabled;

        if(isEnabled){
            controller.makeLocationRequest();
            binding.gpsBtnOnOff.setText(R.string.bnt_gps_off);
            binding.gpsTextStateTextView.setText(R.string.gps_enable_text);
        }else{
            binding.gpsBtnOnOff.setText(R.string.bnt_gps_on);
            binding.gpsTextStateTextView.setText(R.string.gps_disable_text);
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
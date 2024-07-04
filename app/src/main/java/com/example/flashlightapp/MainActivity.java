package com.example.flashlightapp;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.flashlightapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(binding.button.getText().toString().equals(getResources().getString(R.string.turn_on))) {
                    binding.button.setText(R.string.turn_off);
                    binding.imggon.setImageResource(R.drawable.lighton);
                    changeLightstate(true);
                } else {
                    binding.button.setText(R.string.turn_on);
                    binding.imggon.setImageResource(R.drawable.lightofff);
                    changeLightstate(false);
                }
            }
        });
    }

    private void changeLightstate(boolean state) {
        CameraManager cameraManager  =(CameraManager) getSystemService(CAMERA_SERVICE);
        String camid = null;
        try {
            camid = cameraManager.getCameraIdList()[0];
            cameraManager.setTorchMode(camid,state);
        } catch (CameraAccessException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        binding.button.setText(R.string.turn_on);
    }
}

package com.snipertech.leftoversaverapp.view;

import androidx.appcompat.app.AppCompatActivity;
import gr.net.maroulis.library.EasySplashScreen;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.snipertech.leftoversaverapp.R;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EasySplashScreen config = new EasySplashScreen(SplashScreenActivity.this)
                .withFullScreen()
                .withTargetActivity(RegisterActivity.class)
                .withSplashTimeOut(2000)
                .withBackgroundColor(Color.parseColor("#FFFFFF"))
                .withLogo(R.drawable.logo_small);


        View easySplashScreen = config.create();
        setContentView(easySplashScreen);
    }
}

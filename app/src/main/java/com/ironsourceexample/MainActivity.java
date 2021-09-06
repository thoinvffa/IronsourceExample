package com.ironsourceexample;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.sdk.InterstitialListener;
import com.superad.ironsource.IronSourceAdManager;

public class MainActivity extends AppCompatActivity {
    private static final String appKey = "85460dcd";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadAd();
    }

    @Override
    protected void onResume() {
        super.onResume();
        IronSourceAdManager.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        IronSourceAdManager.onPause(this);
    }

    private void loadAd() {
        Toast.makeText(this, "loadAd", Toast.LENGTH_SHORT).show();
        IronSourceAdManager.debugMode = true;
        IronSourceAdManager.init(this, appKey);
        IronSourceAdManager.loadInterstitial(new InterstitialListener() {
            @Override
            public void onInterstitialAdReady() {
                Toast.makeText(MainActivity.this, "onInterstitialAdReady", Toast.LENGTH_SHORT).show();
                IronSourceAdManager.showInterstitial();
            }

            @Override
            public void onInterstitialAdLoadFailed(IronSourceError ironSourceError) {
                Toast.makeText(MainActivity.this, "onInterstitialAdLoadFailed: " + ironSourceError.getErrorMessage(), Toast.LENGTH_LONG).show();

            }

            @Override
            public void onInterstitialAdOpened() {
                Toast.makeText(MainActivity.this, "onInterstitialAdOpened", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onInterstitialAdClosed() {
                Toast.makeText(MainActivity.this, "onInterstitialAdClosed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onInterstitialAdShowSucceeded() {
                Toast.makeText(MainActivity.this, "onInterstitialAdShowSucceeded", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onInterstitialAdShowFailed(IronSourceError ironSourceError) {
                Toast.makeText(MainActivity.this, "onInterstitialAdShowFailed: "
                        + ironSourceError.getErrorCode(), Toast.LENGTH_LONG).show();

            }

            @Override
            public void onInterstitialAdClicked() {
                Toast.makeText(MainActivity.this, "onInterstitialAdClicked", Toast.LENGTH_SHORT).show();

            }
        });
    }

}

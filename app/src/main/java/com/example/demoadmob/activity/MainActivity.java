package com.example.demoadmob.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.demoadmob.R;
import com.example.demoadmob.template.NativeTemplateStyle;
import com.example.demoadmob.template.TemplateView;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.nativead.MediaView;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.vapp.admoblibrary.ads.AdCallback;
import com.vapp.admoblibrary.ads.AdmodUtils;
import com.vapp.admoblibrary.ads.NativeAdCallback;
import com.vapp.admoblibrary.ads.admobnative.enumclass.GoogleEBanner;
import com.vapp.admoblibrary.ads.admobnative.enumclass.GoogleENative;

public class MainActivity extends AppCompatActivity {

    private AdView adView;
    private AppCompatButton btn_click;
    private InterstitialAd mInterstitialAd;
    private FirebaseAnalytics mFirebaseAnalytics;
    private long time_one = 0;
    private LinearLayout layout_native;
    private LinearLayout banner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        adView = findViewById(R.id.adView);
        btn_click = findViewById(R.id.btn_click);
        layout_native = findViewById(R.id.layout_native);
        banner = findViewById(R.id.banner);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {
//                createPersonalizedAd();
            }
        });

        AdmodUtils.getInstance().loadAdBanner(MainActivity.this, getString(R.string.test_ads_admob_banner_id), banner, GoogleEBanner.SIZE_FULL);

        btn_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long a = System.currentTimeMillis();
                long b = a - time_one;
                if (b > 30000){
//                    if (mInterstitialAd != null) {
//                        mInterstitialAd.show(MainActivity.this);
//                        CheckTime();
//                    } else {
//                        Log.d("TAG", "The interstitial ad wasn't ready yet.");
//                        mFirebaseAnalytics = FirebaseAnalytics.getInstance(MainActivity.this);
//                        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
//                        startActivity(intent);
//                    }
                    AdmodUtils.getInstance().loadAndShowAdInterstitialWithCallback(MainActivity.this, "google", 10000,
                            new AdCallback() {
                                @Override
                                public void onAdClosed() {
                                    //code here
                                    Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                                    startActivity(intent);
                                    CheckTime();
                                    AdmodUtils.getInstance().dismissAdDialog();
                                }

                                @Override
                                public void onAdFail() {
                                    //code here
                                    mFirebaseAnalytics = FirebaseAnalytics.getInstance(MainActivity.this);
                                    Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                                    startActivity(intent);
                                }
                            }, true);
                } else {
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    startActivity(intent);
                }
            }
        });

        AdmodUtils.getInstance().loadNativeAds(MainActivity.this, getString(R.string.test_ads_admob_native_id), layout_native, GoogleENative.UNIFIED_MEDIUM, new NativeAdCallback(){
            @Override
            public void onNativeAdLoaded() {
            }

            @Override
            public void onAdFail() {

            }
        });

//        Button crashButton = new Button(this);
//        crashButton.setText("Test Crash");
//        crashButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View view) {
//                throw new RuntimeException("Test Crash"); // Force a crash
//            }
//        });
//
//        addContentView(crashButton, new ViewGroup.LayoutParams(
//                ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT));

    }

//    private void createPersonalizedAd(){
//        Log.d("TAG", "Personalized Ad request");
//        AdRequest adRequest = new AdRequest.Builder().build();
//        adView.loadAd(adRequest);
//        createInterstitialAd(adRequest);
//        createNativeAds(adRequest);
//    }
//
//    private void createInterstitialAd(AdRequest adRequest) {
//        InterstitialAd.load(this,"ca-app-pub-3940256099942544/1033173712", adRequest,
//                new InterstitialAdLoadCallback() {
//                    @Override
//                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
//                        // The mInterstitialAd reference will be null until
//                        // an ad is loaded.
//                        mInterstitialAd = interstitialAd;
//                        Log.d("TAG", "onAdLoaded");
//
//                        mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
//                            @Override
//                            public void onAdDismissedFullScreenContent() {
//                                // Called when fullscreen content is dismissed.
//                                Log.d("dismissed", "The ad was dismissed.");
//                                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
//                                startActivity(intent);
//                            }
//
//                            @Override
//                            public void onAdFailedToShowFullScreenContent(AdError adError) {
//                                // Called when fullscreen content failed to show.
//                                Log.d("TAG", "The ad failed to show.");
//                            }
//
//                            @Override
//                            public void onAdShowedFullScreenContent() {
//                                // Called when fullscreen content is shown.
//                                // Make sure to set your reference to null so you don't
//                                // show it a second time.
//                                mInterstitialAd = null;
//                                Log.d("TAG", "The ad was shown.");
//                                createPersonalizedAd();
//                            }
//                        });
//                    }
//
//                    @Override
//                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
//                        // Handle the error
//                        Log.d("TAG", loadAdError.getMessage());
//                        mInterstitialAd = null;
//                    }
//                });
//    }
//
//    private void createNativeAds(AdRequest adRequest){
//        AdLoader adLoader = new AdLoader.Builder(this, "ca-app-pub-3940256099942544/2247696110")
//                .forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
//                    @Override
//                    public void onNativeAdLoaded(NativeAd nativeAd) {
//                        if (isDestroyed()){
//                            nativeAd.destroy();
//                            return;
//                        }
//                        NativeTemplateStyle styles = new
//                                NativeTemplateStyle.Builder().build();
//                        TemplateView template = findViewById(R.id.my_template);
//                        template.setStyles(styles);
//                        template.setNativeAd(nativeAd);
//                    }
//                })
//                .build();
//        adLoader.loadAd(adRequest);
//    }

    public void CheckTime(){
        time_one = System.currentTimeMillis();
        Log.d("Time", System.currentTimeMillis()+"");
    }
}
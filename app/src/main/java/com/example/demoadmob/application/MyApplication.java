package com.example.demoadmob.application;

import android.content.Context;

import androidx.multidex.MultiDex;

import com.example.demoadmob.BuildConfig;
import com.example.demoadmob.R;
import com.vapp.admoblibrary.AdsMultiDexApplication;
import com.vapp.admoblibrary.ads.AdmodUtils;
import com.vapp.admoblibrary.iap.PurchaseUtils;

public class MyApplication extends AdsMultiDexApplication{

    boolean isShowAds = true;
    boolean isShowAdsResume = true;

    @Override
    public void onCreate() {
        super.onCreate();

        PurchaseUtils.getInstance().initBilling(this,getString(R.string.play_console_license));
        if (PurchaseUtils.getInstance().isPurchased(getString(R.string.premium))) {
            isShowAds = false;
        }else {
            isShowAds = true;
        }

        AdmodUtils.getInstance().initAdmob(this, 10000, BuildConfig.DEBUG, isShowAds);

        if (isShowAdsResume) {
            com.vapp.admoblibrary.ads.AppOpenManager.getInstance().init(this, getString(R.string.test_ads_admob_app_open));
//            com.vapp.admoblibrary.ads.AppOpenManager.getInstance().disableAppResumeWithActivity(MainActivity.class);
        }
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

}

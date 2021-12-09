package com.example.demoadmob.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ads.google.admobads.admobnative.GoogleNativeAdAdapter;
import com.example.demoadmob.R;
import com.example.demoadmob.adapter.AdapterRecycleView;
import com.example.demoadmob.adapter.MyAdapter;
import com.example.demoadmob.model.Ad;
import com.example.demoadmob.model.ItemModel;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.vapp.admoblibrary.ads.AdmodUtils;
import com.vapp.admoblibrary.ads.RewardAdCallback;
import com.vapp.admoblibrary.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    private TextView tv_coins;
    private Button btn_reward;
    private RewardedAd mRewardedAd;
    private final String TAG = "--->AdMob";
    int coin = 0;
    private RecyclerView recyclerView;
//    private AdapterRecycleView adapterRecycleView;
//    private RecyclerView.LayoutManager  layoutManager;
    private ArrayList<ItemModel> itemList;
//    private List<NativeAd> nativeAdList;
//    private ArrayList<Object> objects;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        tv_coins = findViewById(R.id.tv_coins);
        btn_reward = findViewById(R.id.btn_rewarded);
        recyclerView = findViewById(R.id.rcv_item);
        recyclerView.setHasFixedSize(true);
        itemList = new ArrayList<>();
        itemList.add(new ItemModel("Item 1"));
        itemList.add(new ItemModel("Item 2"));
        itemList.add(new ItemModel("Item 3"));
        itemList.add(new ItemModel("Item 4"));
        itemList.add(new ItemModel("Item 5"));
        itemList.add(new ItemModel("Item 6"));
        itemList.add(new ItemModel("Item 7"));
        itemList.add(new ItemModel("Item 8"));
        itemList.add(new ItemModel("Item 9"));
        itemList.add(new ItemModel("Item 10"));

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {
            }
        });

//        layoutManager = new LinearLayoutManager(this);
//        adapterRecycleView = new AdapterRecycleView();
//        adapterRecycleView.setList(itemList);
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setAdapter(adapterRecycleView);
//
//        nativeAdList = new ArrayList<>();

        createNativeAd();

        btn_reward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (mRewardedAd != null){
//                    mRewardedAd.show(SecondActivity.this, new OnUserEarnedRewardListener() {
//                        @Override
//                        public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
//                            Log.d(TAG, "The user earned the reward.");
//                            int rewardAmount = rewardItem.getAmount();
//                            coin += rewardAmount;
////                            String rewardType = rewardItem.getType();
//                            tv_coins.setText(String.valueOf(coin));
//                        }
//                    });
//                } else {
//                    Toast.makeText(SecondActivity.this, "Ad Not Loaded!", Toast.LENGTH_SHORT).show();
//                    Log.d(TAG, "Ad Not Loaded!");
//                }

                AdmodUtils.getInstance().loadAndShowAdRewardWithCallback(SecondActivity.this, getString(R.string.test_ads_admob_reward_id), new RewardAdCallback() {
                    @Override
                    public void onAdClosed() {
                        //code here
                        Utils.getInstance().showMessenger(SecondActivity.this,"Reward");
                        tv_coins.setText(String.valueOf(coin));
                    }

                    @Override
                    public void onAdFail() {
                        //code here
                        Utils.getInstance().showMessenger(SecondActivity.this,"Reward fail");
                    }

                    @Override
                    public void onEarned() {
                        AdmodUtils.getInstance().dismissAdDialog();
                        coin++;
                    }
                }, true);

            }
        });


    }

    private void createNativeAd() {
//        objects = new ArrayList<>();
//        Ad ad = new Ad();
//        AdLoader adLoader = new AdLoader.Builder(this, "ca-app-pub-3940256099942544/2247696110")
//                .forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
//                    @Override
//                    public void onNativeAdLoaded(NativeAd nativeAd) {
//                        if (isDestroyed()){
//                            nativeAd.destroy();
//                            return;
//                        }
//
//                        nativeAdList.add(nativeAd);
//
//                        if (!ad.getAdLoader().isLoading()){
//                            objects.add(itemList.get(0));
//                            objects.add(itemList.get(1));
//                            objects.add(itemList.get(2));
//                            objects.add(nativeAdList.get(0));
//                            objects.add(itemList.get(3));
//                            objects.add(itemList.get(4));
//                            objects.add(itemList.get(5));
//                            objects.add(nativeAdList.get(1));
//                            objects.add(itemList.get(6));
//                            objects.add(itemList.get(7));
//                            objects.add(itemList.get(8));
//                            objects.add(nativeAdList.get(2));
//                            objects.add(itemList.get(9));
//
//                            adapterRecycleView.setObject(objects);
//                        }
//                    }
//                })
//                .withAdListener(new AdListener() {
//                    @Override
//                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
//                        super.onAdFailedToLoad(loadAdError);
//                        new CountDownTimer(10000, 1000){
//
//                            @Override
//                            public void onTick(long l) {
//                                Log.d(TAG, "Timer: " + l);
//                            }
//
//                            @Override
//                            public void onFinish() {
//                                Log.d(TAG, "Reloading Ad!");
//                                createNativeAd();
//                            }
//                        }.start();
//                    }
//                })
//                .build();
//        adLoader.loadAds(new AdRequest.Builder().build(), 3);
//        ad.setAdLoader(adLoader);

        recyclerView.setLayoutManager(new LinearLayoutManager(SecondActivity.this,RecyclerView.VERTICAL,false));
        MyAdapter mainAdapter = new MyAdapter(SecondActivity.this,itemList );
        GoogleNativeAdAdapter googleNativeAdAdapter = new GoogleNativeAdAdapter(
                new GoogleNativeAdAdapter.Param(
                        SecondActivity.this,
                        mainAdapter,
                        getString(R.string.test_ads_admob_native_id),
                        R.layout.ad_unified_medium,
                        2,
                        R.layout.layout_adlib,
                        R.id.id_ad));
        recyclerView.setAdapter(googleNativeAdAdapter);
    }

//    private void loadAd() {
//        AdRequest adRequest = new AdRequest.Builder().build();
//
//        RewardedAd.load(this, "ca-app-pub-3940256099942544/5224354917",
//                adRequest, new RewardedAdLoadCallback() {
//                    @Override
//                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
//                        // Handle the error.
//                        Log.d(TAG, loadAdError.getMessage());
//                        mRewardedAd = null;
//                    }
//
//                    @Override
//                    public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
//                        mRewardedAd = rewardedAd;
//                        Log.d(TAG, "Ad was loaded.");
//
//                        mRewardedAd.setFullScreenContentCallback(new FullScreenContentCallback() {
//                            @Override
//                            public void onAdShowedFullScreenContent() {
//                                // Called when ad is shown.
//                                Log.d(TAG, "Ad was shown.");
//                            }
//
//                            @Override
//                            public void onAdFailedToShowFullScreenContent(AdError adError) {
//                                // Called when ad fails to show.
//                                Log.d(TAG, "Ad failed to show.");
//                            }
//
//                            @Override
//                            public void onAdDismissedFullScreenContent() {
//                                // Called when ad is dismissed.
//                                // Set the ad reference to null so you don't show the ad a second time.
//                                Log.d(TAG, "Ad was dismissed.");
//                                mRewardedAd = null;
//                                loadAd();
//                            }
//                        });
//                    }
//                });
//
//    }

}
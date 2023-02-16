package com.solodroid.ads.sdk.format;

import static com.solodroid.ads.sdk.util.Constant.ADMOB;
import static com.solodroid.ads.sdk.util.Constant.AD_STATUS_ON;
import static com.solodroid.ads.sdk.util.Constant.APPLOVIN;
import static com.solodroid.ads.sdk.util.Constant.APPLOVIN_DISCOVERY;
import static com.solodroid.ads.sdk.util.Constant.APPLOVIN_MAX;
import static com.solodroid.ads.sdk.util.Constant.FAN;
import static com.solodroid.ads.sdk.util.Constant.FAN_BIDDING_ADMOB;
import static com.solodroid.ads.sdk.util.Constant.FAN_BIDDING_AD_MANAGER;
import static com.solodroid.ads.sdk.util.Constant.FAN_BIDDING_APPLOVIN_MAX;
import static com.solodroid.ads.sdk.util.Constant.FAN_BIDDING_IRONSOURCE;
import static com.solodroid.ads.sdk.util.Constant.GOOGLE_AD_MANAGER;
import static com.solodroid.ads.sdk.util.Constant.IRONSOURCE;
import static com.solodroid.ads.sdk.util.Constant.MOPUB;
import static com.solodroid.ads.sdk.util.Constant.NONE;
import static com.solodroid.ads.sdk.util.Constant.STARTAPP;
import static com.solodroid.ads.sdk.util.Constant.UNITY;

import android.app.Activity;
import android.util.Log;

import com.unity3d.mediation.IInitializationListener;
import com.unity3d.mediation.InitializationConfiguration;
import com.unity3d.mediation.UnityMediation;
import com.unity3d.mediation.errors.SdkInitializationError;

public class AdNetwork {

    public static class Initialize {

        private static final String TAG = "AdNetwork";
        Activity activity;
        private String adStatus = "";
        private String adNetwork = "";
        private String backupAdNetwork = "";
        private String adMobAppId = "";
        private String startappAppId = "0";
        private String unityGameId = "";
        private String appLovinSdkKey = "";
        private String mopubBannerId = "";
        private String ironSourceAppKey = "";
        private boolean debug = true;

        public Initialize(Activity activity) {
            this.activity = activity;
        }

        public Initialize build() {
            initAds();
            initBackupAds();
            return this;
        }

        public Initialize setAdStatus(String adStatus) {
            this.adStatus = adStatus;
            return this;
        }

        public Initialize setAdNetwork(String adNetwork) {
            this.adNetwork = adNetwork;
            return this;
        }

        public Initialize setBackupAdNetwork(String backupAdNetwork) {
            this.backupAdNetwork = backupAdNetwork;
            return this;
        }

        public Initialize setAdMobAppId(String adMobAppId) {
            this.adMobAppId = adMobAppId;
            return this;
        }

        public Initialize setStartappAppId(String startappAppId) {
            this.startappAppId = startappAppId;
            return this;
        }

        public Initialize setUnityGameId(String unityGameId) {
            this.unityGameId = unityGameId;
            return this;
        }

        public Initialize setAppLovinSdkKey(String appLovinSdkKey) {
            this.appLovinSdkKey = appLovinSdkKey;
            return this;
        }

        public Initialize setMopubBannerId(String mopubBannerId) {
            this.mopubBannerId = mopubBannerId;
            return this;
        }

        public Initialize setIronSourceAppKey(String ironSourceAppKey) {
            this.ironSourceAppKey = ironSourceAppKey;
            return this;
        }

        public Initialize setDebug(boolean debug) {
            this.debug = debug;
            return this;
        }

        public void initAds() {
            if (adStatus.equals(AD_STATUS_ON)) {
                switch (adNetwork) {
                    case ADMOB:
                    case GOOGLE_AD_MANAGER:
                    case FAN_BIDDING_ADMOB:
                    case FAN_BIDDING_AD_MANAGER:
                        break;
                    case FAN:
                        break;
                    case STARTAPP:
                        break;

                    case UNITY:
                        InitializationConfiguration configuration = InitializationConfiguration.builder()
                                .setGameId(unityGameId)
                                .setInitializationListener(new IInitializationListener() {
                                    @Override
                                    public void onInitializationComplete() {
                                        Log.d(TAG, "Unity Mediation is successfully initialized. with ID : " + unityGameId);
                                    }

                                    @Override
                                    public void onInitializationFailed(SdkInitializationError errorCode, String msg) {
                                        Log.d(TAG, "Unity Mediation Failed to Initialize : " + msg);
                                    }
                                }).build();
                        UnityMediation.initialize(configuration);
                        break;
                    case APPLOVIN:
                    case APPLOVIN_MAX:
                    case FAN_BIDDING_APPLOVIN_MAX:
                        break;

                    case APPLOVIN_DISCOVERY:
                        break;

                    case MOPUB:
                        //Mopub has been acquired by AppLovin
                        break;

                    case IRONSOURCE:
                    case FAN_BIDDING_IRONSOURCE:
                        break;
                }
                Log.d(TAG, "[" + adNetwork + "] is selected as Primary Ads");
            }
        }

        public void initBackupAds() {
            if (adStatus.equals(AD_STATUS_ON)) {
                switch (backupAdNetwork) {
                    case ADMOB:
                    case GOOGLE_AD_MANAGER:
                    case FAN_BIDDING_ADMOB:
                    case FAN_BIDDING_AD_MANAGER:
                        break;
                    case STARTAPP:
                        break;
                    case UNITY:
                        InitializationConfiguration configuration = InitializationConfiguration.builder()
                                .setGameId(unityGameId)
                                .setInitializationListener(new IInitializationListener() {
                                    @Override
                                    public void onInitializationComplete() {
                                        Log.d(TAG, "Unity Mediation is successfully initialized. with ID : " + unityGameId);
                                    }

                                    @Override
                                    public void onInitializationFailed(SdkInitializationError errorCode, String msg) {
                                        Log.d(TAG, "Unity Mediation Failed to Initialize : " + msg);
                                    }
                                }).build();
                        UnityMediation.initialize(configuration);
                        break;
                    case APPLOVIN:
                    case APPLOVIN_MAX:
                    case FAN_BIDDING_APPLOVIN_MAX:
                        break;

                    case APPLOVIN_DISCOVERY:
                        break;

                    case MOPUB:
                        //Mopub has been acquired by AppLovin
                        break;

                    case IRONSOURCE:
                    case FAN_BIDDING_IRONSOURCE:
                        break;

                    case NONE:
                        //do nothing
                        break;
                }
                Log.d(TAG, "[" + backupAdNetwork + "] is selected as Backup Ads");
            }
        }

    }

}

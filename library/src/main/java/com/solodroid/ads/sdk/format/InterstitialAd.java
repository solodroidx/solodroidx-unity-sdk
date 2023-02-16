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

import com.unity3d.mediation.IInterstitialAdLoadListener;
import com.unity3d.mediation.IInterstitialAdShowListener;
import com.unity3d.mediation.errors.LoadError;
import com.unity3d.mediation.errors.ShowError;

public class InterstitialAd {

    public static class Builder {

        private static final String TAG = "AdNetwork";
        private final Activity activity;
        private com.unity3d.mediation.InterstitialAd unityInterstitialAd;
        private int retryAttempt;
        private int counter = 1;

        private String adStatus = "";
        private String adNetwork = "";
        private String backupAdNetwork = "";
        private String adMobInterstitialId = "";
        private String googleAdManagerInterstitialId = "";
        private String fanInterstitialId = "";
        private String unityInterstitialId = "";
        private String appLovinInterstitialId = "";
        private String appLovinInterstitialZoneId = "";
        private String mopubInterstitialId = "";
        private String ironSourceInterstitialId = "";
        private int placementStatus = 1;
        private int interval = 3;

        private boolean legacyGDPR = false;

        public Builder(Activity activity) {
            this.activity = activity;
        }

        public Builder build() {
            loadInterstitialAd();
            return this;
        }

        public void show() {
            showInterstitialAd();
        }

        public Builder setAdStatus(String adStatus) {
            this.adStatus = adStatus;
            return this;
        }

        public Builder setAdNetwork(String adNetwork) {
            this.adNetwork = adNetwork;
            return this;
        }

        public Builder setBackupAdNetwork(String backupAdNetwork) {
            this.backupAdNetwork = backupAdNetwork;
            return this;
        }

        public Builder setAdMobInterstitialId(String adMobInterstitialId) {
            this.adMobInterstitialId = adMobInterstitialId;
            return this;
        }

        public Builder setGoogleAdManagerInterstitialId(String googleAdManagerInterstitialId) {
            this.googleAdManagerInterstitialId = googleAdManagerInterstitialId;
            return this;
        }

        public Builder setFanInterstitialId(String fanInterstitialId) {
            this.fanInterstitialId = fanInterstitialId;
            return this;
        }

        public Builder setUnityInterstitialId(String unityInterstitialId) {
            this.unityInterstitialId = unityInterstitialId;
            return this;
        }

        public Builder setAppLovinInterstitialId(String appLovinInterstitialId) {
            this.appLovinInterstitialId = appLovinInterstitialId;
            return this;
        }

        public Builder setAppLovinInterstitialZoneId(String appLovinInterstitialZoneId) {
            this.appLovinInterstitialZoneId = appLovinInterstitialZoneId;
            return this;
        }

        public Builder setMopubInterstitialId(String mopubInterstitialId) {
            this.mopubInterstitialId = mopubInterstitialId;
            return this;
        }

        public Builder setIronSourceInterstitialId(String ironSourceInterstitialId) {
            this.ironSourceInterstitialId = ironSourceInterstitialId;
            return this;
        }

        public Builder setPlacementStatus(int placementStatus) {
            this.placementStatus = placementStatus;
            return this;
        }

        public Builder setInterval(int interval) {
            this.interval = interval;
            return this;
        }

        public Builder setLegacyGDPR(boolean legacyGDPR) {
            this.legacyGDPR = legacyGDPR;
            return this;
        }

        public void loadInterstitialAd() {
            if (adStatus.equals(AD_STATUS_ON) && placementStatus != 0) {
                switch (adNetwork) {
                    case ADMOB:
                    case FAN_BIDDING_ADMOB:
                       break;

                    case GOOGLE_AD_MANAGER:
                    case FAN_BIDDING_AD_MANAGER:
                        break;

                    case FAN:
                        break;

                    case STARTAPP:
                        break;

                    case UNITY:
                        unityInterstitialAd = new com.unity3d.mediation.InterstitialAd(activity, unityInterstitialId);
                        final IInterstitialAdLoadListener unityAdLoadListener = new IInterstitialAdLoadListener() {
                            @Override
                            public void onInterstitialLoaded(com.unity3d.mediation.InterstitialAd interstitialAd) {
                                Log.d(TAG, "unity interstitial ad loaded");
                            }

                            @Override
                            public void onInterstitialFailedLoad(com.unity3d.mediation.InterstitialAd interstitialAd, LoadError loadError, String s) {
                                Log.e(TAG, "Unity Ads failed to load ad : " + unityInterstitialId + " : error : " + s);
                                loadBackupInterstitialAd();
                            }

                        };
                        unityInterstitialAd.load(unityAdLoadListener);
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
            }
        }

        public void loadBackupInterstitialAd() {
            if (adStatus.equals(AD_STATUS_ON) && placementStatus != 0) {
                switch (backupAdNetwork) {
                    case ADMOB:
                    case FAN_BIDDING_ADMOB:
                        break;

                    case GOOGLE_AD_MANAGER:
                    case FAN_BIDDING_AD_MANAGER:
                        break;

                    case FAN:
                        break;

                    case STARTAPP:
                        break;

                    case UNITY:
                        unityInterstitialAd = new com.unity3d.mediation.InterstitialAd(activity, unityInterstitialId);
                        final IInterstitialAdLoadListener unityAdLoadListener = new IInterstitialAdLoadListener() {
                            @Override
                            public void onInterstitialLoaded(com.unity3d.mediation.InterstitialAd interstitialAd) {
                                Log.d(TAG, "unity interstitial ad loaded");
                            }

                            @Override
                            public void onInterstitialFailedLoad(com.unity3d.mediation.InterstitialAd interstitialAd, LoadError loadError, String s) {
                                Log.e(TAG, "Unity Ads failed to load ad : " + unityInterstitialId + " : error : " + s);
                            }

                        };
                        unityInterstitialAd.load(unityAdLoadListener);
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
            }
        }

        public void showInterstitialAd() {
            if (adStatus.equals(AD_STATUS_ON) && placementStatus != 0) {
                if (counter == interval) {
                    switch (adNetwork) {
                        case ADMOB:
                        case FAN_BIDDING_ADMOB:
                            break;

                        case GOOGLE_AD_MANAGER:
                        case FAN_BIDDING_AD_MANAGER:
                            break;

                        case FAN:
                            break;

                        case STARTAPP:
                            break;

                        case UNITY:
                            final IInterstitialAdShowListener showListener = new IInterstitialAdShowListener() {
                                @Override
                                public void onInterstitialShowed(com.unity3d.mediation.InterstitialAd interstitialAd) {

                                }

                                @Override
                                public void onInterstitialClicked(com.unity3d.mediation.InterstitialAd interstitialAd) {

                                }

                                @Override
                                public void onInterstitialClosed(com.unity3d.mediation.InterstitialAd interstitialAd) {

                                }

                                @Override
                                public void onInterstitialFailedShow(com.unity3d.mediation.InterstitialAd interstitialAd, ShowError showError, String s) {
                                    Log.d(TAG, "unity ads show failure");
                                    showBackupInterstitialAd();
                                }
                            };
                            unityInterstitialAd.show(showListener);
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
                    counter = 1;
                } else {
                    counter++;
                }
                Log.d(TAG, "Current counter : " + counter);
            }
        }

        public void showBackupInterstitialAd() {
            if (adStatus.equals(AD_STATUS_ON) && placementStatus != 0) {
                Log.d(TAG, "Show Backup Interstitial Ad [" + backupAdNetwork.toUpperCase() + "]");
                switch (backupAdNetwork) {
                    case ADMOB:
                    case FAN_BIDDING_ADMOB:

                        break;

                    case GOOGLE_AD_MANAGER:
                    case FAN_BIDDING_AD_MANAGER:

                        break;

                    case FAN:

                        break;

                    case STARTAPP:

                        break;

                    case UNITY:
                        final IInterstitialAdShowListener showListener = new IInterstitialAdShowListener() {
                            @Override
                            public void onInterstitialShowed(com.unity3d.mediation.InterstitialAd interstitialAd) {

                            }

                            @Override
                            public void onInterstitialClicked(com.unity3d.mediation.InterstitialAd interstitialAd) {

                            }

                            @Override
                            public void onInterstitialClosed(com.unity3d.mediation.InterstitialAd interstitialAd) {

                            }

                            @Override
                            public void onInterstitialFailedShow(com.unity3d.mediation.InterstitialAd interstitialAd, ShowError showError, String s) {
                                Log.d(TAG, "unity ads show failure");
                            }
                        };
                        unityInterstitialAd.show(showListener);
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
            }
        }

    }

}

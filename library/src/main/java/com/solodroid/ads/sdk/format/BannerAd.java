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
import static com.solodroid.ads.sdk.util.Constant.UNITY_ADS_BANNER_HEIGHT_MEDIUM;
import static com.solodroid.ads.sdk.util.Constant.UNITY_ADS_BANNER_WIDTH_MEDIUM;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.solodroid.ads.sdk.R;
import com.unity3d.services.banners.BannerErrorInfo;
import com.unity3d.services.banners.BannerView;
import com.unity3d.services.banners.UnityBannerSize;

public class BannerAd {

    public static class Builder {

        private static final String TAG = "AdNetwork";
        private final Activity activity;

        private String adStatus = "";
        private String adNetwork = "";
        private String backupAdNetwork = "";
        private String adMobBannerId = "";
        private String googleAdManagerBannerId = "";
        private String fanBannerId = "";
        private String unityBannerId = "";
        private String appLovinBannerId = "";
        private String appLovinBannerZoneId = "";
        private String mopubBannerId = "";
        private String ironSourceBannerId = "";
        private int placementStatus = 1;
        private boolean darkTheme = false;
        private boolean legacyGDPR = false;

        public Builder(Activity activity) {
            this.activity = activity;
        }

        public Builder build() {
            loadBannerAd();
            return this;
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

        public Builder setAdMobBannerId(String adMobBannerId) {
            this.adMobBannerId = adMobBannerId;
            return this;
        }

        public Builder setGoogleAdManagerBannerId(String googleAdManagerBannerId) {
            this.googleAdManagerBannerId = googleAdManagerBannerId;
            return this;
        }

        public Builder setFanBannerId(String fanBannerId) {
            this.fanBannerId = fanBannerId;
            return this;
        }

        public Builder setUnityBannerId(String unityBannerId) {
            this.unityBannerId = unityBannerId;
            return this;
        }

        public Builder setAppLovinBannerId(String appLovinBannerId) {
            this.appLovinBannerId = appLovinBannerId;
            return this;
        }

        public Builder setAppLovinBannerZoneId(String appLovinBannerZoneId) {
            this.appLovinBannerZoneId = appLovinBannerZoneId;
            return this;
        }

        public Builder setMopubBannerId(String mopubBannerId) {
            this.mopubBannerId = mopubBannerId;
            return this;
        }

        public Builder setIronSourceBannerId(String ironSourceBannerId) {
            this.ironSourceBannerId = ironSourceBannerId;
            return this;
        }

        public Builder setPlacementStatus(int placementStatus) {
            this.placementStatus = placementStatus;
            return this;
        }

        public Builder setDarkTheme(boolean darkTheme) {
            this.darkTheme = darkTheme;
            return this;
        }

        public Builder setLegacyGDPR(boolean legacyGDPR) {
            this.legacyGDPR = legacyGDPR;
            return this;
        }

        public void loadBannerAd() {
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
                        RelativeLayout unityAdView = activity.findViewById(R.id.unity_banner_view_container);
                        BannerView bottomBanner = new BannerView(activity, unityBannerId, new UnityBannerSize(UNITY_ADS_BANNER_WIDTH_MEDIUM, UNITY_ADS_BANNER_HEIGHT_MEDIUM));
                        bottomBanner.setListener(new BannerView.IListener() {
                            @Override
                            public void onBannerLoaded(BannerView bannerView) {
                                unityAdView.setVisibility(View.VISIBLE);
                                Log.d("Unity_banner", "ready");
                            }

                            @Override
                            public void onBannerClick(BannerView bannerView) {

                            }

                            @Override
                            public void onBannerFailedToLoad(BannerView bannerView, BannerErrorInfo bannerErrorInfo) {
                                Log.d("SupportTest", "Banner Error" + bannerErrorInfo);
                                unityAdView.setVisibility(View.GONE);
                                loadBackupBannerAd();
                            }

                            @Override
                            public void onBannerLeftApplication(BannerView bannerView) {

                            }
                        });
                        unityAdView.addView(bottomBanner);
                        bottomBanner.load();
                        Log.d(TAG, adNetwork + " Banner Ad unit Id : " + unityBannerId);
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
                Log.d(TAG, "Banner Ad is enabled");
            } else {
                Log.d(TAG, "Banner Ad is disabled");
            }
        }

        public void loadBackupBannerAd() {
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
                        RelativeLayout unityAdView = activity.findViewById(R.id.unity_banner_view_container);
                        BannerView bottomBanner = new BannerView(activity, unityBannerId, new UnityBannerSize(UNITY_ADS_BANNER_WIDTH_MEDIUM, UNITY_ADS_BANNER_HEIGHT_MEDIUM));
                        bottomBanner.setListener(new BannerView.IListener() {
                            @Override
                            public void onBannerLoaded(BannerView bannerView) {
                                unityAdView.setVisibility(View.VISIBLE);
                                Log.d("Unity_banner", "ready");
                            }

                            @Override
                            public void onBannerClick(BannerView bannerView) {

                            }

                            @Override
                            public void onBannerFailedToLoad(BannerView bannerView, BannerErrorInfo bannerErrorInfo) {
                                Log.d("SupportTest", "Banner Error" + bannerErrorInfo);
                                unityAdView.setVisibility(View.GONE);
                            }

                            @Override
                            public void onBannerLeftApplication(BannerView bannerView) {

                            }
                        });
                        unityAdView.addView(bottomBanner);
                        bottomBanner.load();
                        Log.d(TAG, adNetwork + " Banner Ad unit Id : " + unityBannerId);
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
                Log.d(TAG, "Banner Ad is enabled");
            } else {
                Log.d(TAG, "Banner Ad is disabled");
            }
        }

        public void destroyAndDetachBanner() {

        }

    }

}

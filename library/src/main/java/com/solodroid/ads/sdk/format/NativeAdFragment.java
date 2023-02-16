package com.solodroid.ads.sdk.format;

import android.app.Activity;
import android.view.View;

import com.solodroid.ads.sdk.R;

public class NativeAdFragment {

    public static class Builder {

        private static final String TAG = "AdNetwork";
        private final Activity activity;
        View view;

        private String adStatus = "";
        private String adNetwork = "";
        private String backupAdNetwork = "";
        private String adMobNativeId = "";
        private String adManagerNativeId = "";
        private String fanNativeId = "";
        private String appLovinNativeId = "";
        private String appLovinDiscMrecZoneId = "";
        private int placementStatus = 1;
        private boolean darkTheme = false;
        private boolean legacyGDPR = false;

        private String nativeAdStyle = "";
        private int nativeBackgroundLight = R.color.color_native_background_light;
        private int nativeBackgroundDark = R.color.color_native_background_dark;

        public Builder(Activity activity) {
            this.activity = activity;
        }

        public Builder build() {
            loadNativeAd();
            return this;
        }

        public Builder setPadding(int left, int top, int right, int bottom) {
            setNativeAdPadding(left, top, right, bottom);
            return this;
        }

        public Builder setMargin(int left, int top, int right, int bottom) {
            setNativeAdMargin(left, top, right, bottom);
            return this;
        }

        public Builder setBackgroundResource(int drawableBackground) {
            setNativeAdBackgroundResource(drawableBackground);
            return this;
        }

        public Builder setView(View view) {
            this.view = view;
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

        public Builder setAdMobNativeId(String adMobNativeId) {
            this.adMobNativeId = adMobNativeId;
            return this;
        }

        public Builder setAppLovinDiscoveryMrecZoneId(String appLovinDiscMrecZoneId) {
            this.appLovinDiscMrecZoneId = appLovinDiscMrecZoneId;
            return this;
        }

        public Builder setAdManagerNativeId(String adManagerNativeId) {
            this.adManagerNativeId = adManagerNativeId;
            return this;
        }

        public Builder setFanNativeId(String fanNativeId) {
            this.fanNativeId = fanNativeId;
            return this;
        }

        public Builder setAppLovinNativeId(String appLovinNativeId) {
            this.appLovinNativeId = appLovinNativeId;
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

        public Builder setNativeAdStyle(String nativeAdStyle) {
            this.nativeAdStyle = nativeAdStyle;
            return this;
        }

        public Builder setNativeAdBackgroundColor(int colorLight, int colorDark) {
            this.nativeBackgroundLight = colorLight;
            this.nativeBackgroundDark = colorDark;
            return this;
        }

        public void loadNativeAd() {

        }

        public void loadBackupNativeAd() {

        }

        public void setNativeAdPadding(int left, int top, int right, int bottom) {

        }

        public void setNativeAdMargin(int left, int top, int right, int bottom) {

        }

        public void setMargins(View view, int left, int top, int right, int bottom) {

        }

        public void setNativeAdBackgroundResource(int drawableBackground) {

        }

    }

}
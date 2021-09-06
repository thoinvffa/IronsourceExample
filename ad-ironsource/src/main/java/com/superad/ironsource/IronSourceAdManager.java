package com.superad.ironsource;

import android.app.Activity;
import android.util.Log;

import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.ironsource.mediationsdk.IronSource;
import com.ironsource.mediationsdk.integration.IntegrationHelper;
import com.ironsource.mediationsdk.sdk.InterstitialListener;

public class IronSourceAdManager {
    public static boolean debugMode = false;

    public static void onResume(Activity activity) {
        IronSource.onResume(activity);
    }

    public static void onPause(Activity activity) {
        IronSource.onPause(activity);
    }

    public static void init(Activity activity, String appKey, IronSource.AD_UNIT... adUnits) {
        try {
            if (debugMode) {
                IntegrationHelper.validateIntegration(activity);
                IronSource.setAdaptersDebug(true);
            }

            String advertiserId = IronSource.getAdvertiserId(activity);
            IronSource.setUserId(advertiserId);
            if (adUnits != null) {
                IronSource.init(activity, appKey, adUnits);
            } else {
                IronSource.init(activity, appKey);
            }
            IronSource.shouldTrackNetworkState(activity, true);
        } catch (Exception e) {
            e.printStackTrace();
            FirebaseCrashlytics.getInstance().recordException(e);
        }
    }

    public static void loadInterstitial(InterstitialListener listener) {
        try {
            IronSource.loadInterstitial();
            IronSource.setInterstitialListener(listener);
        } catch (Exception e) {
            e.printStackTrace();
            FirebaseCrashlytics.getInstance().recordException(e);
        }
    }

    public static boolean isInterstitialReady() {
        return IronSource.isInterstitialReady();
    }

    public static boolean showInterstitial() {
        try {
            if (isInterstitialReady()) {
                IronSource.showInterstitial();
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            FirebaseCrashlytics.getInstance().recordException(e);
        }
        return false;
    }

}

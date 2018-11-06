package org.artoolkitx.arx.arsquaretracking;

import android.app.Application;
import android.content.Context;

import org.artoolkitx.arx.arxj.assets.AssetHelper;

public class eApplication extends Application {

    private Context mContext;

    public Context getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getContext();
        this.initializeInstance();
    }

    // Here we do one-off initialisation which should apply to all activities
    // in the application.
    protected void initializeInstance() {

        // Unpack assets to cache directory so native library can read them.
        // N.B.: If contents of assets folder changes, be sure to increment the
        // versionCode integer in the modules build.gradle file.

        AssetHelper assetHelper = new AssetHelper(getAssets());
        assetHelper.cacheAssetFolder(this, "Data");
        assetHelper.cacheAssetFolder(this, "cparam_cache");
    }
}

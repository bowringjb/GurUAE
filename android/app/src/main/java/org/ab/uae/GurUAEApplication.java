package org.ab.uae;

import android.app.Application;
import com.facebook.soloader.SoLoader;

public class GurUAEApplication extends Application  {

    @Override
    public void onCreate() {
        super.onCreate();
        SoLoader.init(this, false);
    }
}

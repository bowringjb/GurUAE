package rn.modules;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.modules.toast.ToastModule;
import com.facebook.react.uimanager.ViewManager;

import org.ab.uae.GurUAEActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RNInteropPackage implements ReactPackage {

    private GurUAEActivity _activity;

    public RNInteropPackage(GurUAEActivity activity) {
        _activity = activity;
    }

    @Override
    public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
        return Collections.emptyList();
    }

    @Override
    public List<NativeModule> createNativeModules(
            ReactApplicationContext reactContext) {
        List<NativeModule> modules = new ArrayList<>();

        modules.add(new RNInteropModule(reactContext, _activity));

        return modules;
    }

}

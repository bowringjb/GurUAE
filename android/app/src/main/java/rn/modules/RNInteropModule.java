package rn.modules;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import org.ab.uae.GurUAEActivity;

import java.util.Map;
import java.util.HashMap;

public class RNInteropModule extends ReactContextBaseJavaModule {
    private static ReactApplicationContext reactContext;

    private static final String DURATION_SHORT_KEY = "SHORT";
    private static final String DURATION_LONG_KEY = "LONG";

    private GurUAEActivity guruActivity;

    public RNInteropModule(ReactApplicationContext context, GurUAEActivity activity) {
        super(context);
        reactContext = context;
        guruActivity = activity;
    }

    @NonNull
    @Override
    public String getName() {
        return "RNInteropModule";
    }

    @Override
    public Map<String, Object> getConstants() {
        final Map<String, Object> constants = new HashMap<>();
        constants.put(DURATION_SHORT_KEY, Toast.LENGTH_SHORT);
        constants.put(DURATION_LONG_KEY, Toast.LENGTH_LONG);
        return constants;
    }

    @ReactMethod
    public void startAmiga() {
        guruActivity.start_amiga();
    }

    @ReactMethod
    public void configureAmiga() {
        guruActivity.configure_amiga();
    }
}

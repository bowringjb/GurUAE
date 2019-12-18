package org.ab.uae;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactPackage;
import com.facebook.react.ReactRootView;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.shell.MainReactPackage;

import java.util.Arrays;

import rn.modules.RNInteropPackage;


public class GurUAEActivity extends Activity implements DefaultHardwareBackBtnHandler {

	private final int OVERLAY_PERMISSION_REQ_CODE = 1;
	private ReactRootView mReactRootView;
	private ReactInstanceManager mReactInstanceManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			if (!android.provider.Settings.canDrawOverlays(this)) {
				Intent intent = new Intent(android.provider.Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
						Uri.parse("package:" + getPackageName()));
				startActivityForResult(intent, OVERLAY_PERMISSION_REQ_CODE);
			}
		}

		mReactRootView = new ReactRootView(this);
		mReactInstanceManager = ReactInstanceManager.builder()
			.setApplication(getApplication())
			.setCurrentActivity(this)
			.setBundleAssetName("index.android.bundle")
			.setJSMainModulePath("index")
			.addPackages(Arrays.<ReactPackage>asList(
					new MainReactPackage(),
					new RNInteropPackage(this)
			))
			.setUseDeveloperSupport(BuildConfig.DEBUG)
			.setInitialLifecycleState(LifecycleState.RESUMED)
			.build();

		mReactRootView.startReactApplication(mReactInstanceManager, "MyReactNativeApp", null);

		setContentView(mReactRootView);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == OVERLAY_PERMISSION_REQ_CODE) {
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
				if (!android.provider.Settings.canDrawOverlays(this)) {
					// SYSTEM_ALERT_WINDOW permission not granted
				}
			}
		}
		mReactInstanceManager.onActivityResult( this, requestCode, resultCode, data );
	}

	@Override
	protected void onPause() {
		super.onPause();

		if (mReactInstanceManager != null) {
			mReactInstanceManager.onHostPause(this);
		}
	}

	@Override
	protected void onResume() {
		super.onResume();

		if (mReactInstanceManager != null) {
			mReactInstanceManager.onHostResume(this, this);
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		if (mReactInstanceManager != null) {
			mReactInstanceManager.onHostDestroy(this);
		}
		if (mReactRootView != null) {
			mReactRootView.unmountReactApplication();
		}
	}

	@Override
	public void onBackPressed() {
		if (mReactInstanceManager != null) {
			mReactInstanceManager.onBackPressed();
		} else {
			super.onBackPressed();
		}
	}

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_MENU && mReactInstanceManager != null) {
			mReactInstanceManager.showDevOptionsDialog();
			return true;
		}
		return super.onKeyUp(keyCode, event);
	}

	@Override
	public void invokeDefaultOnBackPressed() {
		super.onBackPressed();
	}

	public void configure_amiga() {
		Intent settingsIntent = new Intent();
   		settingsIntent.setClass(this, Settings.class);
   		startActivityForResult(settingsIntent, 0);
	}
	
	public void start_amiga() {
		Intent settingsIntent = new Intent();
   		settingsIntent.setClass(this, DemoActivity.class);
   		startActivityForResult(settingsIntent, 20);
	}

}

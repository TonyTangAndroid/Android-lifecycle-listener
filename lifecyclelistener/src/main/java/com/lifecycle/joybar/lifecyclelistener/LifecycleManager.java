package com.lifecycle.joybar.lifecyclelistener;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;
import android.text.TextUtils;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import com.lifecycle.joybar.lifecyclelistener.fragment.SupportLifecycleListenerFragment;
import com.lifecycle.joybar.lifecyclelistener.interfaces.DefaultLifecycleObserver;
import com.lifecycle.joybar.lifecyclelistener.util.CheckUtils;

/** Created by joybar on 2017/6/29. */
public class LifecycleManager {

  private static final String FRAGMENT_TAG = "ActivityFragmentLifecycle";
  private static final String TAG = "LifecycleManager";
  private static volatile LifecycleManager mInstance;
  private String fragmentTagName;

  public LifecycleManager() {}

  public LifecycleManager(String fragmentTagName) {
    this.fragmentTagName = fragmentTagName;
  }

  @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
  private static boolean isDestroyed(Activity activity) {
    return activity.isDestroyed();
  }

  private String getFragmentTag() {
    return fragmentTagName;
  }

  private void setDeFaultFragmentName(Context context) {
    if (TextUtils.isEmpty(fragmentTagName)) {
      fragmentTagName = context.getClass().getName();
    }
  }

  public void registerLifecycleListener(
      Context context, DefaultLifecycleObserver lifecycleListener) {

    if (context == null) {
      throw new IllegalArgumentException("You cannot start a load on a null Context");
    } else if (CheckUtils.isOnMainThread() && !(context instanceof Application)) {
      setDeFaultFragmentName(context);
      if (context instanceof FragmentActivity) {
        handleObserveLifecycle((FragmentActivity) context, lifecycleListener);
      } else if (context instanceof ContextWrapper) {
        handleObserveLifecycle(((ContextWrapper) context).getBaseContext(), lifecycleListener);
      }
    }
  }

  public void registerLifecycleListener(
      Fragment fragment, DefaultLifecycleObserver lifecycleListener) {
    if (fragment.getActivity() == null) {
      throw new IllegalArgumentException(
          "You cannot start a load on a fragment before it is attached");
    }
    FragmentManager fm = fragment.getChildFragmentManager();
    SupportLifecycleListenerFragment supportLifecycleListenerFragment =
        getSupportRequestManagerFragment(fm);
    FragmentLifecycle fragmentLifecycle =
        getActivitySupportFragmentLifecycle(supportLifecycleListenerFragment);
    fragmentLifecycle.addListener(lifecycleListener);
  }

  public void handleObserveLifecycle(
      FragmentActivity activity, DefaultLifecycleObserver lifecycleListener) {
    // Log.d(TAG, "this context type  is FragmentActivity");
    if (isDestroyed(activity)) {
      return;
    }
    FragmentManager fm = activity.getSupportFragmentManager();
    SupportLifecycleListenerFragment fragment = getSupportRequestManagerFragment(fm);
    FragmentLifecycle fragmentLifecycle = getActivitySupportFragmentLifecycle(fragment);
    fragmentLifecycle.addListener(lifecycleListener);
  }

  private void handleObserveLifecycle(Context context, DefaultLifecycleObserver lifecycleListener) {
    // Log.d(TAG, "this context type is Context");
  }

  private SupportLifecycleListenerFragment getSupportRequestManagerFragment(FragmentManager fm) {
    SupportLifecycleListenerFragment current =
        (SupportLifecycleListenerFragment) fm.findFragmentByTag(getFragmentTag());
    if (current == null) {
      current = new SupportLifecycleListenerFragment();
      fm.beginTransaction().add(current, getFragmentTag()).commitAllowingStateLoss();
    }
    return current;
  }

  private FragmentLifecycle getActivitySupportFragmentLifecycle(
      SupportLifecycleListenerFragment fragment) {
    FragmentLifecycle lifecycleListener = fragment.getFragmentLifecycle();
    if (null == lifecycleListener) {
      lifecycleListener = new FragmentLifecycle();
    }
    fragment.setLifecycle(lifecycleListener);
    return lifecycleListener;
  }
}

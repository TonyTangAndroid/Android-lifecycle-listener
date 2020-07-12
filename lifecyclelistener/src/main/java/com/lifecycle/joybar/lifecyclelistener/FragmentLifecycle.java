package com.lifecycle.joybar.lifecyclelistener;

import android.os.Bundle;
import androidx.lifecycle.LifecycleOwner;
import com.lifecycle.joybar.lifecyclelistener.interfaces.Lifecycle;
import com.lifecycle.joybar.lifecyclelistener.interfaces.LifecycleListener;
import com.lifecycle.joybar.lifecyclelistener.util.CheckUtils;
import java.util.HashSet;
import java.util.Set;

/** Created by joybar on 2017/6/29. */
public class FragmentLifecycle implements Lifecycle {

  private static String TAG = "ActivityFragment";
  // private final Set<LifecycleListener> lifecycleListeners = Collections.newSetFromMap(new
  // WeakHashMap<LifecycleListener, Boolean>());
  private final Set<LifecycleListener> lifecycleListeners = new HashSet<>();

  @Override
  public void addListener(LifecycleListener listener) {
    lifecycleListeners.add(listener);
  }

  public void onActivityCreated(Bundle savedInstanceState) {}

  public void onStart(LifecycleOwner lifecycleOwner) {
    for (LifecycleListener lifecycleListener : CheckUtils.getSnapshot(lifecycleListeners)) {
      lifecycleListener.onStart(lifecycleOwner);
    }
  }

  public void onResume(LifecycleOwner lifecycleOwner) {
    for (LifecycleListener lifecycleListener : CheckUtils.getSnapshot(lifecycleListeners)) {
      lifecycleListener.onResume(lifecycleOwner);
    }
  }

  public void onPause(LifecycleOwner lifecycleOwner) {
    for (LifecycleListener lifecycleListener : CheckUtils.getSnapshot(lifecycleListeners)) {
      lifecycleListener.onPause(lifecycleOwner);
    }
  }

  public void onStop(LifecycleOwner lifecycleOwner) {
    for (LifecycleListener lifecycleListener : CheckUtils.getSnapshot(lifecycleListeners)) {
      lifecycleListener.onStop(lifecycleOwner);
    }
  }

  public void onDestroy(LifecycleOwner lifecycleOwner) {
    for (LifecycleListener lifecycleListener : CheckUtils.getSnapshot(lifecycleListeners)) {
      lifecycleListener.onDestroy(lifecycleOwner);
    }
  }

  private void recycleListener(LifecycleListener listener) {
    lifecycleListeners.remove(listener);
    listener = null;
  }
}

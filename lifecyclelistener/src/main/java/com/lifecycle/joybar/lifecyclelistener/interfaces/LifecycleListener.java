package com.lifecycle.joybar.lifecyclelistener.interfaces;

import androidx.lifecycle.LifecycleOwner;

/** Created by joybar on 2017/6/29. */
public interface LifecycleListener {

  void onStart(LifecycleOwner lifecycleOwner);

  void onResume(LifecycleOwner lifecycleOwner);

  void onPause(LifecycleOwner lifecycleOwner);

  void onStop(LifecycleOwner lifecycleOwner);

  void onDestroy(LifecycleOwner lifecycleOwner);
}

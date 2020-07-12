package com.lifecycle.joybar.androidlifecyclelistener.util;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/** Created by joybar on 2017/6/9. */
public class ActivityUtils {

  public static void addFragmentToActivity(
      @NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, int frameId) {
    fragmentManager = CheckUtils.checkNotNull(fragmentManager);
    fragment = CheckUtils.checkNotNull(fragment);
    FragmentTransaction transaction = fragmentManager.beginTransaction();
    transaction.add(frameId, fragment);
    transaction.commit();
  }

  public static void addFragmentToActivity(
      @NonNull android.app.FragmentManager fragmentManager,
      @NonNull android.app.Fragment fragment,
      int frameId) {
    fragmentManager = CheckUtils.checkNotNull(fragmentManager);
    fragment = CheckUtils.checkNotNull(fragment);
    android.app.FragmentTransaction transaction = fragmentManager.beginTransaction();
    transaction.add(frameId, fragment);
    transaction.commit();
  }
}

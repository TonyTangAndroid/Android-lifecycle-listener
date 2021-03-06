package com.lifecycle.joybar.lifecyclelistener.fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import com.lifecycle.joybar.lifecyclelistener.FragmentLifecycle;

/** Created by joybar on 2017/6/29. */
public class SupportLifecycleListenerFragment extends Fragment {

  private static String TAG = "SupportLifecycleListenerFragment";
  private FragmentLifecycle fragmentLifecycle;

  public SupportLifecycleListenerFragment() {
    this.fragmentLifecycle = new FragmentLifecycle();
  }

  public FragmentLifecycle getFragmentLifecycle() {
    return fragmentLifecycle;
  }

  public void setLifecycle(FragmentLifecycle lifecycle) {
    this.fragmentLifecycle = lifecycle;
  }

  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    fragmentLifecycle.onActivityCreated(savedInstanceState);
  }

  @Override
  public void onStart() {
    super.onStart();
    fragmentLifecycle.onStart(this);
  }

  @Override
  public void onResume() {
    super.onResume();
    fragmentLifecycle.onResume(this);
  }

  @Override
  public void onPause() {
    super.onPause();
    fragmentLifecycle.onPause(this);
  }

  @Override
  public void onStop() {
    super.onStop();
    fragmentLifecycle.onStop(this);
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    fragmentLifecycle.onDestroy(this);
  }

  @Override
  public void onLowMemory() {
    super.onLowMemory();
  }
}

package com.lifecycle.joybar.androidlifecyclelistener;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.lifecycle.joybar.androidlifecyclelistener.lifetest.ActivityLifecycle;
import com.lifecycle.joybar.androidlifecyclelistener.lifetest.FragmentActivityLifecycle;
import com.lifecycle.joybar.androidlifecyclelistener.lifetest.drop.DropInnerObjActivity;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  public void onClick(View v) {
    int id = v.getId();
    switch (id) {
      case R.id.button1:
        ActivityLifecycle.launch(MainActivity.this);
        break;
      case R.id.button2:
        FragmentActivityLifecycle.launch(MainActivity.this);
        break;
      case R.id.button3:
        startActivity(new Intent(MainActivity.this, DropInnerObjActivity.class));

      default:
        break;
    }
  }
}

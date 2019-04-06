package com.example.activitytran;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Animatable2;
import android.transition.Slide;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.Transition;
import android.util.Pair;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  private List<Pair<View,String>> list = new ArrayList<>();
  private Window window;
  private Animatable icon;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    window = getWindow();

    final Toolbar toolbar = findViewById(R.id.toolbar);
    //toolbar.setTitle("TitleNew");
    setSupportActionBar(toolbar);
    toolbar.setNavigationIcon(R.drawable.avd_pathmorph_drawer_hamburger_to_arrow);
    icon = (Animatable) toolbar.getNavigationIcon();

    toolbar.setNavigationOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        icon.start();
      }
    });

    final ImageView imageView = findViewById(R.id.imageView);

    list.add(new Pair<View, String>(toolbar, "toolbar"));
    list.add(new Pair<View, String>(imageView, "imageView"));

    imageView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
        Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, list.toArray(new Pair[list.size()])).toBundle();
        startActivity(intent, bundle);
      }
    });

    Slide slide = new Slide();
    slide.setSlideEdge(Gravity.BOTTOM);
    slide.addTarget(R.id.slideDownView);
    slide.setDuration(300);
    window.setExitTransition(slide);

  }
}

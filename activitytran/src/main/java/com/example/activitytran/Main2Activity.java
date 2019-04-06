package com.example.activitytran;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.animation.DecelerateInterpolator;

public class Main2Activity extends AppCompatActivity {

  private Window window;
  private Animatable icon;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main2);
    window = getWindow();

    final Toolbar toolbar = findViewById(R.id.toolbarSecond);
    //toolbar.setTitle("TitleNew");
    setSupportActionBar(toolbar);
    toolbar.setNavigationIcon(R.drawable.avd_pathmorph_drawer_arrow_to_hamburger);
    icon = (Animatable) toolbar.getNavigationIcon();

    toolbar.setNavigationOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        icon.start();
        finishAfterTransition();
      }
    });

    Slide slide = new Slide();
    slide.setSlideEdge(Gravity.BOTTOM);
    slide.addTarget(R.id.slideUpView);
    slide.setDuration(500);
    window.setEnterTransition(slide);

    //window.setTransitionBackgroundFadeDuration(500);
  }

  @Override
  public void onBackPressed() {
    icon.start();
    super.onBackPressed();
  }
}

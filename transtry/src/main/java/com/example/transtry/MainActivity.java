package com.example.transtry;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.Scene;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

  private boolean initialState = true;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    final FrameLayout container = findViewById(R.id.frameLayout);

    final TransitionSet transition1 = new TransitionSet();
    final Slide slide1 = new Slide();
    slide1.setSlideEdge(Gravity.BOTTOM);
    transition1.addTransition(slide1);
    transition1.setDuration(1000);


    final TransitionSet transition2 = new TransitionSet();
    final Slide slide2 = new Slide();
    slide2.setSlideEdge(Gravity.BOTTOM);
    transition2.addTransition(slide2);
    transition2.setDuration(1000);

    transition1.addListener(new Transition.TransitionListener() {
      @Override
      public void onTransitionStart(Transition transition) {

      }

      @Override
      public void onTransitionEnd(Transition transition) {
        if(initialState) {
          TransitionManager.go(Scene.getSceneForLayout(container, R.layout.appearlayout, MainActivity.this), transition2);
          initialState = !initialState;
        } else {
          TransitionManager.go(Scene.getSceneForLayout(container, R.layout.disapplayout, MainActivity.this), transition2);
          initialState = !initialState;
        }
      }

      @Override
      public void onTransitionCancel(Transition transition) {

      }

      @Override
      public void onTransitionPause(Transition transition) {

      }

      @Override
      public void onTransitionResume(Transition transition) {

      }
    });

    findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
         TransitionManager.go(Scene.getSceneForLayout(container, R.layout.empty,MainActivity.this), transition1);
      }
    });
  }
}

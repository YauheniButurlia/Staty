package com.example.scenetry;

import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.CircularPropagation;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  private boolean firstTrue = true;
  private CircularPropagation propagation;
  private Rect rect = new Rect();
  private int width = 20;
  private List<View> array = new ArrayList<>();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    final ViewGroup rootScene = (ViewGroup) findViewById(R.id.root);

    for (int i = 0; i < rootScene.getChildCount(); i++) {
      array.add(rootScene.getChildAt(i));
    }

    propagation = new CircularPropagation();
    propagation.setPropagationSpeed(-1f);

    final TransitionSet set = new TransitionSet();
    set.addTransition(new Explode());
    set.setPropagation(propagation);
    set.setEpicenterCallback(new Transition.EpicenterCallback() {
      @Override
      public Rect onGetEpicenter(Transition transition) {
        return rect;
      }
    });
    set.setDuration(2000);

    rootScene.setOnTouchListener(new View.OnTouchListener() {
      @Override
      public boolean onTouch(View v, MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN){
          setEpicenter(event);
          TransitionManager.beginDelayedTransition(rootScene, set);
          for(View view : array){
            //view.setVisibility(View.INVISIBLE);
            view.setVisibility(View.GONE);
          }
        }
        return true;
      }
    });
  }


  private void setEpicenter(MotionEvent event){
    int x = Math.round(event.getX());
    int y = Math.round(event.getY())+ getSupportActionBar().getHeight();
    rect.left = x - width;
    rect.top = y - width;
    rect.right = x + width;
    rect.bottom = y + width;
  }
}

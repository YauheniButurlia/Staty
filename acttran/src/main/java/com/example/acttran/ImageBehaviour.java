package com.example.acttran;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class ImageBehaviour extends CoordinatorLayout.Behavior<ImageView> {

  public ImageBehaviour() {
  }

  public ImageBehaviour(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  @Override
  public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull ImageView child, @NonNull View dependency) {
    return dependency instanceof AppBarLayout;
  }

  @Override
  public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent, @NonNull ImageView child, @NonNull View dependency) {
    float maxScrollRange = (float) ((AppBarLayout)dependency).getTotalScrollRange();
    float factor = Math.abs(dependency.getY())/maxScrollRange;

    Toolbar toolbar = (Toolbar) dependency.findViewById(R.id.toolbar);
    child.setY(dependency.getBottom() - toolbar.getHeight() - child.getHeight()/2 + factor*toolbar.getHeight()/2);
    Log.d("myLog", "AppBar.getBottom() " + dependency.getBottom() +  " | Toolbar.getHeight() " + toolbar.getHeight()
    + " | child.getY() " + child.getY()+ " | child.getX() " + child.getX());

    CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) child.getLayoutParams();
    layoutParams.width = 250 - Math.round(125*factor);
    layoutParams.height = 250 - Math.round(125*factor);
    child.setLayoutParams(layoutParams);
    return true;
  }
}

package com.example.yauheni.staty.Behaviors;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewConfiguration;

public class AppBarNewBehavior extends AppBarLayout.Behavior {

  private ViewPager pager;
  private int mTouchSlop = -1;
  private int totalYConsumed;
  public AppBarNewBehavior() {

  }

  public AppBarNewBehavior(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  @Override
  public boolean onStartNestedScroll(CoordinatorLayout parent, AppBarLayout child, View directTargetChild, View target, int nestedScrollAxes, int type) {
    boolean scroll = super.onStartNestedScroll(parent, child, directTargetChild, target, nestedScrollAxes, type);
    if(scroll && directTargetChild instanceof ViewPager){
      if(pager == null) {
        pager = (ViewPager) directTargetChild;
      }
      if(mTouchSlop < 0){
        mTouchSlop = ViewConfiguration.get(parent.getContext()).getScaledTouchSlop();
      }
    }
    return scroll;
  }

  @Override
  public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, AppBarLayout child, View target, int dx, int dy, int[] consumed, int type) {
    super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type);
    if (pager != null && consumed[1] > 0) {
      totalYConsumed += consumed[1];
      Log.d("myLog","totalYConsumed: " + totalYConsumed + " mTouchSlop: " + mTouchSlop + " dy: " + dy);
      if (totalYConsumed > mTouchSlop) {
        pager.requestDisallowInterceptTouchEvent(true);
      }
    }
  }

  @Override
  public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, AppBarLayout abl, View target, int type) {
    super.onStopNestedScroll(coordinatorLayout, abl, target, type);
    totalYConsumed = 0;
  }
}

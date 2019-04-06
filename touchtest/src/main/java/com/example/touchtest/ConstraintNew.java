package com.example.touchtest;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

public class ConstraintNew extends ConstraintLayout {
  public ConstraintNew(Context context) {
    super(context);
  }

  public ConstraintNew(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public ConstraintNew(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }


  @Override
  public boolean onInterceptTouchEvent(MotionEvent ev) {
      return super.onInterceptTouchEvent(ev);
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    Log.d("myLog","Const onTouchEvent " + event.toString());
    return false;
  }
}

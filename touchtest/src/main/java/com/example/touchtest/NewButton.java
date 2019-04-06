package com.example.touchtest;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;

public class NewButton extends AppCompatButton {

  MainActivity activity;

  public NewButton(Context context) {
    super(context);
  }

  public NewButton(Context context, AttributeSet attrs) {
    super(context, attrs);
    activity = (MainActivity) context;
  }

  public NewButton(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    Log.d("myLog","NewButton onTouchEvent " + event.toString());
    return super.onTouchEvent(event);
  }
}

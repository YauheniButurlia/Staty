package com.example.touchtest;

import android.content.Context;
import android.util.AttributeSet;
import android.view.WindowInsets;
import android.widget.FrameLayout;

public class FrameLayoutMy extends FrameLayout {
  public FrameLayoutMy(Context context) {
    super(context);
  }

  public FrameLayoutMy(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public FrameLayoutMy(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  public FrameLayoutMy(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
  }

/*
  @Override
  public WindowInsets onApplyWindowInsets(WindowInsets insets) {
    int childCount = getChildCount();
    for (int index = 0; index < childCount; index++) {
      getChildAt(index).dispatchApplyWindowInsets(insets);
    }
    return insets;
  }
*/
}

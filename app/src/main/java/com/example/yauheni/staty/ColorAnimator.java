package com.example.yauheni.staty;

import android.animation.ArgbEvaluator;
import android.support.v4.view.ViewPager;

public class ColorAnimator {
  private int[] colors;

  private int mCurrentItem;
  private int mPreviousItem;
  private int mReservedCurrentPosition;
  private int mItemCount;
  private boolean isAnimated;

  private ArgbEvaluator argbEvaluator;
  private ViewPager viewPager;

  private float percent;

  public ColorAnimator(int[] colors) {
    this.colors = colors;
    this.argbEvaluator = new ArgbEvaluator();
    this.mItemCount = colors.length;
  }

  public void setCurrentItem(int position){
    mReservedCurrentPosition = position;
    mCurrentItem = position;
  }
  public void setAnimated(boolean isAnimated){
    this.isAnimated = isAnimated;
  }

  public int getCurrentColor(int position, float positionOffset){
    if(isAnimated) {
      if (position == mReservedCurrentPosition) {
        if (position == mItemCount - 1) {
          return colors[position];
        }
        this.percent = positionOffset;
        mPreviousItem = mReservedCurrentPosition;
        mCurrentItem = position + 1;
      }
      if (position == mReservedCurrentPosition - 1) {
        this.percent = 1f - positionOffset;
        mPreviousItem = mReservedCurrentPosition;
        mCurrentItem = position;
      }
      return (int) argbEvaluator.evaluate(percent, colors[mPreviousItem],colors[mCurrentItem]);
    }
    return colors[mCurrentItem];
  }
}

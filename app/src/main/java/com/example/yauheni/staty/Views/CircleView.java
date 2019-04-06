package com.example.yauheni.staty.Views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

import com.example.yauheni.staty.R;

public class CircleView extends View {

  private static final String TAG = CircleView.class.getSimpleName();

  private Paint paintBase;
  private Paint paintAccent;
  private Paint paint;
  private int circleDistance;
  private static final int circleRadius = 20;
  private int mPreviousItem;
  private int mCurrentItem = 2; //default test value
  private int mItemCount = 5; //default test value
  private int mReservedCurrentPosition;
  private int colorBase;
  private int colorAccent;

  private float percentDone;

  private int[] textColors;

  private boolean isAnimated = false;

  public CircleView(Context context, AttributeSet attrs) throws Exception {
    super(context, attrs);

    TypedArray a = context.getTheme().obtainStyledAttributes(
            attrs,
            R.styleable.CircleView,
            0, 0);
    try {
      //colorBase = a.getColor(R.styleable.CircleView_base_color, getResources().getColor(R.color.white30));
      //colorAccent = a.getColor(R.styleable.CircleView_accent_color, getResources().getColor(R.color.white80));

    } finally {
      a.recycle();
    }

    init();

    textColors = new int[]{Color.rgb(141,100,198),
            Color.rgb(237,81,178),
            Color.rgb(255,206,108),
            Color.rgb(52,203,159) ,
            Color.rgb(78,215,250) };
  }


  public void setViewPager(ViewPager viewPager) {

    mCurrentItem = viewPager.getCurrentItem();
    mReservedCurrentPosition = mCurrentItem;

    mItemCount = viewPager.getAdapter().getCount();

    invalidate();
    requestLayout();
  }

/*
  public void setColorBase(int color) {
    colorBase = color;
    paintBase.setColor(colorBase);

    invalidate();
    requestLayout();
  }

*/
  /**
   * Setting color of accent circle. For another circles use {@link #setColorBase(int)}.
   *
   * @param color
   */
  /*
  public void setColorAccent(int color) {
    colorAccent = color;
    paintAccent.setColor(colorAccent);

    invalidate();
    requestLayout();
  }*/


  /**
   * Set the current item by index. Optionally, scroll the current item into view. This version
   * is for internal use--the scrollIntoView option is always true for external callers.
   *
   * @param currentItem The index of the current item.
   */
  public void setCurrentItem(int currentItem) {
    mCurrentItem = currentItem;
    mReservedCurrentPosition = currentItem;
    invalidate();
    //requestLayout();
  }


  private void init() throws Exception {
    circleDistance = generateCircleDistance();

    paint = new Paint();
    paint.setAntiAlias(true);

    paintBase = new Paint();
    //paintBase.setColor(colorBase);
    paintBase.setAntiAlias(true);

    paintAccent = new Paint();
    //paintAccent.setColor(colorAccent);
    paintAccent.setAntiAlias(true);
  }

  /**
   * Generate distance between circles. Return value depends on screen width.
   *
   * @return distance between circles.
   * @throws Exception
   */
  private int generateCircleDistance() throws Exception {

    WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
    Display display = wm.getDefaultDisplay();
    int screenWidth = display.getWidth();

    int distance = circleRadius * 2;
    if (screenWidth < distance * mItemCount) {
      distance = (int) (circleRadius * 1.5);
      if (screenWidth < distance * mItemCount) {
        throw new Exception("ERROR: You have too many child in ViewPager. It is not possible to show CircleView widget in one line. Try to reorganize your ViewPager.");
      }
    }

    return distance;
  }

  public void setAnimated(boolean isAnimated){
    this.isAnimated = isAnimated;
  }

  public void onScroll(int position, float percentDone){
    if(isAnimated){
      if(position==mReservedCurrentPosition){
        if(position == mItemCount - 1){
          return;
        }
        this.percentDone = percentDone;
        mPreviousItem = mReservedCurrentPosition;
        mCurrentItem = position + 1;
      }
      if(position == mReservedCurrentPosition - 1){
        this.percentDone = 1f - percentDone;
        mPreviousItem = mReservedCurrentPosition;
        mCurrentItem = position;
      }
    }
    invalidate();
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);

    int cx = circleRadius;
    int alpha;

    if(isAnimated){

        for (int i = 0; i < mItemCount; i++) {

          if (i == mCurrentItem){ //should have more light color


            alpha = Math.round(percentDone*235f);
            Log.d("myLog", String.valueOf(alpha) + " mCurrent");
            paint.setColor(textColors[i]);
            paint.setAlpha(alpha + 20);
            canvas.drawCircle(cx, circleRadius, circleRadius, paint);

          } else if(i == mPreviousItem){

            alpha = Math.round((1-percentDone)*235f);
            Log.d("myLog", String.valueOf(alpha) + " mPrevious");
            paint.setColor(textColors[i]);
            paint.setAlpha(20 + alpha);
            canvas.drawCircle(cx, circleRadius, circleRadius, paint);

          } else {
            paintBase.setColor(textColors[i]);
            paintBase.setAlpha(20);
            canvas.drawCircle(cx, circleRadius, circleRadius, paintBase);
          }

          cx = cx + circleRadius * 2 + circleDistance;
        }


      if(percentDone == 1f){
        isAnimated = false;
      }
    } else {
      for (int i = 0; i < mItemCount; i++) {

        if (i == mCurrentItem) //should have more light color
        {
          paintAccent.setColor(textColors[i]);
          canvas.drawCircle(cx, circleRadius, circleRadius, paintAccent);
        } else {
          paintBase.setColor(textColors[i]);
          paintBase.setAlpha(20);
          canvas.drawCircle(cx, circleRadius, circleRadius, paintBase);
        }

        cx = cx + circleRadius * 2 + circleDistance;
      }
    }
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

    super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    final int height = circleRadius * 2;
    final int width = ((circleRadius * 2 + circleDistance) * mItemCount) - circleDistance;

    setMeasuredDimension(width, height);
  }
}

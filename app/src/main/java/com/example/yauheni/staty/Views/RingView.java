package com.example.yauheni.staty.Views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

import com.example.yauheni.staty.R;

public class RingView extends View {

  private int mCurrentItem;
  private int mPreviousItem;
  private int mItemCount = 5;
  private int mReservedCurrentPosition;

  private Paint arcPaint;
  private Paint circlePaint;
  private Paint paintText;
  private int backColor;

  private float width0;
  private float width1;
  private float width2;
  private float width3;
  private float width4;

  private float xDown,xUp,yDown,yUp,xDiff,yDiff;

  private Point coords;
  private float radius = 200f;
  private float startAngle;

  private RectF oval;

  private int[] colors;
  private float[] centerAngles;
  private float[] startAngleSubstractValue;
  private float[] sweepAngles;
  //private float[] widths;

  private boolean isAppear = false;
  private boolean isAnimated = false;
  private float percent = 0f;
  private int backAlpha = 50;

  public RingView(Context context) {
    super(context);

    init();
  }

  private void init(){
    backColor = getResources().getColor(R.color.toolbarColor);
    arcPaint = new Paint();

    //arcPaint.setStyle(Paint.Style.STROKE);
    arcPaint.setStrokeWidth(radius/15f);

    arcPaint.setAntiAlias(true);
    circlePaint = new Paint();
    circlePaint.setAntiAlias(true);
    circlePaint.setColor(backColor);
    paintText = new Paint();
    paintText.setColor(Color.rgb(255,206,108));
    paintText.setTextAlign(Paint.Align.CENTER);
    paintText.setTextSize(100f);

    width0 = 40f;
    width1 = 70f;
    width2 = 90f;
    width3 = 80f;
    width4 = 80f;

    //widths = new float[]{width0,width4,width3,width2,width1};

    coords = new Point();

    colors = new int[]{Color.rgb(141,100,198),
            Color.rgb(237,81,178),
            Color.rgb(255,206,108),
            Color.rgb(52,203,159) ,
            Color.rgb(78,215,250) };

    oval = new RectF();

    centerAngles = new float[]{90f+width0/2,
            90f+width0+width1/2,
            90f+width0+width1+width2/2,
            90f+width0+width1+width2+width3/2,
            90f+width0+width1+width2+width3 + width4/2
    };

    startAngleSubstractValue = new float[]{
            0f,
            width0,
            width0 + width1,
            width0 + width1 + width2,
            width0 + width1 + width2 + width3
    };

    sweepAngles = new float[]{width0,width1,width2,width3,width4};
  }

  public RingView(Context context, AttributeSet attrs) {

    super(context, attrs);
    init();
  }

  public void setRadius(float radius){
    this.radius = radius;
    invalidate();
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    canvas.drawColor(backColor);
    coords.x = getWidth() / 2;
    coords.y = getHeight() / 2;
    oval.left = coords.x - radius;
    oval.top = coords.y - radius;
    oval.right = coords.x + radius;
    oval.bottom = coords.y + radius;

    if(isAnimated){
      if(mCurrentItem > mPreviousItem){
        startAngle = centerAngles[mPreviousItem] + percent*sweepAngles[mCurrentItem]/2 + percent*sweepAngles[mPreviousItem]/2;
      }
      if(mCurrentItem < mPreviousItem){
        startAngle = centerAngles[mPreviousItem] - percent*sweepAngles[mCurrentItem]/2 - percent*sweepAngles[mPreviousItem]/2;
      }
      drawAnimatedRing(canvas);
      if(percent >= 1f){
        setAnimated(false);
      }
    } else {
      startAngle = centerAngles[mCurrentItem];
      drawRing(canvas);
    }
  }


  private void drawSector(Canvas canvas, int index){
    arcPaint.setColor(colors[index]);
    canvas.drawArc(oval, startAngle - startAngleSubstractValue[index], -sweepAngles[index], true, arcPaint);

    arcPaint.setStyle(Paint.Style.STROKE);
    arcPaint.setColor(backColor);
    canvas.drawArc(oval, startAngle - startAngleSubstractValue[index], -sweepAngles[index], true, arcPaint);
    arcPaint.setStyle(Paint.Style.FILL);
  }
  private void drawSector(Canvas canvas, int index, int alpha){
    arcPaint.setColor(colors[index]);
    arcPaint.setAlpha(alpha);
    canvas.drawArc(oval, startAngle - startAngleSubstractValue[index], -sweepAngles[index], true, arcPaint);

    arcPaint.setStyle(Paint.Style.STROKE);
    arcPaint.setColor(backColor);
    canvas.drawArc(oval, startAngle - startAngleSubstractValue[index], -sweepAngles[index], true, arcPaint);
    arcPaint.setStyle(Paint.Style.FILL);
  }

  private void drawAnimatedRing(Canvas canvas){
    for (int i = 0; i < mItemCount; i++) {

      if (i == mPreviousItem) {
        drawSector(canvas, i, Math.round((1 - percent) * 205f)+backAlpha);
      }
      if (i == mCurrentItem) {
        drawSector(canvas, i, Math.round(percent * 205f)+backAlpha);
      } else {
        drawSector(canvas, i, backAlpha);
      }
    }
    canvas.drawCircle(coords.x, coords.y, radius / 6, circlePaint);
  }

  private void drawRing(Canvas canvas){

    for (int i = 0; i < mItemCount; i++) {
      if(i == mCurrentItem){
          drawSector(canvas,i,255);
      }
        drawSector(canvas, i, backAlpha);
    }

/*
    for (int i = 0; i < mItemCount; i++) {
      drawSector(canvas, i);
    }
    */
    canvas.drawCircle(coords.x, coords.y, radius / 6, circlePaint);
  }

  public void setViewPager(final ViewPager viewPager) {

    mCurrentItem = viewPager.getCurrentItem();
    mReservedCurrentPosition = mCurrentItem;

    //mItemCount = viewPager.getAdapter().getCount();

    invalidate();
    requestLayout();
  }

  public void onScroll(int position, float percentDone){
    if(isAnimated){
      if(position==mReservedCurrentPosition){
        if(position == mItemCount - 1){
          return;
        }
        this.percent = percentDone;
        mPreviousItem = mReservedCurrentPosition;
        mCurrentItem = position + 1;
      }
      if(position == mReservedCurrentPosition - 1){
        this.percent = 1f - percentDone;
        mPreviousItem = mReservedCurrentPosition;
        mCurrentItem = position;
      }
    }
    invalidate();
  }

  public void setCurrentItem(int currentItem) {
    mCurrentItem = currentItem;
    mReservedCurrentPosition = currentItem;
    invalidate();
  }

  public void setAnimated(boolean isAnimated){
    this.isAnimated = isAnimated;
  }

  @Override
  protected void onVisibilityChanged(View changedView, int visibility) {
    super.onVisibilityChanged(changedView, visibility);
    //Log.d("myLog","onVisibilityChanged" + " visibility " + visibility);
    if(visibility == VISIBLE){

    }
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    int height = 2*(int)radius;
    setMeasuredDimension(widthMeasureSpec,height);
  }

  private class Sector {
/*
arcPaint.setColor(colors[0]);
    canvas.drawArc(oval, startAngle, width0, true, arcPaint);

    arcPaint.setStyle(Paint.Style.STROKE);
    arcPaint.setColor(backColor);
    canvas.drawArc(oval, startAngle, width0, true, arcPaint);
    arcPaint.setStyle(Paint.Style.FILL);

    arcPaint.setColor(colors[1]);
    canvas.drawArc(oval, startAngle + width0, width1, true, arcPaint);

    arcPaint.setStyle(Paint.Style.STROKE);
    arcPaint.setColor(backColor);
    canvas.drawArc(oval, startAngle + width0, width1, true, arcPaint);
    arcPaint.setStyle(Paint.Style.FILL);

    arcPaint.setColor(colors[2]);
    canvas.drawArc(oval, startAngle + width0 + width1, width2, true, arcPaint);

    arcPaint.setStyle(Paint.Style.STROKE);
    arcPaint.setColor(backColor);
    canvas.drawArc(oval, startAngle + width0 + width1, width2, true, arcPaint);
    arcPaint.setStyle(Paint.Style.FILL);

    arcPaint.setColor(colors[3]);
    canvas.drawArc(oval, startAngle + width0 + width1 + width2, width3, true, arcPaint);

    arcPaint.setStyle(Paint.Style.STROKE);
    arcPaint.setColor(backColor);
    canvas.drawArc(oval, startAngle + width0 + width1 + width2, width3, true, arcPaint);
    arcPaint.setStyle(Paint.Style.FILL);

    arcPaint.setColor(colors[4]);
    canvas.drawArc(oval, startAngle + width0 + width1 + width2 + width3, width4, true, arcPaint);

    arcPaint.setStyle(Paint.Style.STROKE);
    arcPaint.setColor(backColor);
    canvas.drawArc(oval, startAngle + width0 + width1 + width2 + width3, width4, true, arcPaint);
    arcPaint.setStyle(Paint.Style.FILL);
*/
  }
}

package com.example.yauheni.staty.Views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

import com.example.yauheni.staty.R;

public class TextAnimView extends View {

  private int mItemCount = 5;
  private int mCurrentItem = 0;
  private int mPreviousItem;
  private int mReservedCurrentPosition;

  private String[] titles;
  private Paint textPaint;
  private final float DEFAULT_TEXT_SIZE = 50f;
  private int[] textColors;

  private boolean isAnimated = false;
  private long startTime;
  private long duration = 150;
  private float percentDone;

  private float lineWidth = 100f;
  private float STROKE_WIDTH = 40f;


  public TextAnimView(Context context){
    super(context);
     init();
  }
  public TextAnimView(Context context, AttributeSet attrs) {
    super(context, attrs);

    TypedArray a = context.getTheme().obtainStyledAttributes(
            attrs,
            R.styleable.TextAnimView,
            0, 0);

    a.recycle();
    init();
  }

  private void init(){
    textPaint = new Paint();
    /*
    textPaint.setTextSize(DEFAULT_TEXT_SIZE);
    textPaint.setTextAlign(Paint.Align.CENTER);
    */
    textPaint.setStrokeWidth(STROKE_WIDTH);
    textPaint.setStrokeCap(Paint.Cap.ROUND);


    textColors = new int[]{Color.rgb(141,100,198),
            Color.rgb(237,81,178),
            Color.rgb(255,206,108),
            Color.rgb(52,203,159) ,
            Color.rgb(78,215,250) };

    titles = new String[mItemCount];
    titles[0] = "Roma";
    titles[1] = "Home";
    titles[2] = "Type";
    titles[3] = "Book";
    titles[4] = "File";
  }


  public void setViewPager(final ViewPager viewPager) {

    mCurrentItem = viewPager.getCurrentItem();
    mReservedCurrentPosition = mCurrentItem;

    mItemCount = viewPager.getAdapter().getCount();

    invalidate();
    requestLayout();
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


  public void setCurrentItem(int currentItem) {
    mCurrentItem = currentItem;
    mReservedCurrentPosition = currentItem;
    invalidate();
  }

  public void setStrokeWidth(float strokeWidth){
    textPaint.setStrokeWidth(strokeWidth);
    invalidate();
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);

    float width = (float) getWidth();
    float height = (float) getHeight();

    if(isAnimated){

      if(mPreviousItem == mCurrentItem-1){
        textPaint.setColor(textColors[mPreviousItem]);
        textPaint.setAlpha((int)(255f*(1-percentDone)));
        canvas.drawLine(width/2f - percentDone*width/4f - lineWidth,height/2f,
                width/2f - percentDone*width/4f + lineWidth,height/2f,textPaint);
        //canvas.drawText(titles[mPreviousItem],width/2f - percentDone*width/4f,height/2f, textPaint);

        textPaint.setColor(textColors[mCurrentItem]);
        textPaint.setAlpha((int)(255f*(percentDone)));
        canvas.drawLine(width/2f + width/4f - percentDone*width/4f - lineWidth,height/2f,
                width/2f + width/4f - percentDone*width/4f + lineWidth,height/2f,textPaint);
        //canvas.drawText(titles[mCurrentItem],width/2f + width/4f - percentDone*width/4f,height/2f, textPaint);
      }
      if(mPreviousItem == mCurrentItem +1){
        textPaint.setColor(textColors[mPreviousItem]);
        textPaint.setAlpha((int)(255f*(1-percentDone)));
        canvas.drawLine(width/2f + percentDone*width/4f - lineWidth,height/2f,
                width/2f + percentDone*width/4f + lineWidth,height/2f,textPaint);
        //canvas.drawText(titles[mPreviousItem],width/2f + percentDone*width/4f,height/2f, textPaint);

        textPaint.setColor(textColors[mCurrentItem]);
        textPaint.setAlpha((int)(255f*(percentDone)));
        canvas.drawLine(width/2f - width/4f + percentDone*width/4f - lineWidth,height/2f,
                width/2f - width/4f + percentDone*width/4f + lineWidth,height/2f,textPaint);
        //canvas.drawText(titles[mCurrentItem],width/2f - width/4f + percentDone*width/4f,height/2f, textPaint);
      }
    } else {
      textPaint.setColor(textColors[mCurrentItem]);
      canvas.drawLine(width/2f - lineWidth,height/2f,
              width/2f + lineWidth,height/2f,textPaint);
      //canvas.drawText(titles[mCurrentItem],width/2f,height/2f, textPaint);
    }
    if(percentDone == 1f){
      setAnimated(false);
    }
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    setMeasuredDimension(widthMeasureSpec, heightMeasureSpec/2);
  }
}

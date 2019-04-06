package com.example.yauheni.staty.Views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;

public class ListItemView extends View {

  private final float CORNER_RADIUS = 20f;
  private final float FIRST_STROKE_WIDTH = 40f;
  private final float SECOND_STROKE_WIDTH = 25f;

  private int alphaFirst = 170;
  private int alphaBack = 70;
  private int alphaSecondStroke = 100;
  private Paint backgroundPaint;
  private Paint firstStrokePaint;
  private Paint secondStrokePaint;

  private float width;
  private float height;

  public ListItemView(Context context) {
    super(context);

    init();
  }

  private void init(){
    backgroundPaint = new Paint();
    backgroundPaint.setStyle(Paint.Style.FILL);
    backgroundPaint.setColor(Color.WHITE);
    backgroundPaint.setAlpha(alphaBack);

    firstStrokePaint = new Paint();
    firstStrokePaint.setStyle(Paint.Style.STROKE);
    firstStrokePaint.setStrokeWidth(FIRST_STROKE_WIDTH);
    firstStrokePaint.setStrokeCap(Paint.Cap.ROUND);
    firstStrokePaint.setColor(Color.WHITE);
    firstStrokePaint.setAlpha(alphaFirst);

    secondStrokePaint = new Paint();
    secondStrokePaint.setStyle(Paint.Style.STROKE);
    secondStrokePaint.setStrokeWidth(SECOND_STROKE_WIDTH);
    secondStrokePaint.setStrokeCap(Paint.Cap.ROUND);
    secondStrokePaint.setColor(Color.WHITE);
    secondStrokePaint.setAlpha(alphaSecondStroke);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    width = getWidth();
    height = getHeight();
    canvas.drawRoundRect(width/12,height/8, width - width/12, height - height/8, CORNER_RADIUS, CORNER_RADIUS, backgroundPaint);
    canvas.drawLine(width/10 + width/16,height/4 + height/8, width/2+width/6, height/4 + height/8,firstStrokePaint);
    canvas.drawLine(width/10 + width/16,height/2 + height/8, width/2, height/2 + height/8,secondStrokePaint);
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    setMeasuredDimension(widthMeasureSpec,260);
  }
}

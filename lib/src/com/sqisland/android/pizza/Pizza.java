package com.sqisland.android.pizza;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class Pizza extends View {
  private static final int DEFAULT_STROKE_WIDTH = 2;
  private static final int DEFAULT_NUM_WEDGES = 5;
  private static final int DEFAULT_COLOR = Color.YELLOW;

  private int mStrokeWidth = DEFAULT_STROKE_WIDTH;
  private int mNumWedges = DEFAULT_NUM_WEDGES;
  private int mColor = DEFAULT_COLOR;

  private Paint mPaint;

  public Pizza(Context context) {
    super(context);
    init(null);
  }

  public Pizza(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(attrs);
  }

  public Pizza(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    init(attrs);
  }

  private void init(AttributeSet attrs) {
	if (attrs != null) {
      String namespace = "http://schemas.android.com/apk/res-auto";
      mStrokeWidth = attrs.getAttributeIntValue(namespace, "stroke_width", DEFAULT_STROKE_WIDTH);
      mNumWedges = attrs.getAttributeIntValue(namespace, "num_wedges", DEFAULT_NUM_WEDGES);
      mColor = attrs.getAttributeIntValue(namespace, "color", DEFAULT_COLOR);
	}

    mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    mPaint.setColor(mColor);
    mPaint.setStrokeWidth(mStrokeWidth);
    mPaint.setStyle(Paint.Style.STROKE);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    final int width = getWidth() - getPaddingLeft() - getPaddingRight();
    final int height = getHeight() - getPaddingTop() - getPaddingBottom();
    final int diameter = Math.min(width, height) - mStrokeWidth;
    final int cx = width / 2 + getPaddingLeft();
    final int cy = height / 2 + getPaddingTop();
    final int radius = diameter / 2;

    canvas.drawCircle(cx, cy, radius, mPaint);
    drawPizzaCuts(canvas, cx, cy, radius);
  }

  private void drawPizzaCuts(Canvas canvas, float cx, float cy, float radius) {
    canvas.save();
    final float degrees = 360f / mNumWedges;
    for (int i = 0; i < mNumWedges; ++i) {
      canvas.rotate(degrees, cx, cy);
      canvas.drawLine(cx, cy, cx, cy - radius, mPaint);
    }
    canvas.restore();
  }
}
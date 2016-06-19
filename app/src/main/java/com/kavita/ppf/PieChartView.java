package com.kavita.ppf;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Sharad on 19-Jun-16.
 */

public class PieChartView extends View {
    private Paint paint;
    private float[] valueDegree;
    private int[]   valueColor;
    private RectF rectPie;
    private final int strokeSize = 40;

    public PieChartView(Context context) {
        this(context, null, 0);
    }

    public PieChartView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PieChartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(strokeSize);
        paint.setStyle(Paint.Style.STROKE);
    }

    public void setValues(float[] values) {
        float total = 0;
        for (int i = 0; i < values.length; i++) {
            total += values[i];
        }

        valueDegree = new float[values.length];
        valueColor  = new int[values.length];
        int startColor = ContextCompat.getColor(getContext(), R.color.primary);
        int endColor = Color.WHITE;
        for (int i = 0; i < values.length; i++) {
            valueDegree[i] = 360 * (values[i] / total);
            valueColor[i] = (Integer) new ArgbEvaluator().evaluate(((float)i/values.length), startColor, endColor);
        }

        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float temp = 0;

        for (int i = 0; i < valueDegree.length; i++) {
            paint.setColor(valueColor[i]);
            canvas.drawArc(rectPie, temp, valueDegree[i], false, paint);
            temp += valueDegree[i];
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        int radius = (Math.min(w, h) - strokeSize) / 2;
        rectPie = new RectF((w/2) - radius, (h/2) - radius, (w/2) + radius, (h/2) + radius);
    }
}

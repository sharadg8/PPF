package com.kavita.ppf;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sharad on 19-Jun-16.
 */

public class PieChartView extends View {
    private Paint paint;
    private Paint labelPaint;
    private RectF rectPie;
    private final int strokeSize = 40;
    private List<PieSector> sectors;

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

        labelPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        labelPaint.setStrokeWidth(4);
        labelPaint.setStyle(Paint.Style.STROKE);
    }

    public void setValues(float[] values) {
        float total = 0;
        for (int i = 0; i < values.length; i++) {
            total += values[i];
        }

        sectors = new ArrayList<>();
        int startColor = ContextCompat.getColor(getContext(), R.color.primary);
        int endColor = Color.WHITE;
        float startAngle = 0;
        for (int i = 0; i < values.length; i++) {
            float sweepAngle = 360 * (values[i] / total);
            int color = (Integer) new ArgbEvaluator().evaluate(((float)i/values.length), startColor, endColor);
            sectors.add(new PieSector(startAngle, sweepAngle, color));
            startAngle += sweepAngle;
        }

        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i = 0; i < sectors.size(); i++) {
            PieSector sector = sectors.get(i);
            paint.setColor(sector.color);
            canvas.drawArc(rectPie, sector.startAngle, sector.sweepAngle, false, paint);

            labelPaint.setColor(sector.color);
            float endX = (sector.lineStart.x > rectPie.centerX()) ? getRight() : getLeft();
            canvas.drawLine(sector.lineStart.x, sector.lineStart.y, endX, sector.lineStart.y, labelPaint);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        int radius = (Math.min(w, h) - strokeSize) / 2;
        rectPie = new RectF((w/2) - radius, (h/2) - radius, (w/2) + radius, (h/2) + radius);

        for (int i = 0; i < sectors.size(); i++) {
            sectors.get(i).compute();
        }
    }

    public class PieSector {
        public float  sweepAngle;
        public float  startAngle;
        public int    color;
        public PointF lineStart;

        public PieSector(float startAngle, float sweepAngle, int color) {
            this.startAngle = startAngle;
            this.sweepAngle = sweepAngle;
            this.color = color;
        }

        public void compute(){
            float lineX = (float)(rectPie.centerX() + (rectPie.width() / 2) * Math.cos(Math.toRadians(startAngle + sweepAngle / 2)));
            float lineY = (float)(rectPie.centerY() + (rectPie.height() / 2) * Math.sin(Math.toRadians(startAngle + sweepAngle / 2)));
            lineStart = new PointF(lineX, lineY);
        }
    }
}

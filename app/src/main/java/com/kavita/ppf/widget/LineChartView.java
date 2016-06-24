package com.kavita.ppf.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.kavita.ppf.R;

/**
 * Created by Sharad on 21-Jun-16.
 */


public class LineChartView extends View {
    private static final float GRAPH_SMOOTHNESS = 0.15f;
    private float values[] = new float[] {};
    private float maxValue = 0.1f;
    private Paint paint;
    private boolean smooth = false;

    public LineChartView(Context context) {
        this(context, null, 0);
    }

    public LineChartView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LineChartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(ContextCompat.getColor(context, R.color.primary_light));
        paint.setStyle(Paint.Style.STROKE);
        paint.setShadowLayer(4, 2, 2, 0x81000000);
        paint.setStrokeWidth(4);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Path path = createPath();
        canvas.drawPath(path, paint);
    }

    private Path createPath() {
        Path path = new Path();
        path.moveTo(getXPos(0), getYPos(values[0]));
        if(smooth) {
            for (int i = 0; i < values.length - 1; i++) {
                float thisPointX = getXPos(i);
                float thisPointY = getYPos(values[i]);
                float nextPointX = getXPos(i + 1);
                float nextPointY = getYPos(values[si(i + 1)]);

                float startdiffX = (nextPointX - getXPos(si(i - 1)));
                float startdiffY = (nextPointY - getYPos(values[si(i - 1)]));
                float endDiffX = (getXPos(si(i + 2)) - thisPointX);
                float endDiffY = (getYPos(values[si(i + 2)]) - thisPointY);

                float firstControlX = thisPointX + (GRAPH_SMOOTHNESS * startdiffX);
                float firstControlY = thisPointY + (GRAPH_SMOOTHNESS * startdiffY);
                float secondControlX = nextPointX - (GRAPH_SMOOTHNESS * endDiffX);
                float secondControlY = nextPointY - (GRAPH_SMOOTHNESS * endDiffY);

                path.cubicTo(firstControlX, firstControlY, secondControlX, secondControlY, nextPointX, nextPointY);
            }
        } else {
            for (int i = 1; i < values.length; i++) {
                path.lineTo(getXPos(i), getYPos(values[i]));
            }
        }
        return path;
    }

    /**
     * Given an index in datapoints, it will make sure the the returned index is
     * within the array
     *
     * @param i
     * @return
     */
    private int si(int i) {
        if (i > values.length - 1) {
            return values.length - 1;
        } else if (i < 0) {
            return 0;
        }
        return i;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private float getYPos(float value) {
        float height = getHeight() - getPaddingTop() - getPaddingBottom();
        value = (value / maxValue) * height;
        value = height - value;
        value += getPaddingTop();
        return value;
    }

    private float getXPos(float value) {
        float width = getWidth() - getPaddingLeft() - getPaddingRight();
        value = (value / (values.length - 1)) * width;
        value += getPaddingLeft();
        return value;
    }

    public void setValues(float values[]) {
        setValues(values, false);
    }
    public void setValues(float values[], boolean smooth) {
        this.smooth = smooth;
        maxValue = 0.1f;
        for(int i=0; i<values.length; i++) {
            maxValue = Math.max(values[i], maxValue);
        }

        this.values = values.clone();
        invalidate();
    }
}

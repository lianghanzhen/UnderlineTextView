package com.github.lianghanzhen;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

public class UnderlineTextView extends TextView {

    private static final int LEFT = 0;
    private static final int TOP = 1;
    private static final int RIGHT = 2;
    private static final int BOTTOM = 3;

    private static final int DEFAULT_UNDERLINE_HEIGHT = 0;
    private static final int DEFAULT_UNDERLINE_COLOR = Color.TRANSPARENT;

    private final Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final int[] mUnderlineHeights = new int[] {DEFAULT_UNDERLINE_HEIGHT, DEFAULT_UNDERLINE_HEIGHT, DEFAULT_UNDERLINE_HEIGHT, DEFAULT_UNDERLINE_HEIGHT};
    private final int[] mUnderlineColors = new int[] {DEFAULT_UNDERLINE_COLOR, DEFAULT_UNDERLINE_COLOR, DEFAULT_UNDERLINE_COLOR, DEFAULT_UNDERLINE_COLOR};

    public UnderlineTextView(Context context) {
        super(context);
    }

    public UnderlineTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initUnderlineTextView(context, attrs);
    }

    public UnderlineTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        initUnderlineTextView(context, attrs);
    }

    private void initUnderlineTextView(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.UnderlineTextView);
            if (typedArray.hasValue(R.styleable.UnderlineTextView_underline_height)) {
                mUnderlineHeights[LEFT] = mUnderlineHeights[TOP] = mUnderlineHeights[RIGHT] = mUnderlineHeights[BOTTOM] = typedArray.getDimensionPixelSize(R.styleable.UnderlineTextView_underline_height, DEFAULT_UNDERLINE_HEIGHT);
            } else {
                mUnderlineHeights[LEFT] = typedArray.getDimensionPixelSize(R.styleable.UnderlineTextView_underline_heightLeft, DEFAULT_UNDERLINE_HEIGHT);
                mUnderlineHeights[TOP] = typedArray.getDimensionPixelSize(R.styleable.UnderlineTextView_underline_heightTop, DEFAULT_UNDERLINE_HEIGHT);
                mUnderlineHeights[RIGHT] = typedArray.getDimensionPixelSize(R.styleable.UnderlineTextView_underline_heightRight, DEFAULT_UNDERLINE_HEIGHT);
                mUnderlineHeights[BOTTOM] = typedArray.getDimensionPixelSize(R.styleable.UnderlineTextView_underline_heightBottom, DEFAULT_UNDERLINE_HEIGHT);
            }

            if (typedArray.hasValue(R.styleable.UnderlineTextView_underline_color)) {
                mUnderlineColors[LEFT] = mUnderlineColors[TOP] = mUnderlineColors[RIGHT] = mUnderlineColors[BOTTOM] = typedArray.getColor(R.styleable.UnderlineTextView_underline_color, DEFAULT_UNDERLINE_COLOR);
            } else {
                mUnderlineColors[LEFT] = typedArray.getColor(R.styleable.UnderlineTextView_underline_colorLeft, DEFAULT_UNDERLINE_COLOR);
                mUnderlineColors[TOP] = typedArray.getColor(R.styleable.UnderlineTextView_underline_colorTop, DEFAULT_UNDERLINE_COLOR);
                mUnderlineColors[RIGHT] = typedArray.getColor(R.styleable.UnderlineTextView_underline_colorRight, DEFAULT_UNDERLINE_COLOR);
                mUnderlineColors[BOTTOM] = typedArray.getColor(R.styleable.UnderlineTextView_underline_colorBottom, DEFAULT_UNDERLINE_COLOR);
            }
            setPadding(getPaddingLeft() + mUnderlineHeights[LEFT], getPaddingTop() + mUnderlineHeights[TOP], getPaddingRight() + mUnderlineHeights[RIGHT], getPaddingBottom() + mUnderlineHeights[BOTTOM]);
            typedArray.recycle();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mUnderlineHeights[LEFT] > 0) {
            mPaint.setColor(mUnderlineColors[LEFT]);
            canvas.drawRect(0, 0, mUnderlineHeights[LEFT], getHeight(), mPaint);
        }
        if (mUnderlineHeights[TOP] > 0) {
            mPaint.setColor(mUnderlineColors[TOP]);
            canvas.drawRect(0, 0, getWidth(), mUnderlineHeights[TOP], mPaint);
        }
        if (mUnderlineHeights[RIGHT] > 0) {
            mPaint.setColor(mUnderlineColors[RIGHT]);
            canvas.drawRect(getWidth() - mUnderlineHeights[RIGHT], 0, getWidth(), getHeight(), mPaint);
        }
        if (mUnderlineHeights[BOTTOM] > 0) {
            mPaint.setColor(mUnderlineColors[BOTTOM]);
            canvas.drawRect(0, getHeight() - mUnderlineHeights[BOTTOM], getWidth(), getHeight(), mPaint);
        }
    }

    public void setUnderlineHeights(int left, int top, int right, int bottom) {
        int originalLeft = getPaddingLeft() - mUnderlineHeights[LEFT];
        mUnderlineHeights[LEFT] = left > 0 ? left : 0;
        int originalTop = getPaddingLeft() - mUnderlineHeights[TOP];
        mUnderlineHeights[TOP] = top > 0 ? top : 0;
        int originalRight = getPaddingLeft() - mUnderlineHeights[RIGHT];
        mUnderlineHeights[RIGHT] = right > 0 ? right : 0;
        int originalBottom = getPaddingLeft() - mUnderlineHeights[BOTTOM];
        mUnderlineHeights[BOTTOM] = bottom > 0 ? bottom : 0;
        setPadding(originalLeft + mUnderlineHeights[LEFT], originalTop + mUnderlineHeights[TOP], originalRight + mUnderlineHeights[RIGHT], originalBottom + mUnderlineHeights[BOTTOM]);
    }

    public void setUnderlineColor(int left, int top, int right, int bottom) {
        mUnderlineColors[LEFT] = left;
        mUnderlineColors[TOP] = top;
        mUnderlineColors[RIGHT] = right;
        mUnderlineColors[bottom] = bottom;
        invalidate();
    }

}

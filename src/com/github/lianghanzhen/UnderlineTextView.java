package com.github.lianghanzhen;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

public class UnderlineTextView extends TextView {

    private static final int DEFAULT_UNDERLINE_HEIGHT = 0;
    private static final int DEFAULT_UNDERLINE_COLOR = Color.TRANSPARENT;

    private final Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int mUnderlineHeight;

    public UnderlineTextView(Context context) {
        super(context);

        mPaint.setColor(DEFAULT_UNDERLINE_COLOR);
        mUnderlineHeight = DEFAULT_UNDERLINE_HEIGHT;
        initUnderlineTextView(context, null);
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
            mUnderlineHeight = typedArray.getDimensionPixelSize(R.styleable.UnderlineTextView_underline_height, DEFAULT_UNDERLINE_HEIGHT);
            mPaint.setColor(typedArray.getColor(R.styleable.UnderlineTextView_underline_color, DEFAULT_UNDERLINE_HEIGHT));
            typedArray.recycle();
        }
    }

    @Override
    public void setPadding(int left, int top, int right, int bottom) {
        super.setPadding(left, top, right, bottom + mUnderlineHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRect(0, getHeight() - mUnderlineHeight, getWidth(), getHeight(), mPaint);
    }

    public void setUnderlineHeight(int underlineHeight) {
        if (underlineHeight < 0) {
            underlineHeight = 0;
        }
        if (mUnderlineHeight != underlineHeight) {
            int originalPadding = getPaddingBottom() - mUnderlineHeight;
            mUnderlineHeight = underlineHeight;
            setPadding(getPaddingLeft(), getPaddingTop(), getPaddingRight(), originalPadding);
        }
    }

    public void setUnderlineColor(int underlineColor) {
        if (mPaint.getColor() != underlineColor) {
            mPaint.setColor(underlineColor);
            invalidate();
        }
    }

}

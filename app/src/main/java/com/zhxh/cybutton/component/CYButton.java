package com.zhxh.cybutton.component;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.zhxh.cybutton.R;

/**
 * Created by zhxh on 2018/1/19.
 */

public class CYButton extends android.support.v7.widget.AppCompatButton implements View.OnTouchListener {

    private boolean isShadowEnabled = true;
    private int mButtonColor;
    private int mButtonDrawable;
    private Bitmap mButtonBitmap;
    private int mShadowColor;
    private int mShadowHeight;
    private int borderWidth;
    private int outerRadius;
    private int innerRadius;

    private int mPaddingLeft;
    private int mPaddingRight;
    private int mPaddingTop;
    private int mPaddingBottom;

    private Drawable pressedDrawable;
    private Drawable unpressedDrawable;

    boolean isShadowColorDefined = false;

    int cy_btn_default_color = 0xff3eadeb;
    int cy_btn_default_shadow_color = 0xff3493c8;
    int cy_btn_default_null = 0;

    public CYButton(Context context) {
        super(context);
        bounds = new Rect();
        init();
        this.setOnTouchListener(this);
    }

    public CYButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        bounds = new Rect();
        applyAttributes(attrs);
        init();
        parseAttrs(context, attrs);
        this.setOnTouchListener(this);
    }

    public CYButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        bounds = new Rect();
        applyAttributes(attrs);
        init();
        parseAttrs(context, attrs);
        this.setOnTouchListener(this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        refresh();
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (mButtonDrawable != 0) {
                    Drawable drawable = getResources().getDrawable(mButtonDrawable);
                    drawable.setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
                    updateBackground(drawable);
                    this.setPadding(mPaddingLeft, mPaddingTop + mShadowHeight, mPaddingRight, mPaddingBottom);
                } else if (mButtonBitmap != null) {
                    Drawable drawable = new RoundImageDrawable(mButtonBitmap, 10);
                    drawable.setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
                    updateBackground(drawable);
                    this.setPadding(mPaddingLeft, mPaddingTop + mShadowHeight, mPaddingRight, mPaddingBottom);
                } else {
                    updateBackground(pressedDrawable);
                    this.setPadding(mPaddingLeft, mPaddingTop + mShadowHeight, mPaddingRight, mPaddingBottom);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                Rect r = new Rect();
                view.getLocalVisibleRect(r);
                if (!r.contains((int) motionEvent.getX(), (int) motionEvent.getY() + 3 * mShadowHeight) &&
                        !r.contains((int) motionEvent.getX(), (int) motionEvent.getY() - 3 * mShadowHeight)) {
                    if (mButtonDrawable != 0) {
                        Drawable drawable = getResources().getDrawable(mButtonDrawable);
                        drawable.clearColorFilter();
                        updateBackground(drawable);
//                        updateBackground(getResources().getDrawable(mButtonDrawable));
                        this.setPadding(mPaddingLeft, mPaddingTop + mShadowHeight, mPaddingRight, mPaddingBottom + mShadowHeight);
                    } else if (mButtonBitmap != null) {
                        Drawable drawable = new RoundImageDrawable(mButtonBitmap, 10);
                        drawable.clearColorFilter();
                        updateBackground(drawable);
                        this.setPadding(mPaddingLeft, mPaddingTop + mShadowHeight, mPaddingRight, mPaddingBottom);
                    } else {
                        updateBackground(unpressedDrawable);
                        this.setPadding(mPaddingLeft, mPaddingTop + mShadowHeight, mPaddingRight, mPaddingBottom + mShadowHeight);
                    }
                }
                break;
            case MotionEvent.ACTION_OUTSIDE:
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                if (mButtonDrawable != 0) {
                    Drawable drawable = getResources().getDrawable(mButtonDrawable);
                    drawable.clearColorFilter();
                    updateBackground(drawable);
//                    updateBackground(getResources().getDrawable(mButtonDrawable));
                    this.setPadding(mPaddingLeft, mPaddingTop + mShadowHeight, mPaddingRight, mPaddingBottom + mShadowHeight);
                } else if (mButtonBitmap != null) {
                    Drawable drawable = new RoundImageDrawable(mButtonBitmap, 10);
                    drawable.clearColorFilter();
                    updateBackground(drawable);
                    this.setPadding(mPaddingLeft, mPaddingTop + mShadowHeight, mPaddingRight, mPaddingBottom);
                } else {
                    updateBackground(unpressedDrawable);
                    this.setPadding(mPaddingLeft, mPaddingTop + mShadowHeight, mPaddingRight, mPaddingBottom + mShadowHeight);
                }
                break;
        }
        return false;
    }

    private void init() {
        //Init default values
        isShadowEnabled = true;
        Resources resources = getResources();
        if (resources == null) return;
        mButtonColor = cy_btn_default_color;
        mShadowColor = cy_btn_default_shadow_color;
        mShadowHeight = cy_btn_default_null;
        borderWidth = cy_btn_default_null;
        outerRadius = cy_btn_default_null;
        innerRadius = cy_btn_default_null;
    }

    private void parseAttrs(Context context, AttributeSet attrs) {
        //Load from custom attributes
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CYButton);
        if (typedArray == null) return;
        for (int i = 0; i < typedArray.getIndexCount(); i++) {
            int attr = typedArray.getIndex(i);
            if (attr == R.styleable.CYButton_XshadowEnabled) {
                isShadowEnabled = typedArray.getBoolean(attr, true); //Default is true
            } else if (attr == R.styleable.CYButton_XbuttonColor) {
                mButtonColor = typedArray.getColor(attr, cy_btn_default_color);
            } else if (attr == R.styleable.CYButton_XshadowColor) {
                mShadowColor = typedArray.getColor(attr, cy_btn_default_shadow_color);
                isShadowColorDefined = true;
            } else if (attr == R.styleable.CYButton_XshadowHeight) {
                mShadowHeight = typedArray.getDimensionPixelSize(attr, cy_btn_default_null);
            } else if (attr == R.styleable.CYButton_XborderRadius) {
                borderWidth = typedArray.getDimensionPixelSize(attr, cy_btn_default_null);
            } else if (attr == R.styleable.CYButton_XouterRadius) {
                outerRadius = typedArray.getDimensionPixelSize(attr, cy_btn_default_null);
            } else if (attr == R.styleable.CYButton_XinnerRadius) {
                innerRadius = typedArray.getDimensionPixelSize(attr, cy_btn_default_null);
            }
        }
        typedArray.recycle();

        //Get paddingLeft, paddingRight
        int[] attrsArray = new int[]{
                android.R.attr.paddingLeft,  // 0
                android.R.attr.paddingRight, // 1
        };
        TypedArray ta = context.obtainStyledAttributes(attrs, attrsArray);
        if (ta == null) return;
        mPaddingLeft = ta.getDimensionPixelSize(0, 0);
        mPaddingRight = ta.getDimensionPixelSize(1, 0);
        ta.recycle();

        //Get paddingTop, paddingBottom
        int[] attrsArray2 = new int[]{
                android.R.attr.paddingTop,   // 0
                android.R.attr.paddingBottom,// 1
        };
        TypedArray ta1 = context.obtainStyledAttributes(attrs, attrsArray2);
        if (ta1 == null) return;
        mPaddingTop = ta1.getDimensionPixelSize(0, 0);
        mPaddingBottom = ta1.getDimensionPixelSize(1, 0);
        ta1.recycle();
    }

    public void refresh() {
        int alpha = Color.alpha(mButtonColor);
        float[] hsv = new float[3];
        Color.colorToHSV(mButtonColor, hsv);
        hsv[2] *= 0.8f; // value component
        //if shadow color was not defined, generate shadow color = 80% brightness
        if (!isShadowColorDefined) {
            mShadowColor = Color.HSVToColor(alpha, hsv);
        }

        if (this.isEnabled()) {
            if (isShadowEnabled) {
                pressedDrawable = createDrawable(outerRadius, innerRadius, borderWidth, Color.TRANSPARENT, mButtonColor);
                unpressedDrawable = createDrawable(outerRadius, innerRadius, borderWidth, mButtonColor, mShadowColor);
            } else {
                mShadowHeight = 0;
                pressedDrawable = createDrawable(outerRadius, innerRadius, borderWidth, mShadowColor, Color.TRANSPARENT);
                unpressedDrawable = createDrawable(outerRadius, innerRadius, borderWidth, mButtonColor, Color.TRANSPARENT);
            }
        } else {
            Color.colorToHSV(mButtonColor, hsv);
            hsv[1] *= 0.25f; // saturation component
            int disabledColor = mShadowColor = Color.HSVToColor(alpha, hsv);
            // Disabled button does not have shadow
            pressedDrawable = createDrawable(outerRadius, innerRadius, borderWidth, disabledColor, Color.TRANSPARENT);
            unpressedDrawable = createDrawable(outerRadius, innerRadius, borderWidth, disabledColor, Color.TRANSPARENT);
        }
        updateBackground(unpressedDrawable);
        //Set padding
        this.setPadding(mPaddingLeft, mPaddingTop + mShadowHeight, mPaddingRight, mPaddingBottom + mShadowHeight);
    }

    private void updateBackground(Drawable background) {
        if (background == null) return;
        //Set button background
        if (Build.VERSION.SDK_INT >= 16) {
            this.setBackground(background);
        } else {
            this.setBackgroundDrawable(background);
        }
    }

    private LayerDrawable createDrawable(int outRadius, int inRadius, int border, int topColor, int bottomColor) {

        float[] outerRadius = new float[]{outRadius, outRadius, outRadius, outRadius, outRadius, outRadius, outRadius, outRadius};
        float[] innerRadius = new float[]{inRadius, inRadius, inRadius, inRadius, inRadius, inRadius, inRadius, inRadius};
        //border
        RectF inset = new RectF(border, border, border, border);
        //Top
        RoundRectShape topRoundRect = new RoundRectShape(outerRadius, inset, innerRadius);
        ShapeDrawable topShapeDrawable = new ShapeDrawable(topRoundRect);
        topShapeDrawable.getPaint().setColor(topColor);
        //Bottom
        RoundRectShape roundRectShape = new RoundRectShape(outerRadius, inset, innerRadius);
        ShapeDrawable bottomShapeDrawable = new ShapeDrawable(roundRectShape);
        bottomShapeDrawable.getPaint().setColor(bottomColor);
        //Create array
        Drawable[] drawArray = {bottomShapeDrawable, topShapeDrawable};
        LayerDrawable layerDrawable = new LayerDrawable(drawArray);

        //Set shadow height
        if (isShadowEnabled && topColor != Color.TRANSPARENT) {
            //unpressed drawable
            layerDrawable.setLayerInset(0, 0, 0, 0, 0);  /*index, left, top, right, bottom*/
        } else {
            //pressed drawable
            layerDrawable.setLayerInset(0, 0, mShadowHeight, 0, 0);  /*index, left, top, right, bottom*/
        }
        layerDrawable.setLayerInset(1, 0, 0, 0, mShadowHeight);  /*index, left, top, right, bottom*/

        return layerDrawable;
    }


    protected int drawableWidth;
    protected DrawablePositions drawablePosition;
    protected int iconPadding;
    Rect bounds;

    private enum DrawablePositions {
        NONE,
        LEFT_AND_RIGHT,
        LEFT,
        RIGHT
    }

    protected void applyAttributes(AttributeSet attrs) {
        if (null == bounds) {
            bounds = new Rect();
        }

        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.CYButton);
        int paddingId = typedArray.getDimensionPixelSize(R.styleable.CYButton_XiconPadding, 0);
        setIconPadding(paddingId);
        typedArray.recycle();
    }

    public void setIconPadding(int padding) {
        iconPadding = padding;
        requestLayout();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        Paint textPaint = getPaint();
        String text = getText().toString();
        textPaint.getTextBounds(text, 0, text.length(), bounds);

        int textWidth = bounds.width();
        int factor = (drawablePosition == DrawablePositions.LEFT_AND_RIGHT) ? 2 : 1;
        int contentWidth = drawableWidth + iconPadding * factor + textWidth;
        int horizontalPadding = (int) ((getWidth() / 2.0) - (contentWidth / 2.0));

        setCompoundDrawablePadding(-horizontalPadding + iconPadding);

        switch (drawablePosition) {
            case LEFT:
                setPadding(horizontalPadding, getPaddingTop(), 0, getPaddingBottom());
                break;

            case RIGHT:
                setPadding(0, getPaddingTop(), horizontalPadding, getPaddingBottom());
                break;

            case LEFT_AND_RIGHT:
                setPadding(horizontalPadding, getPaddingTop(), horizontalPadding, getPaddingBottom());
                break;

            default:
                setPadding(0, getPaddingTop(), 0, getPaddingBottom());
        }
    }

    @Override
    public void setCompoundDrawablesWithIntrinsicBounds(Drawable left, Drawable top, Drawable right, Drawable bottom) {
        super.setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom);

        if (left != null && right != null) {
            drawableWidth = left.getIntrinsicWidth() + right.getIntrinsicWidth();
            drawablePosition = DrawablePositions.LEFT_AND_RIGHT;
        } else if (left != null) {
            drawableWidth = left.getIntrinsicWidth();
            drawablePosition = DrawablePositions.LEFT;
        } else if (right != null) {
            drawableWidth = right.getIntrinsicWidth();
            drawablePosition = DrawablePositions.RIGHT;
        } else {
            drawablePosition = DrawablePositions.NONE;
        }

        requestLayout();
    }

    //Setter
    public void setShadowEnabled(boolean isShadowEnabled) {
        this.isShadowEnabled = isShadowEnabled;
        setShadowHeight(0);
        refresh();
    }

    public void setButtonDrawable(int drawable) {
        this.mButtonDrawable = drawable;
    }

    public void setButtonBitmap(Bitmap drawable) {
        this.mButtonBitmap = drawable;
    }

    public void setButtonColor(int buttonColor) {
        this.mButtonColor = buttonColor;
        refresh();
    }

    public void setShadowColor(int shadowColor) {
        this.mShadowColor = shadowColor;
        isShadowColorDefined = true;
        refresh();
    }

    public void setShadowHeight(int shadowHeight) {
        this.mShadowHeight = shadowHeight;
        refresh();
    }

    public void setBorderWidth(int borderWidth) {
        this.borderWidth = borderWidth;
        refresh();
    }

    public void setOuterRadius(int outerRadius) {
        this.outerRadius = outerRadius;
        refresh();
    }

    public void setInnerRadius(int innerRadius) {
        this.innerRadius = innerRadius;
        refresh();
    }

    public void setFButtonPadding(int left, int top, int right, int bottom) {
        mPaddingLeft = left;
        mPaddingRight = right;
        mPaddingTop = top;
        mPaddingBottom = bottom;
        refresh();
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        refresh();
    }

    //Getter
    public boolean isShadowEnabled() {
        return isShadowEnabled;
    }

    public int getButtonColor() {
        return mButtonColor;
    }

    public int getShadowColor() {
        return mShadowColor;
    }

    public int getBorderWidth() {
        return borderWidth;
    }

    public int getShadowHeight() {
        return mShadowHeight;
    }

    public int getOuterRadius() {
        return outerRadius;
    }


    public class RoundImageDrawable extends Drawable {
        private Paint mPaint;
        private Bitmap mBitmap;

        private RectF rectF;

        private float mRadius;

        public RoundImageDrawable(Bitmap bitmap, float radius) {
            mBitmap = bitmap;
            mRadius = radius;
            BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP,
                    Shader.TileMode.CLAMP);
            mPaint = new Paint();
            mPaint.setAntiAlias(true);
            mPaint.setShader(bitmapShader);
        }

        @Override
        public void setBounds(int left, int top, int right, int bottom) {
            super.setBounds(left, top, right, bottom);
            rectF = new RectF(left, top, right, bottom);
        }

        @Override
        public void draw(Canvas canvas) {
            canvas.drawRoundRect(rectF, mRadius, mRadius, mPaint);
        }

        @Override
        public int getIntrinsicWidth() {
            return mBitmap.getWidth();
        }

        @Override
        public int getIntrinsicHeight() {
            return mBitmap.getHeight();
        }

        @Override
        public void setAlpha(int alpha) {
            mPaint.setAlpha(alpha);
        }

        @Override
        public void setColorFilter(ColorFilter cf) {
            mPaint.setColorFilter(cf);
        }

        @Override
        public int getOpacity() {
            return PixelFormat.TRANSLUCENT;
        }
    }


}

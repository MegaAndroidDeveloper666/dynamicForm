package ru.markstudio.marksdynamicforms.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;

import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.ViewCompat;

import org.apache.commons.lang3.StringUtils;

import ru.markstudio.marksdynamicforms.R;

public class DynamicBackgroundButton extends AppCompatButton {

    public enum Type {
        PRIMARY(1, R.color.defaultColorAccent, R.color.defaultColorAccentDark, R.color.defaultColorAccentDisabled, R.color.defaultButtonText),
        OUTLINED(2, R.color.defaultColorAccent, R.color.defaultColorAccentDark, R.color.defaultColorAccentDisabled, R.color.defaultColorAccent);

        private int type;
        private int color;
        private int colorPressed;
        private int colorDisabled;
        private int textColor;

        Type(int type, int color, int colorPressed, int colorDisabled, int textColor) {
            this.type = type;
            this.color = color;
            this.colorPressed = colorPressed;
            this.colorDisabled = colorDisabled;
            this.textColor = textColor;
        }

        public static Type getByInt(int typeInt) {
            if (typeInt == PRIMARY.type) {
                return PRIMARY;
            } else if (typeInt == OUTLINED.type) {
                return OUTLINED;
            } else {
                return PRIMARY;
            }
        }

        public int getColor() {
            return color;
        }

        public int getColorPressed() {
            return colorPressed;
        }

        public int getColorDisabled() {
            return colorDisabled;
        }

        public int getTextColor() {
            return textColor;
        }
    }


    int dynamicBackgroundColorActive;
    int dynamicBackgroundColorPressed;
    int dynamicBackgroundDisable;
    boolean stroked;
    int strokeWidth;
    int typeInt;
    Type type;

    private float radius;

    private Settings settings;

    private DynamicBackgroundButton(Context context) {
        super(context);
        settings = new Settings(context);
    }

    private DynamicBackgroundButton(Context context, Settings settings) {
        super(context);
        this.settings = settings;
    }

    private DynamicBackgroundButton(Context context, Settings settings, AttributeSet attrs) {
        super(context, attrs);
        this.settings = settings;
    }

    public DynamicBackgroundButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        settings = new Settings(context);

        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.DynamicBackgroundButton);

        radius = typedArray.getDimension(R.styleable.DynamicBackgroundButton_radius, AppUtils.dpToPx(getContext(), 8));

        stroked = typedArray.getBoolean(R.styleable.DynamicBackgroundButton_stroked, false);
        strokeWidth = typedArray.getInteger(R.styleable.DynamicBackgroundButton_stroke, AppUtils.dpToPx(getContext(), 2));
        typeInt = typedArray.getInteger(R.styleable.DynamicBackgroundButton_type, 1);
        type = Type.getByInt(typeInt);

        setDefaults(attrs);
        setType(type);

        dynamicBackgroundColorActive = typedArray.getColor(R.styleable.DynamicBackgroundButton_colorActive, ContextCompat.getColor(getContext(), type.getColor()));
        dynamicBackgroundColorPressed = typedArray.getColor(R.styleable.DynamicBackgroundButton_colorPressed, ContextCompat.getColor(getContext(), type.getColorPressed()));
        dynamicBackgroundDisable = typedArray.getColor(R.styleable.DynamicBackgroundButton_colorDisable, ContextCompat.getColor(getContext(), type.getColorDisabled()));
        typedArray.recycle();

        ViewCompat.setBackground(this, createDrawable());
    }

    public void setType(Type type) {
        dynamicBackgroundColorActive = ContextCompat.getColor(getContext(), type.getColor());
        dynamicBackgroundColorPressed = ContextCompat.getColor(getContext(), type.getColorPressed());
        dynamicBackgroundDisable = ContextCompat.getColor(getContext(), type.getColorDisabled());
        ViewCompat.setBackground(this, createDrawable());
        invalidate();
    }

    public void setCustomColors(int color, int colorPressed, int colorDisabled, int textColor) {
        dynamicBackgroundColorActive = color;
        dynamicBackgroundColorPressed = colorPressed;
        dynamicBackgroundDisable = colorDisabled;
        setTextColor(textColor);
        ViewCompat.setBackground(this, createDrawable());
        invalidate();
    }

    private void setDefaults(AttributeSet attrs) {
        final String androidNamespace = "http://schemas.android.com/apk/res/android";

        String textColor = attrs.getAttributeValue(androidNamespace, "textColor");
        if (StringUtils.isEmpty(textColor)) {
            setTextColor(getResources().getColor(type.getTextColor()));
        }

        String fontFamily = attrs.getAttributeValue(androidNamespace, "fontFamily");
        if (StringUtils.isEmpty(fontFamily)) {
            setTypeface(ResourcesCompat.getFont(getContext(), Typeface.BOLD));
        }

        String textSize = attrs.getAttributeValue(androidNamespace, "textSize");
        if (StringUtils.isEmpty(textSize)) {
            setTextSize(TypedValue.COMPLEX_UNIT_PX, 16);
        }

        String textAllCaps = attrs.getAttributeValue(androidNamespace, "textAllCaps");
        if (StringUtils.isEmpty(textAllCaps)) {
            setAllCaps(false);
        }

        String gravity = attrs.getAttributeValue(androidNamespace, "gravity");
        if (StringUtils.isEmpty(gravity)) {
            setGravity(Gravity.CENTER);
        }
    }


    private StateListDrawable createDrawable() {
        StateListDrawable stateListDrawable = new StateListDrawable();

        GradientDrawable mNormalBackground = new GradientDrawable();
        GradientDrawable mPressedBackground = new GradientDrawable();
        GradientDrawable mUnableBackground = new GradientDrawable();

        if (stroked || type == Type.OUTLINED) {
            int transparent = getResources().getColor(R.color.transparent);
            mNormalBackground.setColor(transparent);
            mPressedBackground.setColor(transparent);
            mUnableBackground.setColor(transparent);

            mNormalBackground.setStroke(strokeWidth, getDynamicBackgroundColorActive());
            mPressedBackground.setStroke(strokeWidth, getDynamicBackgroundColorPressed());
            mUnableBackground.setStroke(strokeWidth, getDynamicBackgroundDisable());
        } else {
            mNormalBackground.setColor(getDynamicBackgroundColorActive());
            mPressedBackground.setColor(getDynamicBackgroundColorPressed());
            mUnableBackground.setColor(getDynamicBackgroundDisable());
        }

        mNormalBackground.setCornerRadius(getRadius());
        mPressedBackground.setCornerRadius(getRadius());
        mUnableBackground.setCornerRadius(getRadius());


        stateListDrawable.addState(new int[]{-android.R.attr.state_pressed, android.R.attr.state_enabled}, mNormalBackground);
        stateListDrawable.addState(new int[]{android.R.attr.state_pressed, android.R.attr.state_enabled}, mPressedBackground);
        stateListDrawable.addState(new int[]{-android.R.attr.state_enabled}, mUnableBackground);

        return stateListDrawable;
    }

    public float getRadius() {
        return radius;
    }

    public int getDynamicBackgroundColorActive() {
        return dynamicBackgroundColorActive;
    }

    public int getDynamicBackgroundColorPressed() {
        return dynamicBackgroundColorPressed;
    }

    public int getDynamicBackgroundDisable() {
        return dynamicBackgroundDisable;
    }


}

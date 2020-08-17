package com.loe.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.Button;

@SuppressLint("AppCompatCustomView")
public class StateButton extends Button
{
    public StateButton(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init(context, context.obtainStyledAttributes(attrs, R.styleable.StateButton));
    }

    public StateButton(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init(context, context.obtainStyledAttributes(attrs, R.styleable.StateButton, defStyleAttr, 0));
    }

    @SuppressLint("NewApi")
    public StateButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes)
    {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, context.obtainStyledAttributes(attrs, R.styleable.StateButton, defStyleAttr, defStyleRes));
    }

    private Drawable state0Background;
    private int state0TextColor;
    private String state0Text;

    private Drawable state1Background;
    private int state1TextColor;
    private String state1Text;

    private Drawable state2Background;
    private int state2TextColor;
    private String state2Text;

    private boolean isTextChange;

    private int buttonState = 0;

    private void init(Context context, TypedArray typedArray)
    {
        // style="?android:attr/borderlessButtonStyle"
        isTextChange = typedArray.getBoolean(R.styleable.StateButton_is_text_change, false);

        state0Background = getBackground();
        state0TextColor = getTextColors().getDefaultColor();
        state0Text = getText().toString();

        if (isTextChange)
        {
            setTextColor(getColorList(state0TextColor));
        }

        state1Background = typedArray.getDrawable(R.styleable.StateButton_state1_background);
        state1TextColor = typedArray.getColor(R.styleable.StateButton_state1_text_color, 0);
        state1Text = typedArray.getString(R.styleable.StateButton_state1_text);

        state2Background = typedArray.getDrawable(R.styleable.StateButton_state2_background);
        state2TextColor = typedArray.getColor(R.styleable.StateButton_state2_text_color, 0);
        state2Text = typedArray.getString(R.styleable.StateButton_state2_text);

        setButtonState(typedArray.getInt(R.styleable.StateButton_button_state, 0));
    }

    public int getButtonState()
    {
        return buttonState;
    }

    public Boolean isStateEnable()
    {
        return buttonState > 0;
    }

    public void setStateEnable(boolean enable)
    {
        setButtonState(enable ? 1 : 0);
    }

    public void setButtonState(int buttonState)
    {
        this.buttonState = buttonState;
        switch (buttonState)
        {
            case 1:
                if (state1Background != null)
                {
                    setBackground(state1Background);
                }
                if (state1TextColor != 0)
                {
                    if (isTextChange)
                    {
                        setTextColor(getColorList(state1TextColor));
                    }
                    else
                    {
                        setTextColor(state1TextColor);
                    }
                }
                if (state1Text != null)
                {
                    setText(state1Text);
                }
                break;
            case 2:
                if (state2Background != null)
                {
                    setBackground(state2Background);
                }
                if (state2TextColor != 0)
                {
                    if (isTextChange)
                    {
                        setTextColor(getColorList(state2TextColor));
                    }
                    else
                    {
                        setTextColor(state2TextColor);
                    }
                }
                if (state2Text != null)
                {
                    setText(state2Text);
                }
                break;
            default:
                if (state0Background != null)
                {
                    setBackground(state0Background);
                }
                if (state0TextColor != 0)
                {
                    if (isTextChange)
                    {
                        setTextColor(getColorList(state0TextColor));
                    }
                    else
                    {
                        setTextColor(state0TextColor);
                    }
                }
                if (state0Text != null)
                {
                    setText(state0Text);
                }
                break;
        }
    }

    public boolean isTextChange()
    {
        return isTextChange;
    }

    private ColorStateList getColorList(int color)
    {
        int[][] states = new int[3][];
        states[0] = new int[]{android.R.attr.state_enabled, -android.R.attr.state_pressed};
        states[1] = new int[]{android.R.attr.state_enabled, android.R.attr.state_pressed};
        states[2] = new int[]{-android.R.attr.state_enabled};
        int alphaColor = (0x99 << 030) + (color & 0xFFFFFF);
        return new ColorStateList(states, new int[]{color, alphaColor, alphaColor});
    }
}
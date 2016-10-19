package ggikko.me.customkeyboardapp.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ggikko.me.customkeyboardapp.R;

/**
 * Created by ggikko on 2016. 10. 18..
 */

public class CustomKeyButtonView extends LinearLayout {

    @BindView(R.id.mainTextView) TextView mainTextView;
    @BindView(R.id.subTextView) TextView subTextView;

    private String mainText, subText, mainSecondText, subSecondText, specialCharacter;

    public CustomKeyButtonView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setUp(context, attrs);
    }

    public CustomKeyButtonView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setUp(context, attrs);
    }

    private void setUp(Context context, AttributeSet attrs) {
        inflate(context, R.layout.widget_custom_key_button_layout, this);
        ButterKnife.bind(this);
        loadAttr(context, attrs);
        invalidateText();
    }

    private void loadAttr(Context context, AttributeSet attrs) {
        final TypedArray attributes = context.obtainStyledAttributes(
                attrs, R.styleable.customKeyButtonView);

        mainText = attributes.getString(R.styleable.customKeyButtonView_main_text);
        subText = attributes.getString(R.styleable.customKeyButtonView_sub_text);
        mainSecondText = attributes.getString(R.styleable.customKeyButtonView_main_second_text);
        subSecondText = attributes.getString(R.styleable.customKeyButtonView_sub_second_text);
        specialCharacter = attributes.getString(R.styleable.customKeyButtonView_special_character);

        attributes.recycle();
    }

    private void invalidateText() {
        mainTextView.setText(mainText);
        if(isNotEmpty(subText)) {
            subTextView.setText(subText);
        }else{
            subTextView.setVisibility(View.GONE);
        }
    }

    public void changeTextWhenShiftButtonPressed(boolean isShiftButtonPressed){
        if(isShiftButtonPressed){
            mainTextView.setText(mainText);
            subTextView.setText(subText);
        }else{
            mainTextView.setText(mainSecondText);
            subTextView.setText(subSecondText);
        }
    }

    public void changeTextWhenSpecialCharacterButtonPressed(boolean isCharacterButtonPressed){
        if(!isCharacterButtonPressed){
            subTextView.setVisibility(View.GONE);
            mainTextView.setText(specialCharacter);
        }else{
            if(isNotEmpty(subText)) {
                subTextView.setVisibility(View.VISIBLE);
            }else{
                subTextView.setVisibility(View.GONE);
            }
            mainTextView.setText(mainText);
            subTextView.setText(subText);
        }
    }

    public String getText(){
        return mainTextView.getText().toString();
    }

    public static boolean isNotEmpty(final CharSequence cs) {
        return !isEmpty(cs);
    }

    public static boolean isEmpty(final CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

}

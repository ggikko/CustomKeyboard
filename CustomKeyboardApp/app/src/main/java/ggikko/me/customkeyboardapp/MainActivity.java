package ggikko.me.customkeyboardapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ggikko.me.customkeyboardapp.widget.CustomKeyButtonView;

public class MainActivity extends AppCompatActivity {

    private boolean isShiftButtonPressed = false;
    private boolean isSpecialCharacterButtonPressed = false;

    @BindView(R.id.typing) TextView typing;
    @BindView(R.id.keypadSpecialCharacter) TextView keypadSpecialCharacter;
    @BindView(R.id.keyboardWrapper) LinearLayout keyboardWrapper;

    @OnClick({
            R.id.keypadNum1, R.id.keypadNum2, R.id.keypadNum3, R.id.keypadNum4, R.id.keypadNum5,
            R.id.keypadNum6, R.id.keypadNum7, R.id.keypadNum8, R.id.keypadNum9, R.id.keypadNum0,

            R.id.keypadCharacterQ, R.id.keypadCharacterW, R.id.keypadCharacterE,
            R.id.keypadCharacterR, R.id.keypadCharacterT, R.id.keypadCharacterY,
            R.id.keypadCharacterU, R.id.keypadCharacterI, R.id.keypadCharacterO,
            R.id.keypadCharacterP,

            R.id.keypadCharacterA, R.id.keypadCharacterS, R.id.keypadCharacterD,
            R.id.keypadCharacterF, R.id.keypadCharacterG, R.id.keypadCharacterH,
            R.id.keypadCharacterJ, R.id.keypadCharacterK, R.id.keypadCharacterL,

            R.id.keypadCharacterZ, R.id.keypadCharacterX,
            R.id.keypadCharacterC, R.id.keypadCharacterV, R.id.keypadCharacterB,
            R.id.keypadCharacterN, R.id.keypadCharacterM, R.id.keypadBack,

            R.id.keypadSpecialCharacter, R.id.keypadSpace, R.id.keypadComplete
    })
    void pressKeyPad(View view) {

        switch (view.getId()) {
            //backButton
            case R.id.keypadBack:
                break;
            //특수문자
            case R.id.keypadSpecialCharacter:
                if(!isSpecialCharacterButtonPressed){
                    changeButtonTextWithSpecialCharacter(isSpecialCharacterButtonPressed);
                    isSpecialCharacterButtonPressed = true;
                    isShiftButtonPressed = false;
                    keypadSpecialCharacter.setText("영문");
                }else{
                    changeButtonTextWithSpecialCharacter(isSpecialCharacterButtonPressed);
                    isSpecialCharacterButtonPressed = false;
                    keypadSpecialCharacter.setText("특수");
                }
                break;
            //Space
            case R.id.keypadSpace:
                break;
            //Complete
            case R.id.keypadComplete:
                break;
            //others
            default:
                if (view instanceof CustomKeyButtonView) {
                    String character = ((CustomKeyButtonView) view).getText();
                    typing.setText(character);
                }
                break;
        }
    }

    @OnClick(R.id.keypadShift)
    void pressShiftKey(){
        if(!isSpecialCharacterButtonPressed && isShiftButtonPressed){
            changeCharacters();
            isShiftButtonPressed = false;
        }else if(!isSpecialCharacterButtonPressed && !isShiftButtonPressed){
            changeCharacters();
            isShiftButtonPressed = true;
        }
    }

    private void changeButtonTextWithSpecialCharacter(boolean isSpecialCharacter) {
        int childCount = keyboardWrapper.getChildCount();
        for(int i =0; i < childCount; i ++){
            View child = keyboardWrapper.getChildAt(i);
            if(child instanceof LinearLayout){
                int grandChildCount = ((LinearLayout) child).getChildCount();
                for(int j=0; j<grandChildCount; j++){
                    View grandChild = ((LinearLayout) child).getChildAt(j);
                    if(grandChild instanceof CustomKeyButtonView){
                        ((CustomKeyButtonView)grandChild).changeTextWhenSpecialCharacterButtonPressed(isSpecialCharacter);
                    }
                }
            }
        }
    }

    public void changeCharacters(){
        int childCount = keyboardWrapper.getChildCount();
        for(int i =0; i < childCount; i ++){
            View child = keyboardWrapper.getChildAt(i);
            if(child instanceof LinearLayout){
                int grandChildCount = ((LinearLayout) child).getChildCount();
                for(int j=0; j<grandChildCount; j++){
                    View grandChild = ((LinearLayout) child).getChildAt(j);
                    if(grandChild instanceof CustomKeyButtonView){
                        ((CustomKeyButtonView)grandChild).changeTextWhenShiftButtonPressed(isShiftButtonPressed);
                    }
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
     }
}

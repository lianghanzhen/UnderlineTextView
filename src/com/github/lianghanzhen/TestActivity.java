package com.github.lianghanzhen;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.RadioGroup;

public class TestActivity extends Activity implements RadioGroup.OnCheckedChangeListener {

    private UnderlineTextView changeHeightView;
    private UnderlineTextView changeColorView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        changeHeightView = (UnderlineTextView) findViewById(R.id.change_height_text_view);
        ((RadioGroup) findViewById(R.id.change_height_group)).setOnCheckedChangeListener(this);
        changeColorView = (UnderlineTextView) findViewById(R.id.change_color_text_view);
        ((RadioGroup) findViewById(R.id.change_color_group)).setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        if (radioGroup.getId() == R.id.change_height_group) {
            switch (checkedId) {
                case R.id.radio_button_1dp:
                    changeHeightView.setUnderlineHeight(getResources().getDimensionPixelSize(R.dimen.dp_1));
                    break;
                case R.id.radio_button_3dp:
                    changeHeightView.setUnderlineHeight(getResources().getDimensionPixelSize(R.dimen.dp_3));
                    break;
                case R.id.radio_button_6dp:
                    changeHeightView.setUnderlineHeight(getResources().getDimensionPixelSize(R.dimen.dp_6));
                    break;
                case R.id.radio_button_9dp:
                    changeHeightView.setUnderlineHeight(getResources().getDimensionPixelSize(R.dimen.dp_9));
                    break;
            }
        } else {
            switch (checkedId) {
                case R.id.radio_button_red:
                    changeColorView.setUnderlineColor(0xffcc0000);
                    break;
                case R.id.radio_button_green:
                    changeColorView.setUnderlineColor(0xff00cc00);
                    break;
                case R.id.radio_button_blue:
                    changeColorView.setUnderlineColor(0xff0000cc);
                    break;
                case R.id.radio_button_black:
                    changeColorView.setUnderlineColor(0xff000000);
                    break;
            }
        }
    }
}

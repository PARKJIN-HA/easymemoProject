package com.woon.memopad;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MemoPopupActivity extends Activity {
    RadioGroup radioGroup;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_memo_popup);

        radioGroup = (RadioGroup)findViewById(R.id.memoType);
        button = (Button) findViewById(R.id.enterMemo);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            int radioId = radioGroup.getCheckedRadioButtonId();
            RadioButton radioButton = (RadioButton)findViewById(radioId);
                if (radioButton.getText().toString().equals("기본")) {
                    Intent intent = new Intent(getApplicationContext(), SaveMemoActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}

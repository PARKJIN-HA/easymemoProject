package com.woon.memopad;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SettingActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        Spinner sizeSpinner = (Spinner) findViewById(R.id.fontSize);
        Spinner fontSpinner = (Spinner) findViewById(R.id.font);
        Spinner lineSpinner = (Spinner) findViewById(R.id.lineUp);
        ArrayAdapter sizeAdapter = ArrayAdapter.createFromResource(this, R.array.font_size, android.R.layout.simple_spinner_item);
        ArrayAdapter fontAdapter = ArrayAdapter.createFromResource(this, R.array.font, android.R.layout.simple_spinner_item);
        ArrayAdapter lineAdapter = ArrayAdapter.createFromResource(this, R.array.line_up, android.R.layout.simple_spinner_item);
        sizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fontAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lineAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sizeSpinner.setAdapter(sizeAdapter);
        fontSpinner.setAdapter(fontAdapter);
        lineSpinner.setAdapter(lineAdapter);
    }



    public void onMemoButtonClick(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }
    public void onCalendarButtonClick(View view) {
        Intent intent = new Intent(getApplicationContext(), CalendarActivity.class);
        startActivity(intent);
        finish();
    }
    public void onSearchButtonClick(View view) {
        Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
        startActivity(intent);
        finish();
    }
}

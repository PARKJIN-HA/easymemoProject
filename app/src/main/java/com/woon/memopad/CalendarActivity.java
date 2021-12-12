package com.woon.memopad;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class CalendarActivity extends AppCompatActivity {
    public String readDay = null;
    public String str = null;
    public CalendarView calendarView;
    public Button saveBtn, changeBtn, deleteBtn;
    public EditText contentEditText;
    public TextView dateText, contentText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        calendarView = findViewById(R.id.calendarView);
        dateText = findViewById(R.id.date);
        saveBtn = findViewById(R.id.saveBtn);
        deleteBtn = findViewById(R.id.deleteBtn);
        changeBtn = findViewById(R.id.changeBtn);
        contentText = findViewById(R.id.content);
        contentEditText = findViewById(R.id.contentEdit);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                dateText.setVisibility(View.VISIBLE);
                nullContent();
                dateText.setText(String.format("%d / %d / %d", year, month + 1, dayOfMonth));
                contentEditText.setText("");
                checkDay(year, month, dayOfMonth);
            }
        });
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                saveDiary(readDay);
                str = contentEditText.getText().toString();
                contentText.setText(str);
                haveContent();
            }
        });
    }

    public void checkDay(int cYear, int cMonth, int cDay) {
        readDay = "" + cYear + "-" + (cMonth + 1) + "" + "-" + cDay + ".txt";
        FileInputStream fileInputStream;

        try {
            fileInputStream = openFileInput(readDay);

            byte[] fileData = new byte[fileInputStream.available()];
            fileInputStream.read(fileData);
            fileInputStream.close();

            str = new String(fileData);
            contentText.setText(str);
            haveContent();

            changeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    nullContent();
                    contentEditText.setText(str);
                    contentText.setText(contentEditText.getText());
                }
            });

            deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    contentEditText.setText("");
                    nullContent();
                    removeDiary(readDay);
                }
            });

            if (contentText.getText().equals("")) {
                nullContent();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void nullContent() {
        contentText.setVisibility(View.INVISIBLE);
        contentEditText.setVisibility(View.VISIBLE);
        saveBtn.setVisibility(View.VISIBLE);
        changeBtn.setVisibility(View.INVISIBLE);
        deleteBtn.setVisibility(View.INVISIBLE);
    }

    void haveContent() {
        contentText.setVisibility(View.VISIBLE);
        contentEditText.setVisibility(View.INVISIBLE);
        saveBtn.setVisibility(View.INVISIBLE);
        changeBtn.setVisibility(View.VISIBLE);
        deleteBtn.setVisibility(View.VISIBLE);
    }

    @SuppressLint("WrongConstant")
    public void saveDiary(String readDay) {
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = openFileOutput(readDay, MODE_NO_LOCALIZED_COLLATORS);
            String content = contentEditText.getText().toString();
            fileOutputStream.write((content).getBytes());
            fileOutputStream.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("WrongConstant")
    public void removeDiary(String readDay) {
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = openFileOutput(readDay, MODE_NO_LOCALIZED_COLLATORS);
            String content = "";
            fileOutputStream.write((content).getBytes());
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onMemoButtonClick(View view) {
        Log.i("------------", "button--------------");
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }
    public void onSearchButtonClick(View view) {
        Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
        startActivity(intent);
        finish();
    }
    public void onSettingButtonClick(View view) {
        Intent intent = new Intent(getApplicationContext(), SettingActivity.class);
        startActivity(intent);
        finish();
    }}
package com.woon.memopad;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.woon.memopad.Recycler.RecyclerAdapter;
import com.woon.memopad.Room.AppDatabase;
import com.woon.memopad.Room.User;

import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity {

    private final int SAVE_MEMO_ACTIVITY = 1;
    private FloatingActionButton add;

    //리사이클러 뷰
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerAdapter adapter;
    private List<User> users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialized();

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);


        add.setOnClickListener(v -> {
            move();
        });

    }


    private void initialized() {
        add = findViewById(R.id.addMemo);

        recyclerView = findViewById(R.id.mainRecyclerView);
        linearLayoutManager = new LinearLayoutManager(this);
        adapter = new RecyclerAdapter();

        users = AppDatabase.getInstance(this).userDao().getAll();
        int size = users.size();
        for(int i = 0; i < size; i++){
            adapter.addItem(users.get(i));
        }
    }

    public void move() {
        Intent intent = new Intent(getApplicationContext(), MemoPopupActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        users = AppDatabase.getInstance(this).userDao().getAll();
        adapter.addItems((ArrayList) users);
        super.onStart();
    }

    // 추가
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
    public void onSettingButtonClick(View view) {
        Intent intent = new Intent(getApplicationContext(), SettingActivity.class);
        startActivity(intent);
        finish();
    }
}

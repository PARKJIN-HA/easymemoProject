package com.woon.memopad;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.woon.memopad.Recycler.AddressAdapter;

import java.util.ArrayList;
import java.util.List;

public class AddressActivity extends AppCompatActivity {

    EditText et_city, et_district, et_building, et_roadName, et_detail;
    String str_city, str_district, str_building, str_roadName, str_detail;
    List<AddressAdapter.ItemList> data;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        recyclerView = findViewById(R.id.address_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    public void saveAddress(View view) {
        et_city     = (EditText) findViewById(R.id.editText1);
        et_district = (EditText) findViewById(R.id.editText2);
        et_building = (EditText) findViewById(R.id.editText3);
        et_roadName = (EditText) findViewById(R.id.editText4);
        et_detail   = (EditText) findViewById(R.id.editText5);
        str_city    = et_city.getText().toString();
        str_district= et_district.getText().toString();
        str_building= et_building.getText().toString();
        str_roadName= et_roadName.getText().toString();
        str_detail  = et_detail.getText().toString();

        data = new ArrayList<>();
        data.add(new AddressAdapter.ItemList(str_city, str_district, str_building, str_roadName, str_detail));

        recyclerView.setAdapter(new AddressAdapter(data));
    }
}

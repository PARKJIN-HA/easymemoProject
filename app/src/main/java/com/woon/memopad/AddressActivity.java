package com.woon.memopad;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.woon.memopad.Recycler.AddressAdapter;

import java.util.ArrayList;
import java.util.List;

public class AddressActivity extends AppCompatActivity {

    EditText et_city, et_district, et_town, et_roadName, et_detail;
    String str_city, str_district, str_town, str_roadName, str_detail;
    List<AddressAdapter.ItemList> data;
    private RecyclerView recyclerView;
    String addressNum;

    AddDBManager dbManager;
    SQLiteDatabase sqLiteDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        LinearLayout layout = (LinearLayout) findViewById(R.id.address);

        Intent intent = getIntent();
        addressNum = intent.getStringExtra("number");

        try {
            dbManager = new AddDBManager(this);
            sqLiteDatabase = dbManager.getReadableDatabase();
            Cursor cursor = sqLiteDatabase.query("Address", null, "addressNum = ?", new String[]{addressNum}, null, null, null, null);

            int i = 0;
            while (cursor.moveToNext()) {
                String str_city = cursor.getString(cursor.getColumnIndexOrThrow("city"));
                String str_district = cursor.getString(cursor.getColumnIndexOrThrow("district"));
                String str_town = cursor.getString(cursor.getColumnIndexOrThrow("town"));
                String str_roadName = cursor.getString(cursor.getColumnIndexOrThrow("roadName"));
                String str_detail = cursor.getString(cursor.getColumnIndexOrThrow("detailAddr"));

                LinearLayout layout_item = new LinearLayout(this);
                layout_item.setOrientation(LinearLayout.VERTICAL);
                layout_item.setPadding(20, 10, 20, 10);
                layout_item.setId(i);
                layout_item.setBackgroundColor(getResources().getColor(R.color.background));
                layout_item.setTag(str_city);

                TextView tv_city = new TextView(this);
                tv_city.setBackgroundColor(getResources().getColor(R.color.backgroundText));
                tv_city.setTextSize(30);
                tv_city.setText("시/도:   " + str_city);
                layout_item.addView(tv_city);

                TextView tv_district = new TextView(this);
                tv_district.setText("시/군/구: " + str_district);
                layout_item.addView(tv_district);

                TextView tv_town = new TextView(this);
                tv_town.setText("읍/면/동: " + str_town);
                layout_item.addView(tv_town);

                TextView tv_roadName = new TextView(this);
                tv_roadName.setText("도로명:   " + str_roadName);
                layout_item.addView(tv_roadName);

                TextView tv_detail = new TextView(this);
                tv_detail.setText("상세주소:    " + str_detail);
                layout_item.addView(tv_detail);

                TextView tv = new TextView(this);
                tv.setText(addressNum);
                layout_item.addView(tv);

                layout.addView(layout_item);

                i++;
            }

        } catch (SQLException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void addAddress(View view) {
        et_city     = (EditText) findViewById(R.id.city);
        et_district = (EditText) findViewById(R.id.district);
        et_town     = (EditText) findViewById(R.id.town);
        et_roadName = (EditText) findViewById(R.id.roadName);
        et_detail   = (EditText) findViewById(R.id.detailAddr);
        str_city    = et_city.getText().toString();
        str_district= et_district.getText().toString();
        str_town    = et_town.getText().toString();
        str_roadName= et_roadName.getText().toString();
        str_detail  = et_detail.getText().toString();



        Intent intent = getIntent();
        addressNum = intent.getStringExtra("number");

        try {
            dbManager = new AddDBManager(this);
            sqLiteDatabase = dbManager.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("addressNum", addressNum);
            values.put("city", str_city);
            values.put("district", str_district);
            values.put("town", str_town);
            values.put("roadName", str_roadName);
            values.put("detailAddr", str_detail);

            long newRowId = sqLiteDatabase.insert("Address", null, values);

            sqLiteDatabase.close();
            dbManager.close();

        } catch (SQLException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

        finish();
        startActivity(intent);
    }

}

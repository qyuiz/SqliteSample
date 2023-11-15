package com.example.sqlitesample;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.textview);

        helper = new DatabaseHelper(this);
        try {
            helper.createDatabase();
        }
        catch (IOException e) {
            throw new Error("Unable to create database");
        }
        StringBuilder builder = new StringBuilder();

        SQLiteDatabase db = helper.getReadableDatabase();
        String sql = "select name, age from users";

        try {
            Cursor cursor = db.rawQuery(sql, null);
            while(cursor.moveToNext()) {
                builder.append(cursor.getString(0) + " ");
                builder.append(cursor.getInt(1) + " ");
            }
        } finally {
            db.close();
        }
        textView.setText(builder.toString());
    }
}
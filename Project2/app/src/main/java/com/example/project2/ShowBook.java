package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.Queue;

public class ShowBook extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbHelper;
    Button bt2;
    TextView text1,text2,text3,text4,text5;
    Queue<String> bookQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_book);
        dbHelper = new DataHelper(this);
        text1 = (TextView) findViewById(R.id.textView1);
        text2 = (TextView) findViewById(R.id.textView2);
        text3 = (TextView) findViewById(R.id.textView3);
        text4 = (TextView) findViewById(R.id.textView4);
        text5 = (TextView) findViewById(R.id.textView5);
        bookQueue = new LinkedList<>(); // Initialize the Queue

        // Fetch book details from the cursor and add them to the Queue
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM biodata WHERE title = '" + getIntent().getStringExtra("title") + "'",null);
        cursor.moveToFirst();

        if (cursor.getCount()>0)
        {
            cursor.moveToPosition(0);
            text1.setText(cursor.getString(0).toString());
            text2.setText(cursor.getString(1).toString());
            text3.setText(cursor.getString(2).toString());
            text4.setText(cursor.getString(3).toString());
            text5.setText(cursor.getString(4).toString());
        }
        bt2 = (Button) findViewById(R.id.button1);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO Auto-generated method stub
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//Inflate the menu: this adds items to the action bar if it is present
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}



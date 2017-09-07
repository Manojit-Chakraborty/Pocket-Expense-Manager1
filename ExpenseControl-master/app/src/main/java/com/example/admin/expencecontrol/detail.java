package com.example.admin.expencecontrol;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

public class detail extends AppCompatActivity {
    TextView tv1, tv2, tv3, tv4;
    String[] separated;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //Initialization
        db = openOrCreateDatabase("MONEY", MODE_PRIVATE, null);
        tv1 = (TextView) findViewById(R.id.type);
        tv2 = (TextView) findViewById(R.id.money);
        tv3 = (TextView) findViewById(R.id.cate);
        tv4 = (TextView) findViewById(R.id.tv);
        String s = getIntent().getExtras().getString("u");
        separated = s.split(" ");
        tv1.setText(separated[0] + separated[1] + separated[2]);
        tv2.setText(separated[4].substring(0, separated[4].length() - 1));
        tv3.setText(separated[5]);
        tv4.setText(separated[6]);

    }

    public void delete(View v) {

        String s1 = separated[6];
        String query = "select * from userdetails where expence='" + s1 + "'";
        Cursor c = db.rawQuery(query, null);
        if (c.moveToFirst() == true) {
            db.execSQL("delete from userdetails where expence='" + s1 + "' and date='" + separated[0] + "' and month='" + separated[1] + "' and year='" + separated[2] + "' and ammount='" + separated[4].substring(0, separated[4].length() - 1) + "'  ");
            Toast.makeText(getApplicationContext(), "delete ✄✄", Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(detail.this, "" + s1, Toast.LENGTH_SHORT).show();
        finish();
    }

    /*@Override
    public void onBackPressed() {

        String sss = getIntent().getExtras().getString("j");


        if (sss == "null") {
            Toast.makeText(getApplicationContext(), "income"+sss, Toast.LENGTH_SHORT).show();
            Intent moveback =
                    new Intent(detail.this, spend.class);
            startActivity(moveback);
            finish();
        }
        else
        {
            Toast.makeText(getApplicationContext(), "spend"+sss, Toast.LENGTH_SHORT).show();
            Intent moveback =
                    new Intent(detail.this, income.class);
            startActivity(moveback);
            finish();
        }
        }*/

    }
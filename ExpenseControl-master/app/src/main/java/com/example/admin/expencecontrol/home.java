package com.example.admin.expencecontrol;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class home extends AppCompatActivity {
    TextView tv,tv1,tv2,tv3;
    SQLiteDatabase db;
    boolean doubleBackToExitPressedOnce = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        tv = (TextView) findViewById(R.id.rs3);
        tv1 = (TextView) findViewById(R.id.rsmonth);
        tv2 = (TextView) findViewById(R.id.rsincome);
        tv3 = (TextView) findViewById(R.id.rsspend);

        db = openOrCreateDatabase("MONEY", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS userdetails" + "(date integer,month varchar,year integer,expence varchar,ammount integer,category varchar);");
        String s;
        Date dt = new Date();
        int i = dt.getMonth();
        i = i + 1;
        int j = dt.getYear();
        j = j + 1900;
        if (i == 1) {
            s = "January";
        } else if (i == 2) {
            s = "February";
        } else if (i == 3) {
            s = "March";
        } else if (i == 4) {
            s = "April";
        } else if (i == 5) {
            s = "May";
        } else if (i == 6) {
            s = "June";
        } else if (i == 7) {
            s = "July";
        } else if (i == 8) {
            s = "August";
        } else if (i == 9) {
            s = "September";
        } else if (i == 10) {
            s = "October";
        } else if (i == 11) {
            s = "November";
        } else {
            s = "December";
        }

        //for income
        String type = "Income";
        Integer money1 = 0;
        Integer money2 = 0;
        String m = "";
        String query = "select * from userdetails where month='" + s + "'and year='" + j + "'and expence='" + type + "'";
        Cursor c = db.rawQuery(query, null);
        if (c.moveToFirst()) {
            while (c.isAfterLast() == false) {
                m = c.getString(4);
                money1 = Integer.parseInt(m);
                money2 = money1 + money2;
                c.moveToNext();
            }
        }

        //for spend
        String type2 = "Spend";
        Integer money3 = 0;
        Integer money4 = 0;
        String mm = "";
        String query2 = "select * from userdetails where month='" + s + "'and year='" + j + "'and expence='" + type2 + "'";
        Cursor cc = db.rawQuery(query2, null);
        if (cc.moveToFirst()) {
            while (cc.isAfterLast() == false) {
                mm = cc.getString(4);
                money3 = Integer.parseInt(mm);
                money4 = money3 + money4;
                cc.moveToNext();
            }
        }
        Integer money = money2 - money4;
        String mmm = String.valueOf(money);
        tv.setText("Rs. " + mmm);
        tv1.setText("" + s);
        tv2.setText("" + money2);
        tv3.setText("" + money4);
    }
    public void sp(View v) {
        Intent i = new Intent(home.this, spend.class);
        startActivity(i);
    }
    public void cain(View v) {
        Intent i = new Intent(home.this, income.class);
        startActivity(i);
    }
    public void add(View v) {
        Intent i = new Intent(home.this, add.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}

package com.example.admin.expencecontrol;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class income extends ListActivity {
    SQLiteDatabase db;
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        String item=l.getItemAtPosition(position).toString();
        Intent i=new Intent(income.this,detail.class);
        i.putExtra("u",item+" Income");
        i.putExtra("j",1);
        startActivity(i);
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);
        db=openOrCreateDatabase("MONEY",MODE_PRIVATE,null);
        String type="Income";
        String query="select * from userdetails where expence='"+type+"'";
        Cursor c=db.rawQuery(query,null);
        ArrayList list=new ArrayList();
        if(c.moveToFirst()) {
            while (c.isAfterLast() == false) {
                list.add(c.getString(0)+" "+c.getString(1)+" "+c.getString(2)+" "+"Rs. "+c.getString(4)+"\n "+c.getString(5));
                c.moveToNext();
            }
        }
        else {
            Toast.makeText(getApplicationContext(), "Add your Income", Toast.LENGTH_SHORT).show();
            finish();
        }
        ArrayAdapter adapter4=new ArrayAdapter(getApplicationContext(),R.layout.mylistview,list);
        setListAdapter(adapter4);
    }
}

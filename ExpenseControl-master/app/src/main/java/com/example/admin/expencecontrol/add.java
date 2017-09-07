package com.example.admin.expencecontrol;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class add extends AppCompatActivity {
    Spinner date, month, year, expence;
    Spinner category;
    EditText amount;
    SQLiteDatabase db;
    private TextView text_date;
    private Button button;
    static final int DATE_DIALOG_ID = 100;
    private int year3;
    private int month3;
    private int day3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        //initialization
        amount = (EditText) findViewById(R.id.amount);
        expence = (Spinner) findViewById(R.id.expence);
        category = (Spinner) findViewById(R.id.categoryname);

        //database create
        db = openOrCreateDatabase("MONEY", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS userdetails" + "(date integer,month varchar,year integer,expence varchar,ammount integer,category varchar);");

        //add income or spend
        final ArrayList expences = new ArrayList();
        expences.add("Spend");
        expences.add("Income");
        ArrayAdapter adapter4 = new ArrayAdapter(this, R.layout.mylayout, expences);
        expence.setAdapter(adapter4);


        //add category
        expence.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if(expence.getSelectedItem()=="Spend")
                {
                    ArrayList category1 = new ArrayList();
                    category1.add("-Select a Category-");
                    category1.add("Books");
                    category1.add("Medicine");
                    category1.add("Other");
                    category1.add("Education");
                    category1.add("Stationary");
                    category1.add("Transport");
                    category1.add("Bills");
                    category1.add("Loan");
                    category1.add("Entertainment");
                    category1.add("Car");
                    category1.add("Clothes");
                    category1.add("Dining");
                    category1.add("Dinner");
                    category1.add("Electricity");
                    category1.add("Fast Food");
                    category1.add("Grocery");
                    category1.add("Shopping");
                    category1.add("Travel");

                    ArrayAdapter adapter5 = new ArrayAdapter(getApplicationContext(), R.layout.mylayout, category1);
                    category.setAdapter(adapter5);
                }
                else
                {
                    ArrayList category2 = new ArrayList();
                    category2.add("-Select a Category-");
                    category2.add("gift");
                    category2.add("Salary");
                    category2.add("loan");
                    ArrayAdapter adapter6 = new ArrayAdapter(getApplicationContext(), R.layout.mylayout, category2);
                    category.setAdapter(adapter6);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                ArrayList category1 = new ArrayList();
                category1.add("-Select a Category-");
                category1.add("Books");
                category1.add("Medicine");
                category1.add("Other");
                category1.add("Education");
                category1.add("Stationary");
                category1.add("Transport");
                category1.add("Bills");
                category1.add("Loan");
                category1.add("Entertainment");
                ArrayAdapter adapter5 = new ArrayAdapter(getApplicationContext(), R.layout.mylayout, category1);
                category.setAdapter(adapter5);
            }
        });

        //datepicker
        setCurrentDate();
        addButtonListener();
    }
    // display current date both on the text view and the Date Picker when the application starts.
    public void setCurrentDate() {
        text_date = (TextView) findViewById(R.id.text_date);
        final Calendar calendar = Calendar.getInstance();
        year3 = calendar.get(Calendar.YEAR);
        month3 = calendar.get(Calendar.MONTH);
        day3 = calendar.get(Calendar.DAY_OF_MONTH);
        // set current date into textview
        text_date.setText(new StringBuilder()
                // Month is 0 based, so you have to add 1
                .append(month3 + 1).append("-")
                .append(day3).append("-")
                .append(year3).append(" "));
    }
    public void addButtonListener() {
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }
        });
    }
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                // set date picker as current date
                return new DatePickerDialog(this, datePickerListener, year3, month3,day3);
        }
        return null;
    }
    public DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear,int selectedMonth, int selectedDay) {
            year3 = selectedYear;
            month3 = selectedMonth;
            day3 = selectedDay;
            // set selected date into Text View
            text_date.setText(new StringBuilder().append(day3).append("-").append(month3 + 1).append("-").append(year3).append(" "));
    }
    };

    public void add(View v)
    {
        String s;
        int i=month3+1;
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
        String amnt=amount.getText().toString();
        String exp = expence.getSelectedItem().toString();
        String cat=category.getSelectedItem().toString();
        if(cat=="-Select a Category-")
        {
            cat="Cash";
        }
        String query="Insert into userdetails values('"+day3+"','"+s+"','"+year3+"','"+exp+"','"+amnt+"','"+cat+"')";
        db.execSQL(query);
        Toast.makeText(getApplicationContext(),"add!", Toast.LENGTH_SHORT).show();
        Intent j=new Intent(add.this,home.class);
        startActivity(j);
        finish();
    }
    @Override
    public void onBackPressed()
    {

        Intent moveback =
                new Intent(add.this, home.class);
        startActivity(moveback);
        finish();
    }
}

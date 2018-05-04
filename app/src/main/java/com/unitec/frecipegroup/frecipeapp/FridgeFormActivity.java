package com.unitec.frecipegroup.frecipeapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.unitec.frecipegroup.frecipeapp.model.Item;
import com.unitec.frecipegroup.frecipeapp.sqlite.MySQLiteHelper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class FridgeFormActivity extends AppCompatActivity {
    private Spinner spinner_unit;
    private Spinner spinner_category;
    private EditText date;
    private DatePickerDialog datePickerDialog;

    private MySQLiteHelper db;
    private Button btnSave;
    private Button btnDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fridge_form);

        // Sets up the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        addItemsOnSpinner();
        addCategoriesOnSpinner();
        createDatePciker();

        db = MySQLiteHelper.getInstance(this);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText title = (EditText) findViewById(R.id.txt_title);
                final String   txtTitle   = title.getText().toString();

                EditText quantity = (EditText) findViewById(R.id.text_Qty);
                final double textQty   =  Double.parseDouble(quantity.getText().toString());

                Spinner unit = (Spinner) findViewById(R.id.spinner_unit);
                final String textUnit   = unit.getSelectedItem().toString();

                Spinner category = (Spinner) findViewById(R.id.spinner_category2);
                final String txtCat   = category.getSelectedItem().toString();

                EditText date = (EditText) findViewById(R.id.date);
                final String txtDate   = date.getText().toString();

                Item item = new Item(txtTitle, textQty, textUnit, txtCat, txtDate);

                db.addItem(item);

                title.getText().clear();
                quantity.getText().clear();
                date.getText().clear();
                unit.setSelection(0);
                category.setSelection(0);
            }
        });

        btnDone = (Button) findViewById(R.id.btnDone);
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FridgeFormActivity.this, FridgeActivity.class);
                startActivity(intent);
            }
        });

    }

    // create date picker
    public void createDatePciker() {
        // initiate the date picker and a button
        date = (EditText) findViewById(R.id.date);
        // perform click event on edit text
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(FridgeFormActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                date.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
    }

    // add categories into spinner dynamically
    public void addCategoriesOnSpinner() {
        spinner_category = (Spinner) findViewById(R.id.spinner_category2);
        List<String> list = new ArrayList<String>();
        list.add("Grains");
        list.add("Vegetables");
        list.add("Fruits");
        list.add("Dairy");
        list.add("Meat");
        list.add("Other");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_category.setAdapter(dataAdapter);

    }

    // add items into spinner dynamically
    public void addItemsOnSpinner(){
        spinner_unit = (Spinner) findViewById(R.id.spinner_unit);
        List<String> list = new ArrayList<String>();
        list.add("g");
        list.add("kg");
        list.add("ml");
        list.add("l");
        list.add("oz");
        list.add("lb");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_unit.setAdapter(dataAdapter);
    }

    // Set custom app_bar_menu to toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.app_bar_menu, menu);
        return true;
    }

    // Sets listeners to the navigation icons in the toolbars,
    // so that the user can navigate through the application screens.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.item_home:
                intent = new Intent(FridgeFormActivity.this, HomeActivity.class);
                startActivity(intent);
                return true;
            case R.id.item_fridge:
                intent = new Intent(FridgeFormActivity.this, FridgeActivity.class);
                startActivity(intent);
                return true;
            case R.id.item_book:
                intent = new Intent(FridgeFormActivity.this, RecipeActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}

package com.unitec.frecipegroup.frecipeapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.unitec.frecipegroup.frecipeapp.model.Item;
import com.unitec.frecipegroup.frecipeapp.sqlite.MySQLiteHelper;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.LinkedList;
import java.util.List;

public class FridgeActivity extends AppCompatActivity implements Button.OnClickListener {
    private Button btn_grains;
    private Button btn_vegetables;
    private Button btn_fruits;
    private Button btn_meat;
    private Button btn_dairy;
    private Button btn_other;
    private Button btn_expiring;
    private Button btn_expired;

    private List<Item> items;
    private View promptView;
    final Context context = this;
    private MySQLiteHelper db;
    private TextView catName;
    String category;

    private TableLayout tl;
    private TableRow tr;
    private TextView item, quantity, unit, expiry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fridge);

        // Sets up the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button btn_add = (Button) findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FridgeActivity.this, FridgeFormActivity.class);
                startActivity(intent);
            }
        });

        db = MySQLiteHelper.getInstance(this);

        btn_grains = (Button) findViewById(R.id.btn_grains);
        btn_grains.setOnClickListener(this);

        btn_vegetables = (Button) findViewById(R.id.btn_vegetables);
        btn_vegetables.setOnClickListener(this);

        btn_fruits = (Button) findViewById(R.id.btn_fruits);
        btn_fruits.setOnClickListener(this);

        btn_meat = (Button) findViewById(R.id.btn_meat);
        btn_meat.setOnClickListener(this);

        btn_dairy = (Button) findViewById(R.id.btn_dairy);
        btn_dairy.setOnClickListener(this);

        btn_other = (Button) findViewById(R.id.btn_other);
        btn_other.setOnClickListener(this);

        btn_expiring = (Button) findViewById(R.id.btn_expiring);
        btn_expiring.setOnClickListener(this);

        btn_expired = (Button) findViewById(R.id.btn_expired);
        btn_expired.setOnClickListener(this);
    }

    /** This function add the headers to the table **/
    public void addHeaders(){

        /** Create a TableRow dynamically **/
        tr = new TableRow(this);
        tr.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.FILL_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));

        /** Creating a TextView to add to the row **/
        TextView item = new TextView(this);
        item.setText("Item");
        item.setWidth(300);
        item.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        item.setTextColor(Color.WHITE);
        item.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        item.setPadding(5, 5, 5, 0);
        tr.addView(item);  // Adding textView to tablerow.

        /** Creating a TextView to add to the row **/
        TextView quantity = new TextView(this);
        quantity.setText("Qty");
        quantity.setWidth(150);
        quantity.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        quantity.setTextColor(Color.WHITE);
        quantity.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        quantity.setPadding(5, 5, 5, 0);
        tr.addView(quantity);  // Adding textView to tablerow.

        /** Creating a TextView to add to the row **/
        TextView unit = new TextView(this);
        unit.setText("Unit");
        unit.setWidth(120);
        unit.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        unit.setTextColor(Color.WHITE);
        unit.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        unit.setPadding(5, 5, 5, 0);
        tr.addView(unit);  // Adding textView to tablerow.

        /** Creating a TextView to add to the row **/
        TextView expiry = new TextView(this);
        expiry.setText("Expiry");
        expiry.setWidth(250);
        expiry.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        expiry.setTextColor(Color.WHITE);
        expiry.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        expiry.setPadding(5, 5, 5, 0);
        tr.addView(expiry);  // Adding textView to tablerow.

        // Add the TableRow to the TableLayout
        tl.addView(tr, new TableLayout.LayoutParams(
                TableRow.LayoutParams.FILL_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));


    }

    /** This function add the data to the table **/
    public void addData(){

        for (Item obj : items)
        {
            /** Create a TableRow dynamically **/
            tr = new TableRow(this);
            tr.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.FILL_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));

            /** Creating a TextView to add to the row **/
            item = new TextView(this);
            item.setText(obj.getName());
            item.setWidth(300);
            item.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);
            item.setPadding(5, 5, 5, 5);
            tr.addView(item);  // Adding textView to tablerow.

            /** Creating another textview **/
            quantity = new TextView(this);
            quantity.setText(Double.toString(obj.getQuantity()));
            quantity.setWidth(150);
            quantity.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);
            quantity.setPadding(5, 5, 5, 5);
            tr.addView(quantity);  // Adding textView to tablerow.

            /** Creating a TextView to add to the row **/
            unit = new TextView(this);
            unit.setText(obj.getUnit());
            unit.setWidth(120);
            unit.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);
            unit.setPadding(5, 5, 5, 5);
            tr.addView(unit);  // Adding textView to tablerow.

            /** Creating a TextView to add to the row **/
            expiry = new TextView(this);
            expiry.setText(obj.getExpiryDate());
            expiry.setWidth(250);
            expiry.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);
            expiry.setPadding(5, 5, 5, 5);
            tr.addView(expiry);  // Adding textView to tablerow.

            // Add the TableRow to the TableLayout
            tl.addView(tr, new TableLayout.LayoutParams(
                    TableRow.LayoutParams.FILL_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
        }
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
                intent = new Intent(FridgeActivity.this, HomeActivity.class);
                startActivity(intent);
                return true;
            case R.id.item_fridge:
                return true;
            case R.id.item_book:
                intent = new Intent(FridgeActivity.this, RecipeActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_grains:
                items = db.getAllItemsForCategory("Grains");
                category = "Grains";
                displayItems();
                break;
            case R.id.btn_dairy:
                items = db.getAllItemsForCategory("Dairy");
                category = "Dairy";
                displayItems();
                break;
            case R.id.btn_fruits:
                items = db.getAllItemsForCategory("Fruits");
                category = "Fruits";
                displayItems();
                break;
            case R.id.btn_vegetables:
                items = db.getAllItemsForCategory("Vegetables");
                category = "Vegetables";
                displayItems();
                break;
            case R.id.btn_meat:
                items = db.getAllItemsForCategory("Meat");
                category = "Meat";
                displayItems();
                break;
            case R.id.btn_other:
                items = db.getAllItemsForCategory("Other");
                category = "Other";
                displayItems();
                break;
            case R.id.btn_expiring:
                items = db.getAllItems();
                category = "Expiring Soon";
                displayItems();
                break;
            case R.id.btn_expired:
                items = db.getAllItems();
                category = "Expired";
                displayItems();
                break;
        }
    }

    private void displayItems() {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        promptView = layoutInflater.inflate(R.layout.display_items, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setView(promptView);
        catName = (TextView) promptView.findViewById(R.id.categoryName);
        catName.setText(category);

        if(category.equals("Expiring Soon")) {
            setExpiringItems();
        } else if(category.equals("Expired")) {
            setExpiredItems();
        }

        tl = (TableLayout) promptView.findViewById(R.id.table);
        addHeaders();
        addData();

        alertDialogBuilder.setCancelable(false)
                .setNegativeButton("Back",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        });

        AlertDialog alertD = alertDialogBuilder.create();
        alertD.show();
    }

    private void setExpiringItems() {
        // get today's date
        // for each item in items, get expiry date
        // if ((expiry date - today's date) == 3 days, then do nothing, else remove from items

        DateTime today = new DateTime();
        String todayString = today.toString("dd/MM/yyyy");
        Log.i("today", todayString);

        int dayDifference = 0;
        List<Item> newItems = new LinkedList<>();

        for (Item i : items) {
            String expiryString = i.getExpiryDate();
            DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy");
            DateTime expiry = formatter.parseDateTime(expiryString);

            dayDifference = Days.daysBetween(today.toLocalDate(), expiry.toLocalDate()).getDays();

            if ((dayDifference < 4) & (dayDifference > -1)) {
                newItems.add(i);
            }
        }
        items = newItems;
    }

    private void setExpiredItems() {
        // get today's date
        // for each item in items, get expiry date
        // if expiry date is equal to or greater than today's date, then do nothing, else remove from items

        DateTime today = new DateTime();
        List<Item> newItems = new LinkedList<>();

        for (Item i : items) {
            String expiryString = i.getExpiryDate();
            DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy");
            DateTime expiry = formatter.parseDateTime(expiryString);

            if (today.isAfter(expiry)) {
                newItems.add(i);
            }
        }
        items = newItems;
    }
}
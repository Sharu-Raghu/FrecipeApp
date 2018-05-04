package com.unitec.frecipegroup.frecipeapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


import com.unitec.frecipegroup.frecipeapp.model.Ingredient;
import com.unitec.frecipegroup.frecipeapp.model.Item;
import com.unitec.frecipegroup.frecipeapp.model.Recipe;
import com.unitec.frecipegroup.frecipeapp.sqlite.MySQLiteHelper;

import java.util.LinkedList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private  List<Ingredient> ingredients;
    private TableLayout tl;
    private TableRow tr;
    private TextView name;
    private List<Recipe> frecipes;
    private MySQLiteHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        db = MySQLiteHelper.getInstance(this);

        // Sets up the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        frecipes = new LinkedList<Recipe>();
        tl = (TableLayout) findViewById(R.id.table);
        generateRecipes();
        addHeaders();
        addData();
    }

    private void generateRecipes() {
        int expected = 0, actual = 0;
        List<Recipe> recipes = db.getAllRecipes();
        List<Item> items = db.getAllItems();
        for(Item i : items) {
            //convert all units to either g or ml
            if(i.getUnit() == "l") {
                i.setQuantity(i.getQuantity()/1000);
                i.setUnit("ml");
            } else if (i.getUnit() != "g"){
                switch(i.getUnit()) {
                    case "kg" :
                        i.setQuantity(i.getQuantity()/1000);
                        i.setUnit("g");
                        break;
                    case "oz" :
                        i.setQuantity(i.getQuantity() * 28.38);
                        i.setUnit("g");
                        break;
                    case "lb" :
                        i.setQuantity(i.getQuantity() * 453.59);
                        i.setUnit("g");
                        break;
                    case "ml" :
                        break;
                }
            }
        }
        for (Recipe recipe : recipes) {
            List<Ingredient> ingredients = db.getAllIngredientsForRecipe(recipe);
            expected = ingredients.size();
            Log.d("Expected: ", expected + "ingredients");

            for(Ingredient i : ingredients) {
                //convert all units to either g or ml
                if(i.getUnit() == "l") {
                    i.setQuantity(i.getQuantity()/1000);
                    i.setUnit("ml");
                } else if (i.getUnit() != "g"){
                    switch(i.getUnit()) {
                        case "kg" :
                            i.setQuantity(i.getQuantity()/1000);
                            i.setUnit("g");
                            break;
                        case "oz" :
                            i.setQuantity(i.getQuantity() * 28.38);
                            i.setUnit("g");
                            break;
                        case "lb" :
                            i.setQuantity(i.getQuantity() * 453.59);
                            i.setUnit("g");
                            break;
                        case "ml" :
                            break;
                    }
                }
                for (Item item : items) {
                    if(item.getName().equals(i.getName())) {
                        if(item.getQuantity() >= i.getQuantity()) {
                            actual++;
                            Log.d("Actual: ", actual + "ingredients");
                        }
                    }
                }
            }
            if (actual == expected) {
                frecipes.add(recipe);
            }
            actual = 0;
            expected = 0;
            ingredients.clear();
            Log.d("variables: ", "cleared");
        }
    }

    // Set custom app_bar_menu to toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.app_bar_menu, menu);
        return true;
    }

    /** This function add the headers to the table **/
    private void addHeaders(){

        /** Create a TableRow dynamically **/
        tr = new TableRow(this);
        tr.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));

        /** Creating a TextView to add to the row **/
        TextView item = new TextView(this);
        item.setText("Recipe");
        item.setWidth(320);
        item.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        item.setTextColor(Color.WHITE);
        item.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        item.setPadding(5, 5, 5, 0);
        tr.addView(item);  // Adding textView to tablerow.


        // Add the TableRow to the TableLayout
        tl.addView(tr, new TableLayout.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));


    }

    /** This function add the data to the table **/
    private void addData(){

        for (Recipe obj : frecipes)
        {
            /** Create a TableRow dynamically **/
            tr = new TableRow(this);
            tr.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));

            /** Creating a TextView to add to the row **/
            name = new TextView(this);
            name.setText(obj.getName());
            name.setWidth(320);
            name.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);
            name.setPadding(5, 5, 5, 5);
            tr.addView(name);  // Adding textView to tablerow.

            // Add the TableRow to the TableLayout
            tl.addView(tr, new TableLayout.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
        }
    }


    // Sets listeners to the navigation icons in the toolbars,
    // so that the user can navigate through the application screens.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.item_home:
                return true;
            case R.id.item_fridge:
                intent = new Intent(HomeActivity.this, FridgeActivity.class);
                startActivity(intent);
                return true;
            case R.id.item_book:
                intent = new Intent(HomeActivity.this, RecipeActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

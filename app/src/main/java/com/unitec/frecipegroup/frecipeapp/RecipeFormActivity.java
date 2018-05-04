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
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.unitec.frecipegroup.frecipeapp.model.Ingredient;
import com.unitec.frecipegroup.frecipeapp.model.Recipe;
import com.unitec.frecipegroup.frecipeapp.sqlite.MySQLiteHelper;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RecipeFormActivity extends AppCompatActivity {
    private Spinner spinner_category;
    private Spinner spinner_unit;
    private View promptView;
    final Context context = this;

    private MySQLiteHelper db;
    private Button btnSave;
    private Button btnDone;
    private Button btnIngredient;
    private Button btnAdd;
    private List<Ingredient> ingredients = new LinkedList<>();
    private Recipe recipe;

    private EditText title;
    private Spinner category;
    private EditText description;
    private EditText method;

    private TableLayout tl;
    private TableRow tr;
    private TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_form);


        // Sets up the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        addCategoriesOnSpinner();
        db = MySQLiteHelper.getInstance(this);

        btnIngredient = (Button) findViewById(R.id.btn_ingredient);
        btnIngredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = (EditText) findViewById(R.id.txt_title);
                final String   txtTitle   = title.getText().toString();

                category = (Spinner) findViewById(R.id.spinner_category);
                final String txtCat   = category.getSelectedItem().toString();

                description = (EditText) findViewById(R.id.txt_desc);
                final String txtDesc   =  description.getText().toString();

                method = (EditText) findViewById(R.id.txt_method);
                final String txtMethod   =  method.getText().toString();

                recipe = new Recipe(txtTitle, txtDesc, txtMethod, txtCat);

                db.addRecipe(recipe);

                LayoutInflater layoutInflater = LayoutInflater.from(context);
                promptView = layoutInflater.inflate(R.layout.add_ingredient, null);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setView(promptView);
                addItemsOnSpinner();
                tl = (TableLayout) promptView.findViewById(R.id.table);
                addHeaders();

                btnAdd = (Button) promptView.findViewById(R.id.btn_add);
                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText name = (EditText) promptView.findViewById(R.id.txt_name);
                        final String   txtName   = name.getText().toString();

                        Spinner unit = (Spinner) promptView.findViewById(R.id.spinner_unit2);
                        final String txtUnit   = unit.getSelectedItem().toString();

                        EditText quantity = (EditText) promptView.findViewById(R.id.text_Qty);
                        final double txtQty   =  Double.parseDouble(quantity.getText().toString());

                        Ingredient ingredient = new Ingredient(txtName, txtQty, txtUnit, recipe);

                        db.addIngredient(ingredient);
                        ingredients.add(ingredient);

                        name.getText().clear();
                        quantity.getText().clear();
                        unit.setSelection(0);

                        addData();
                    }
                });

                alertDialogBuilder.setCancelable(false)
                        .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alertD = alertDialogBuilder.create();
                alertD.show();
            }
        });

        btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recipe.setIngredients(ingredients);
                title.getText().clear();
                description.getText().clear();
                method.getText().clear();
                category.setSelection(0);
            }
        });

        btnDone = (Button) findViewById(R.id.btnDone);
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecipeFormActivity.this, RecipeActivity.class);
                startActivity(intent);
            }
        });
    }

    // add items into spinner dynamically
    public void addItemsOnSpinner(){
        spinner_unit = (Spinner) promptView.findViewById(R.id.spinner_unit2);
        List<String> list = new ArrayList<String>();
        list.add("g");
        list.add("kg");
        list.add("ml");
        list.add("l");
        list.add("oz");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_unit.setAdapter(dataAdapter);
    }

    // add categories into spinner dynamically
    public void addCategoriesOnSpinner() {
        spinner_category = (Spinner) findViewById(R.id.spinner_category);
        List<String> list = new ArrayList<String>();
        list.add("Mains");
        list.add("Sides");
        list.add("Snacks");
        list.add("Desserts");
        list.add("Beverages");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_category.setAdapter(dataAdapter);

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
                intent = new Intent(RecipeFormActivity.this, HomeActivity.class);
                startActivity(intent);
                return true;
            case R.id.item_fridge:
                intent = new Intent(RecipeFormActivity.this, FridgeActivity.class);
                startActivity(intent);
                return true;
            case R.id.item_book:
                intent = new Intent(RecipeFormActivity.this, RecipeActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
        item.setText("Item");
        item.setWidth(178);
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

        for (Ingredient obj : ingredients)
        {
            /** Create a TableRow dynamically **/
            tr = new TableRow(this);
            tr.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));

            /** Creating a TextView to add to the row **/
            name = new TextView(this);
            name.setText(obj.getName());
            name.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);
            name.setWidth(178);
            name.setPadding(5, 5, 5, 5);
            tr.addView(name);  // Adding textView to tablerow.

            // Add the TableRow to the TableLayout
            tl.addView(tr, new TableLayout.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
        }
    }

}

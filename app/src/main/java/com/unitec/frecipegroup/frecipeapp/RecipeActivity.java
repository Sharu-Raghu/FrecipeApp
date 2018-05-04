package com.unitec.frecipegroup.frecipeapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.unitec.frecipegroup.frecipeapp.model.Recipe;
import com.unitec.frecipegroup.frecipeapp.sqlite.MySQLiteHelper;

import java.util.ArrayList;
import java.util.List;

public class RecipeActivity extends AppCompatActivity {

    private static List<Recipe> recipes;
    private MySQLiteHelper db;

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        db = MySQLiteHelper.getInstance(this);

        // Sets up the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

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
                intent = new Intent(RecipeActivity.this, HomeActivity.class);
                startActivity(intent);
                return true;
            case R.id.item_fridge:
                intent = new Intent(RecipeActivity.this, FridgeActivity.class);
                startActivity(intent);
                return true;
            case R.id.item_book:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        private TableLayout tl;
        private TableRow tr;
        private TextView name;

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_recipes, container, false);
            Spinner spinner_cat = (Spinner) rootView.findViewById(R.id.spinner_cat);
            List<String> list = new ArrayList<String>();
            list.add("Show All");
            list.add("Mains");
            list.add("Sides");
            list.add("Snacks");
            list.add("Desserts");
            list.add("Beverages");
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner_cat.setAdapter(dataAdapter);

            tl = (TableLayout) rootView.findViewById(R.id.table);
            addHeaders();
            addData();

            return rootView;
        }

        /** This function add the headers to the table **/
        private void addHeaders(){

            /** Create a TableRow dynamically **/
            tr = new TableRow(getContext());
            tr.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));

            /** Creating a TextView to add to the row **/
            TextView item = new TextView(getContext());
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

            for (Recipe obj : recipes)
            {
                /** Create a TableRow dynamically **/
                tr = new TableRow(getContext());
                tr.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));

                /** Creating a TextView to add to the row **/
                name = new TextView(getContext());
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
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 4 total pages.
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    recipes = db.getAllRecipes();
                    return "Recipes";
                case 1:
                    return "Shared";
                case 2:
                    return "Saved";
                case 3:
                    return "Global";
            }
            return null;
        }
    }

    public void addBtnClick(View v) {
        switch(v.getId()) {
            case R.id.btn_add:
                Intent intent = new Intent(RecipeActivity.this, RecipeFormActivity.class);
                startActivity(intent);
                break;
        }
    }


}

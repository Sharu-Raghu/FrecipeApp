package com.unitec.frecipegroup.frecipeapp.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.unitec.frecipegroup.frecipeapp.model.Ingredient;
import com.unitec.frecipegroup.frecipeapp.model.Item;
import com.unitec.frecipegroup.frecipeapp.model.Recipe;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Yasmin on 15-May-17.
 */

public class MySQLiteHelper extends SQLiteOpenHelper {
    private static MySQLiteHelper sInstance;
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "FrecipeDB";

    public static synchronized MySQLiteHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new MySQLiteHelper(context.getApplicationContext());
            createSampleData();
        }
        return sInstance;
    }

    private static void createSampleData() {
        Recipe recipen = new Recipe("Chocolate Cake", "Low fat, low sugar, perfect for the family!", "Blend, Mix, Bake, etc", "Dessert");
        Ingredient ic = new Ingredient("Flour", 500, "g", recipen);
        Ingredient id = new Ingredient("Chocolate", 100, "g", recipen);
        sInstance.addIngredient(ic);
        sInstance.addIngredient(id);
        sInstance.addRecipe(recipen);

        Recipe recipem = new Recipe("Vanilla Cake", "Low fat, low sugar, perfect for the family!", "Blend, Mix, Bake, etc", "Dessert");
        Ingredient ia = new Ingredient("Flour", 500, "g", recipem);
        Ingredient ib = new Ingredient("Butter", 100, "g", recipem);
        sInstance.addIngredient(ia);
        sInstance.addIngredient(ib);
        sInstance.addRecipe(recipem);

        //Recipe1-Chicken burger

        Recipe recipe1 = new Recipe("Chicken Burger", "Delicious with juicy, crunchy chicken meat!", "Fry, Mix, Bake, etc", "Lunch");
        Ingredient i1 = new Ingredient("Chicken", 500, "g", recipe1);
        Ingredient i2 = new Ingredient("Milk", 100, "ml", recipe1);
        Ingredient i3 = new Ingredient("Sweet Onion", 50, "g", recipe1);
        sInstance.addIngredient(i1);
        sInstance.addIngredient(i2);
        sInstance.addIngredient(i3);
        sInstance.addRecipe(recipe1);

        //Recipe2-Bread and butter pudding

        Recipe recipe2 = new Recipe("Bread and butter pudding", "Perfect for a desert!", "Blend, Mix, Bake, etc", "Dessert");
        Ingredient i4 = new Ingredient("Butter", 50, "g", recipe2);
        Ingredient i5 = new Ingredient("Bread", 500, "g", recipe2);
        Ingredient i6 = new Ingredient("Egg", 250, "g", recipe2);
        Ingredient i7 = new Ingredient("Cream", 300, "ml", recipe2);
        sInstance.addIngredient(i4);
        sInstance.addIngredient(i5);
        sInstance.addIngredient(i6);
        sInstance.addIngredient(i7);
        sInstance.addRecipe(recipe2);

        //Recipe3-Chargrilled vegetable sandwich

        Recipe recipe3 = new Recipe("Chargrilled vegetable sandwich", "With a creamy yoghurt dressing!", "toast,grill, etc", "Lunch");
        Ingredient i8 = new Ingredient("Zucchini", 50, "g", recipe3);
        Ingredient i9 = new Ingredient("EggPlants", 50, "g", recipe3);
        Ingredient i10 = new Ingredient("Capsicum", 50, "g", recipe3);
        Ingredient i11 = new Ingredient("Lemon", 50, "g", recipe3);
        Ingredient i12 = new Ingredient("Yoghurt", 50, "g", recipe3);
        sInstance.addIngredient(i8);
        sInstance.addIngredient(i9);
        sInstance.addIngredient(i10);
        sInstance.addIngredient(i11);
        sInstance.addIngredient(i12);
        sInstance.addRecipe(recipe3);


        //Recipe4-Malaysian fried chicken (Ayam goreng)

        Recipe recipe4 = new Recipe("Malaysian fried chicken(Ayam goreng)", "Tastes just as good as the street-food mamak version!", "Deep-fry,grill,fry, etc", "Lunch");
        Ingredient i13 = new Ingredient("Chicken drumsticks", 1.5, "kg", recipe4);
        Ingredient i14 = new Ingredient("Rice flour", 200, "g", recipe4);
        Ingredient i15 = new Ingredient("Lemongrass ", 50, "g", recipe4);
        Ingredient i16 = new Ingredient("Onion", 150, "g", recipe4);
        Ingredient i17 = new Ingredient("Ginger", 25, "g", recipe4);
        Ingredient i18 = new Ingredient("Mint leaves", 25, "g", recipe4);
        Ingredient i19 = new Ingredient("Coriander leaves", 25, "g", recipe4);
        sInstance.addIngredient(i13);
        sInstance.addIngredient(i14);
        sInstance.addIngredient(i15);
        sInstance.addIngredient(i16);
        sInstance.addIngredient(i17);
        sInstance.addIngredient(i18);
        sInstance.addIngredient(i19);
        sInstance.addRecipe(recipe4);


        //Recipe5-Avocado & feta smash on toasted rye

        Recipe recipe5 = new Recipe("Avocado & feta smash on toasted rye", "Ultimate healthy breakfast in a dash!", "Deep-fry,grill,fry, etc", "Breakfast");
        Ingredient i20 = new Ingredient("Avocado", 500, "g", recipe5);
        Ingredient i21 = new Ingredient("Creamy feta", 80, "g", recipe5);
        Ingredient i22 = new Ingredient("Mint", 50, "g", recipe5);
        Ingredient i23 = new Ingredient("Lemon", 100, "ml", recipe5);
        Ingredient i24 = new Ingredient("Bread", 400, "g", recipe5);
        sInstance.addIngredient(i20);
        sInstance.addIngredient(i21);
        sInstance.addIngredient(i22);
        sInstance.addIngredient(i23);
        sInstance.addIngredient(i24);
        sInstance.addRecipe(recipe5);

        sInstance.addItem(new Item("Flour", 500, "g", "Grains", "30/05/2017"));
        sInstance.addItem(new Item("Chocolate", 100, "g", "Other", "30/05/2018"));
        sInstance.addItem(new Item("Chicken", 1, "kg", "Meat", "6/06/2017"));
        sInstance.addItem(new Item("Milk", 100, "ml", "Dairy", "5/06/2017"));
        sInstance.addItem(new Item("Sweet Onion", 50, "g", "Vegetables", "5/06/2017"));
        sInstance.addItem(new Item("Butter", 50, "g", "Dairy", "5/06/2017"));
        sInstance.addItem(new Item("Bread", 450, "g", "Grains", "5/06/2017"));
        sInstance.addItem(new Item("Egg", 500, "g", "Other", "5/06/2017"));
        sInstance.addItem(new Item("Cream", 400, "ml", "Other", "5/06/2017"));
        sInstance.addItem(new Item("Zucchini", 100, "g", "Vegetables", "5/06/2017"));
        sInstance.addItem(new Item("EggPlants", 50, "g", "Vegetables", "5/06/2017"));
        sInstance.addItem(new Item("Capsicum", 250, "g", "Vegetables", "5/06/2017"));
        sInstance.addItem(new Item("Lemon", 250, "g", "Vegetables", "5/06/2017"));
        sInstance.addItem(new Item("Yoghurt", 250, "g", "Dairy", "5/06/2017"));
        sInstance.addItem(new Item("Chicken drumsticks", 2, "kg", "Meat", "5/06/2017"));
        sInstance.addItem(new Item("Rice flour", 1, "kg", "Grains", "5/06/2017"));
        sInstance.addItem(new Item("Lemongrass", 100, "g", "Vegetables", "5/06/2017"));
        sInstance.addItem(new Item("Onion", 500, "g", "Vegetables", "5/06/2017"));
        sInstance.addItem(new Item("Ginger", 150, "g", "Vegetables", "5/06/2017"));
        sInstance.addItem(new Item("Mint leaves", 100, "g", "Vegetables", "5/06/2017"));
        sInstance.addItem(new Item("Coriander leaves", 100, "g", "Vegetables", "5/06/2017"));
        sInstance.addItem(new Item("Avocado", 600, "g", "Fruits", "5/06/2017"));
        sInstance.addItem(new Item("Creamy feta", 100, "g", "Dairy", "5/06/2017"));
        sInstance.addItem(new Item("Mint", 50, "g", "Vegetables", "5/06/2017"));
    }

    private MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create all tables
        String CREATE_ITEM_TABLE = "CREATE TABLE item ( " +
                "id INTEGER, " +
                "name TEXT, "+
                "quantity REAL, "+
                "unit TEXT, "+
                "category TEXT, "+
                "expiryDate TEXT )";

        String CREATE_RECIPE_TABLE = "CREATE TABLE recipe ( " +
                "id INTEGER, " +
                "name TEXT, "+
                "description TEXT, "+
                "method TEXT, "+
                "category TEXT )";

        String CREATE_INGREDIENT_TABLE = "CREATE TABLE ingredient ( " +
                "id INTEGER, " +
                "name TEXT, "+
                "quantity REAL, "+
                "unit TEXT, "+
                "recipeID INTEGER NOT NULL, "+
                "FOREIGN KEY (recipeID) REFERENCES recipe(id) )";

        // create all tables
        db.execSQL(CREATE_ITEM_TABLE);
        db.execSQL(CREATE_RECIPE_TABLE);
        db.execSQL(CREATE_INGREDIENT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older all tables if existed
        db.execSQL("DROP TABLE IF EXISTS item");
        db.execSQL("DROP TABLE IF EXISTS recipe");
        db.execSQL("DROP TABLE IF EXISTS ingredient");

        // create fresh tables
        this.onCreate(db);
    }

    //==================================================================================================================================

    /**
     * CRUD operations (create "add", read "get", update, delete) records + get all records + delete all records
     */

    // Instantiate table names
    private static final String TABLE_ITEM = "item";
    private static final String TABLE_RECIPE = "recipe";
    private static final String TABLE_INGREDIENT = "ingredient";

    // Instantiate table Columns names
    private static final String ITEM_ID = "id";
    private static final String ITEM_NAME = "name";
    private static final String ITEM_QUANTITY = "quantity";
    private static final String ITEM_UNIT = "unit";
    private static final String ITEM_CATEGORY = "category";
    private static final String ITEM_EXPIRYDATE = "expiryDate";

    private static final String RECIPE_ID = "id";
    private static final String RECIPE_NAME = "name";
    private static final String RECIPE_DESCRIPTION = "description";
    private static final String RECIPE_METHOD = "method";
    private static final String RECIPE_CATEGORY = "category";

    private static final String INGREDIENT_ID = "id";
    private static final String INGREDIENT_NAME = "name";
    private static final String INGREDIENT_QUANTITY = "quantity";
    private static final String INGREDIENT_UNIT = "unit";
    private static final String INGREDIENT_RECIPEID = "recipeID";

    private static final String[] ITEM_COLUMNS = {ITEM_ID,ITEM_NAME,ITEM_QUANTITY,ITEM_UNIT,ITEM_CATEGORY,ITEM_EXPIRYDATE};
    private static final String[] RECIPE_COLUMNS = {RECIPE_ID,RECIPE_NAME,RECIPE_DESCRIPTION,RECIPE_METHOD,RECIPE_CATEGORY};
    private static final String[] INGREDIENT_COLUMNS = {INGREDIENT_ID,INGREDIENT_NAME, INGREDIENT_QUANTITY,INGREDIENT_UNIT,INGREDIENT_RECIPEID};

    //---------------------------------------------------------------------

    // Add record methods
    public void addItem(Item item){
        Log.d("addItem", item.toString());
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(ITEM_ID, item.getId()); // get id
        values.put(ITEM_NAME, item.getName()); // get name
        values.put(ITEM_QUANTITY, item.getQuantity()); // get quantity
        values.put(ITEM_UNIT, item.getUnit()); // get unit
        values.put(ITEM_CATEGORY, item.getCategory()); // get category
        values.put(ITEM_EXPIRYDATE, item.getExpiryDate()); // get expiryDate

        // 3. insert
        db.insert(TABLE_ITEM, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values

        // 4. close
        db.close();
    }

    public void addRecipe(Recipe recipe){
        Log.d("addRecipe", recipe.toString());
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(RECIPE_ID, recipe.getId()); // get id
        values.put(RECIPE_NAME, recipe.getName()); // get name
        values.put(RECIPE_DESCRIPTION, recipe.getDescription()); // get description
        values.put(RECIPE_METHOD, recipe.getMethod()); // get method
        values.put(RECIPE_CATEGORY, recipe.getCategory()); // get category

        // 3. insert
        db.insert(TABLE_RECIPE, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values

        // 4. close
        db.close();
    }

    public void addIngredient(Ingredient ingredient){
        Log.d("addIngredient", ingredient.toString());
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(INGREDIENT_ID, ingredient.getId()); // get id
        values.put(INGREDIENT_NAME, ingredient.getName()); // get name
        values.put(INGREDIENT_QUANTITY, ingredient.getQuantity()); // get quantity
        values.put(INGREDIENT_UNIT, ingredient.getQuantity()); // get unit
        values.put(INGREDIENT_RECIPEID, ingredient.getRecipe().getId()); // get recipeID

        // 3. insert
        db.insert(TABLE_INGREDIENT, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values

        // 4. close
        db.close();
    }

    //---------------------------------------------------------------------

    public Item getItem(int id){

        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();

        // 2. build query
        Cursor cursor =
                db.query(TABLE_ITEM, // a. table
                        ITEM_COLUMNS, // b. column names
                        " id = ?", // c. selections
                        new String[] { String.valueOf(id) }, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit

        // 3. if we got results get the first one
        if (cursor != null)
            cursor.moveToFirst();

        // 4. build item object
        Item item = new Item();
        item.setId(Integer.parseInt(cursor.getString(0)));
        item.setName(cursor.getString(1));
        item.setQuantity(cursor.getInt(2));
        item.setUnit(cursor.getString(3));
        item.setCategory(cursor.getString(4));
        item.setExpiryDate(cursor.getString(5));

        Log.d("getItem("+id+")", item.toString());

        // 5. return item
        return item;
    }

    public Recipe getRecipe(int id){

        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();

        // 2. build query
        Cursor cursor =
                db.query(TABLE_RECIPE, // a. table
                        RECIPE_COLUMNS, // b. column names
                        " id = ?", // c. selections
                        new String[] { String.valueOf(id) }, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit

        // 3. if we got results get the first one
        if (cursor != null)
            cursor.moveToFirst();

        // 4. build recipe object
        Recipe recipe = new Recipe();
        recipe.setId(Integer.parseInt(cursor.getString(0)));
        recipe.setName(cursor.getString(1));
        recipe.setDescription(cursor.getString(2));
        recipe.setMethod(cursor.getString(3));
        recipe.setCategory(cursor.getString(4));

        List<Ingredient> ingredients = getAllIngredientsForRecipe(recipe);
        recipe.setIngredients(ingredients);

        Log.d("getRecipe("+id+")", recipe.toString());

        // 5. return recipe
        return recipe;
    }

    public Ingredient getIngredient(int id){

        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();

        // 2. build query
        Cursor cursor =
                db.query(TABLE_INGREDIENT, // a. table
                        INGREDIENT_COLUMNS, // b. column names
                        " id = ?", // c. selections
                        new String[] { String.valueOf(id) }, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit

        // 3. if we got results get the first one
        if (cursor != null)
            cursor.moveToFirst();

        // 4. build item object
        Ingredient ingredient = new Ingredient();
        ingredient.setId(Integer.parseInt(cursor.getString(0)));
        ingredient.setName(cursor.getString(1));
        ingredient.setQuantity(cursor.getInt(2));
        ingredient.setUnit(cursor.getString(3));
        Recipe recipe = getRecipe(cursor.getInt(4));
        ingredient.setRecipe(recipe);

        Log.d("getIngredient("+id+")", ingredient.toString());

        // 5. return ingredient
        return ingredient;
    }

    //---------------------------------------------------------------------

    // Get All Items
    public List<Item> getAllItemsForCategory(String category) {
        List<Item> items = new LinkedList<Item>();
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. build query
        Cursor cursor =
                db.query(TABLE_ITEM, // a. table
                        ITEM_COLUMNS, // b. column names
                        " category = ?", // c. selections
                        new String[] { String.valueOf(category) }, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit

        // 3. go over each row, build book and add it to list
        Item item = null;
        if (cursor.moveToFirst()) {
            do {
                item = new Item();
                item.setId(Integer.parseInt(cursor.getString(0)));
                item.setName(cursor.getString(1));
                item.setQuantity(cursor.getInt(2));
                item.setUnit(cursor.getString(3));
                item.setCategory(cursor.getString(4));
                item.setExpiryDate(cursor.getString(5));

                // Add item to items
                items.add(item);
            } while (cursor.moveToNext());
        }

        Log.d("getItemsForCategory", items.toString());

        // return items
        return items;
    }

    // Get All Items
    public List<Item> getAllItems() {
        List<Item> items = new LinkedList<Item>();

        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_ITEM;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        // 3. go over each row, build book and add it to list
        Item item = null;
        if (cursor.moveToFirst()) {
            do {
                item = new Item();
                item.setId(Integer.parseInt(cursor.getString(0)));
                item.setName(cursor.getString(1));
                item.setQuantity(cursor.getInt(2));
                item.setUnit(cursor.getString(3));
                item.setCategory(cursor.getString(4));
                item.setExpiryDate(cursor.getString(5));

                // Add item to items
                items.add(item);
            } while (cursor.moveToNext());
        }

        Log.d("getAllItems()", items.toString());

        // return items
        return items;
    }

    public List<Ingredient> getAllIngredientsForRecipe(Recipe recipe) {
        List<Ingredient> ingredients = new LinkedList<Ingredient>();
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. build query
        Cursor cursor =
                db.query(TABLE_INGREDIENT, // a. table
                        INGREDIENT_COLUMNS, // b. column names
                        " recipeID = ?", // c. selections
                        new String[] { String.valueOf(recipe.getId()) }, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit



        // 3. go over each row, build book and add it to list
        Ingredient ingredient = null;
        if (cursor.moveToFirst()) {
            do {
                ingredient = new Ingredient();
                ingredient.setId(Integer.parseInt(cursor.getString(0)));
                ingredient.setName(cursor.getString(1));
                ingredient.setQuantity(cursor.getInt(2));
                ingredient.setUnit(cursor.getString(3));
                ingredient.setRecipe(recipe);

                // Add ingredient to ingredients
                ingredients.add(ingredient);
            } while (cursor.moveToNext());
        }

        Log.d("getAllIngredients()", ingredients.toString());

        // return ingredients
        return ingredients;
    }

    public List<Recipe> getAllRecipes() {
        List<Recipe> recipes = new LinkedList<Recipe>();

        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_RECIPE;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build book and add it to list
        Recipe recipe = null;
        if (cursor.moveToFirst()) {
            do {
                recipe = new Recipe();
                recipe.setId(Integer.parseInt(cursor.getString(0)));
                recipe.setName(cursor.getString(1));
                recipe.setDescription(cursor.getString(2));
                recipe.setMethod(cursor.getString(3));
                recipe.setCategory(cursor.getString(4));

                List<Ingredient> ingredients = getAllIngredientsForRecipe(recipe);
                recipe.setIngredients(ingredients);

                // Add recipe to recipes
                recipes.add(recipe);
            } while (cursor.moveToNext());
        }

        Log.d("getAllRecipes()", recipes.toString());

        // return recipes
        return recipes;
    }

    //---------------------------------------------------------------------

    // Updating single item
    public int updateItem(Item item) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(ITEM_NAME, item.getName()); // get name
        values.put(ITEM_QUANTITY, item.getQuantity()); // get quantity
        values.put(ITEM_UNIT, item.getUnit()); // get unit
        values.put(ITEM_CATEGORY, item.getCategory()); // get category
        values.put(ITEM_EXPIRYDATE, item.getExpiryDate()); // get expiryDate

        // 3. updating row
        int i = db.update(TABLE_ITEM, //table
                values, // column/value
                ITEM_ID+" = ?", // selections
                new String[] { String.valueOf(item.getId()) }); //selection args

        // 4. close
        db.close();

        return i;
    }

    public int updateIngredient(Ingredient ingredient) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(INGREDIENT_NAME, ingredient.getName()); // get name
        values.put(INGREDIENT_QUANTITY, ingredient.getQuantity()); // get quantity
        values.put(INGREDIENT_UNIT, ingredient.getUnit()); // get unit
        values.put(INGREDIENT_RECIPEID, ingredient.getRecipe().getId()); // get recipeID

        // 3. updating row
        int i = db.update(TABLE_INGREDIENT, //table
                values, // column/value
                INGREDIENT_ID+" = ?", // selections
                new String[] { String.valueOf(ingredient.getId()) }); //selection args

        // 4. close
        db.close();

        return i;
    }

    public int updateRecipe(Recipe recipe) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(RECIPE_NAME, recipe.getName()); // get name
        values.put(RECIPE_DESCRIPTION, recipe.getDescription()); // get description
        values.put(RECIPE_METHOD, recipe.getMethod()); // get method
        values.put(RECIPE_CATEGORY, recipe.getCategory()); // get category

        // 3. updating row
        int i = db.update(TABLE_RECIPE, //table
                values, // column/value
                RECIPE_ID+" = ?", // selections
                new String[] { String.valueOf(recipe.getId()) }); //selection args

        // 4. close
        db.close();

        return i;
    }

    //---------------------------------------------------------------------

    // Deleting single item
    public void deleteItem(Item item) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_ITEM,
                ITEM_ID+" = ?",
                new String[] { String.valueOf(item.getId()) });

        // 3. close
        db.close();

        Log.d("deleteItem", item.toString());

    }

    // Deleting single ingredient
    public void deleteIngredient(Ingredient ingredient) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_INGREDIENT,
                INGREDIENT_ID+" = ?",
                new String[] { String.valueOf(ingredient.getId()) });

        // 3. close
        db.close();

        Log.d("deleteIngredient", ingredient.toString());

    }

    // Deleting single recipe
    public void deleteRecipe(Recipe recipe) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_RECIPE,
                RECIPE_ID+" = ?",
                new String[] { String.valueOf(recipe.getId()) });

        // 3. close
        db.close();

        Log.d("deleteRecipe", recipe.toString());

    }
}

package co.touchlab.basicsqlite;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kgalligan on 7/11/15.
 */
public class MySQLiteOpenHelper extends SQLiteOpenHelper
{
    public static final String MYDB = "mydb";
    public static final int VERSION = 1;

    private static MySQLiteOpenHelper INSTANCE;

    public static synchronized MySQLiteOpenHelper getInstance(Context context)
    {
        if(INSTANCE == null)
        {
            INSTANCE = new MySQLiteOpenHelper(context.getApplicationContext());
        }

        return INSTANCE;
    }

    private MySQLiteOpenHelper(Context context)
    {
        super(context, MYDB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void insertData()
    {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(PersonEntry.TABLE_NAME, null, null);
        insertRow("Mike", 27, "blue");
        insertRow("Jen", 32, "orange");
    }

    private void insertRow(String name, int age, String favoriteColor)
    {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PersonEntry.COLUMN_NAME_NAME, name);
        values.put(PersonEntry.COLUMN_NAME_AGE, age);
        values.put(PersonEntry.COLUMN_NAME_FAVORITE_COLOR, favoriteColor);

        db.insert(
                PersonEntry.TABLE_NAME,
                null,
                values);
    }

    public List<Person> loadData()
    {
        String[] projection = {
                PersonEntry._ID,
                PersonEntry.COLUMN_NAME_NAME,
                PersonEntry.COLUMN_NAME_AGE,
                PersonEntry.COLUMN_NAME_FAVORITE_COLOR
        };

        SQLiteDatabase db = getWritableDatabase();

        List<Person> persons = new ArrayList<>();

        Cursor cursor = db.query(PersonEntry.TABLE_NAME, projection, null, null, null, null, null);
        while(cursor.moveToNext())
        {
            persons.add(new Person(cursor.getInt(cursor.getColumnIndex(PersonEntry._ID)),
                                   cursor.getString(
                                           cursor.getColumnIndex(PersonEntry.COLUMN_NAME_NAME)),
                                   cursor.getInt(
                                           cursor.getColumnIndex(PersonEntry.COLUMN_NAME_AGE)),
                                   cursor.getString(cursor.getColumnIndex(
                                           PersonEntry.COLUMN_NAME_FAVORITE_COLOR))));
        }

        cursor.close();

        return persons;
    }

    public static abstract class PersonEntry implements BaseColumns
    {
        public static final String TABLE_NAME                 = "person";
        public static final String COLUMN_NAME_NAME           = "name";
        public static final String COLUMN_NAME_AGE            = "age";
        public static final String COLUMN_NAME_FAVORITE_COLOR = "favorite_color";
    }

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";

    //Will be: CREATE TABLE person (_id INTEGER PRIMARY KEY,name TEXT,age INTEGER,favorite_color TEXT )
    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + PersonEntry.TABLE_NAME + " (" +
            PersonEntry._ID + " INTEGER PRIMARY KEY," +
            PersonEntry.COLUMN_NAME_NAME + " TEXT," +
            PersonEntry.COLUMN_NAME_AGE + " INTEGER," +
            PersonEntry.COLUMN_NAME_FAVORITE_COLOR + " TEXT" +
            " )";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + PersonEntry.TABLE_NAME;
}

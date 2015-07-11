package co.touchlab.basicsqlite;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by kgalligan on 7/11/15.
 */
public class MySQLiteOpenHelper extends SQLiteOpenHelper
{

    public static final String MYDB = "mydb";
    public static final int VERSION = 1;

    public MySQLiteOpenHelper(Context context)
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

    public static abstract class PersonEntry implements BaseColumns
    {
        public static final String TABLE_NAME = "person";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_AGE = "age";
        public static final String COLUMN_NAME_FAVORITE_COLOR = "favorite_color";
    }

    private static final String TEXT_TYPE          = " TEXT";
    private static final String COMMA_SEP          = ",";
    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + PersonEntry.TABLE_NAME + " (" +
            PersonEntry._ID + " INTEGER PRIMARY KEY," +
            PersonEntry.COLUMN_NAME_NAME + " TEXT," +
            PersonEntry.COLUMN_NAME_AGE + " INTEGER," +
            PersonEntry.COLUMN_NAME_FAVORITE_COLOR + " TEXT" +
            " )";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + PersonEntry.TABLE_NAME;
}

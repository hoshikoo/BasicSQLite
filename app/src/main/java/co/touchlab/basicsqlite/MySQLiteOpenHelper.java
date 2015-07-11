package co.touchlab.basicsqlite;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.List;

import co.touchlab.basicsqlite.data.Person;

/**
 * Created by kgalligan on 7/11/15.
 */
public class MySQLiteOpenHelper extends OrmLiteSqliteOpenHelper
{
    public static final String MYDB = "mydb";
    public static final int VERSION = 2;

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
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource)
    {
        try
        {
            TableUtils.createTable(connectionSource, co.touchlab.basicsqlite.data.Person.class);
        }
        catch(SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion)
    {
        try
        {
            TableUtils.dropTable(connectionSource, co.touchlab.basicsqlite.data.Person.class, false);
            onCreate(database, connectionSource);
        }
        catch(SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void insertData() throws SQLException
    {
        Dao<Person, ?> dao = getDao(Person.class);

        dao.delete(dao.deleteBuilder().prepare());
        insertRow("Mike", 27, "blue");
        insertRow("Jen", 32, "orange");
    }

    private void insertRow(String name, int age, String favoriteColor) throws SQLException
    {
        Person person = new Person();
        person.setName(name);
        person.setAge(age);
        person.setFavoriteColor(favoriteColor);
        getDao(Person.class).create(person);
    }

    public List<Person> loadData() throws SQLException
    {
        return getDao(Person.class).queryForAll();
    }

}

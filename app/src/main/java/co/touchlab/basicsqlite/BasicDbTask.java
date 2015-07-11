package co.touchlab.basicsqlite;
import android.content.Context;

import java.util.List;

import co.touchlab.android.threading.eventbus.EventBusExt;
import co.touchlab.android.threading.tasks.Task;
import co.touchlab.basicsqlite.data.Person;

/**
 * Created by kgalligan on 7/11/15.
 */
public class BasicDbTask extends Task
{
    public List<Person> persons;

    @Override
    protected void run(Context context) throws Throwable
    {
        MySQLiteOpenHelper mySQLiteOpenHelper = MySQLiteOpenHelper.getInstance(context);

        mySQLiteOpenHelper.insertData();

        persons = mySQLiteOpenHelper.loadData();
    }

    @Override
    protected void onComplete(Context context)
    {
        EventBusExt.getDefault().post(this);
    }

    @Override
    protected boolean handleError(Context context, Throwable e)
    {
        return false;
    }
}

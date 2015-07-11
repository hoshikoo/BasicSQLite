package co.touchlab.basicsqlite;
import android.content.Context;
import android.widget.Toast;

import co.touchlab.android.threading.tasks.Task;

/**
 * Created by kgalligan on 7/11/15.
 */
public class BasicDbTask extends Task
{
    @Override
    protected void run(Context context) throws Throwable
    {
        MySQLiteOpenHelper mySQLiteOpenHelper = new MySQLiteOpenHelper(context);

        //This will make it open
        mySQLiteOpenHelper.getWritableDatabase();

        mySQLiteOpenHelper.close();
    }

    @Override
    protected void onComplete(Context context)
    {
        Toast.makeText(context, "Done!", Toast.LENGTH_LONG).show();
    }

    @Override
    protected boolean handleError(Context context, Throwable e)
    {
        return false;
    }
}
